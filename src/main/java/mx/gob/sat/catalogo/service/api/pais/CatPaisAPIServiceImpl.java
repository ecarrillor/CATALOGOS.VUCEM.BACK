package mx.gob.sat.catalogo.service.api.pais;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.basems.utils.consts.GeneralConst;
import mx.gob.sat.catalogo.controller.request.pais.CatPaisRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.SelectResponse;
import mx.gob.sat.catalogo.controller.response.pais.CatPaisResponse;
import mx.gob.sat.catalogo.model.entity.CatMoneda;
import mx.gob.sat.catalogo.model.entity.CatPais;
import mx.gob.sat.catalogo.repository.CatMonedaRepository;
import mx.gob.sat.catalogo.repository.CatPaisRepository;
import mx.gob.sat.catalogo.util.SortValidator;
import mx.gob.sat.catalogo.util.consts.CodigoErrorConst;
import mx.gob.sat.catalogo.util.mapper.CatPaisMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * <b>Class:</b> CatPaisAPIServiceImpl.java <br>
 * <b>Description:</b>
 * <p>Implementacion del servicio API para el catalogo de paises.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Implementacion del servicio API
 */
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
class CatPaisAPIServiceImpl implements CatPaisAPIService {

    /** Columnas permitidas para ordenamiento. */
    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of(
            "cvePais", "cvePais",
            "nombre", "nombre",
            "cvePaisWco", "cvePaisWco"
    );

    /** Columna de ordenamiento predeterminada. */
    private static final String DEFAULT_SORT = "nombre";

    private final CatPaisRepository paisRepository;
    private final CatMonedaRepository monedaRepository;
    private final CatPaisMapper paisMapper;

    /**
     * Lista los paises de forma paginada con filtro de texto y estado activo.
     *
     * @param pagina Numero de pagina.
     * @param tamano Tamano de pagina.
     * @param busqueda Texto de busqueda o "activo"/"inactivo".
     * @param sortBy Columna de ordenamiento.
     * @param sortDir Direccion de ordenamiento.
     * @return Pagina de paises en BaseResponse.
     */
    @Override
    public BaseResponse<PaginaResponse<CatPaisResponse>> listar(
            final int pagina, final int tamano,
            final String busqueda, final String sortBy, final String sortDir) {

        final Sort sort = SortValidator.buildSort(sortBy, sortDir, ALLOWED_SORT_COLUMNS);
        final Pageable pageable = sort.isSorted()
                ? PageRequest.of(pagina, tamano, sort)
                : PageRequest.of(pagina, tamano, Sort.by(Sort.Direction.ASC, DEFAULT_SORT));

        Boolean activo = null;
        String texto = null;

        if (busqueda != null && !busqueda.isBlank()) {
            final String termino = busqueda.trim().toLowerCase();
            if ("activo".equals(termino)) {
                activo = Boolean.TRUE;
            } else if ("inactivo".equals(termino)) {
                activo = Boolean.FALSE;
            } else {
                texto = "%" + termino + "%";
            }
        }

        final Page<CatPais> paginaResultado = paisRepository.search(texto, activo, pageable);
        final List<CatPaisResponse> contenido = paisMapper.toResponseList(paginaResultado.getContent());
        return exito(PaginaResponse.<CatPaisResponse>builder()
                .contenido(contenido)
                .pagina(paginaResultado.getNumber())
                .tamano(paginaResultado.getSize())
                .total(paginaResultado.getTotalElements())
                .totalPaginas(paginaResultado.getTotalPages())
                .ultima(paginaResultado.isLast())
                .build());
    }

    /**
     * Busca un pais por su clave.
     *
     * @param cvePais Clave del pais.
     * @return Pais en BaseResponse.
     */
    @Override
    public BaseResponse<CatPaisResponse> findById(final String cvePais) {
        final Optional<CatPais> resultado = paisRepository.findById(cvePais);
        if (resultado.isEmpty()) {
            return error(CodigoErrorConst.PAIS_NO_ENCONTRADO, "Pais no encontrado: " + cvePais);
        }
        return exito(paisMapper.toResponse(resultado.get()));
    }

    /**
     * Crea un nuevo pais. Logica de negocio: fecCaptura = hoy, blnActivo = true,
     * blnRestriccion = true, nombreAlterno = nombre si no se especifica.
     *
     * @param request Datos del pais.
     * @return Pais creado en BaseResponse.
     */
    @Override
    public BaseResponse<CatPaisResponse> crear(final CatPaisRequest request) {
        final CatPais entidad = new CatPais();
        entidad.setCvePais(request.getCvePais());
        entidad.setNombre(request.getNombre());
        entidad.setCvePaisWco(request.getCvePaisWco());
        entidad.setFecCaptura(LocalDate.now());
        entidad.setFecIniVigencia(request.getFecIniVigencia());
        entidad.setFecFinVigencia(request.getFecFinVigencia());
        entidad.setBlnActivo(Boolean.TRUE);
        entidad.setBlnRestriccion(Boolean.TRUE);
        entidad.setNombreAlterno(request.getNombreAlterno() != null
                ? request.getNombreAlterno() : request.getNombre());

        if (request.getCveMoneda() != null) {
            final Optional<CatMoneda> moneda = monedaRepository.findById(request.getCveMoneda());
            if (moneda.isEmpty()) {
                return error(CodigoErrorConst.MONEDA_NO_ENCONTRADA,
                        "Moneda no encontrada: " + request.getCveMoneda());
            }
            entidad.setCveMoneda(moneda.get());
        }

        final CatPais guardado = paisRepository.save(entidad);
        log.info("Pais creado: {}", guardado.getCvePais());
        return exito(paisMapper.toResponse(guardado));
    }

    /**
     * Actualiza los campos no nulos de un pais existente.
     *
     * @param cvePais Clave del pais.
     * @param request Datos de actualizacion.
     * @return Pais actualizado en BaseResponse.
     */
    @Override
    public BaseResponse<CatPaisResponse> actualizar(final String cvePais, final CatPaisRequest request) {
        final Optional<CatPais> opt = paisRepository.findById(cvePais);
        if (opt.isEmpty()) {
            return error(CodigoErrorConst.PAIS_NO_ENCONTRADO, "Pais no encontrado: " + cvePais);
        }
        final CatPais entidad = opt.get();
        if (request.getNombre() != null) {
            entidad.setNombre(request.getNombre());
        }
        if (request.getCvePaisWco() != null) {
            entidad.setCvePaisWco(request.getCvePaisWco());
        }
        if (request.getFecIniVigencia() != null) {
            entidad.setFecIniVigencia(request.getFecIniVigencia());
        }
        if (request.getFecFinVigencia() != null) {
            entidad.setFecFinVigencia(request.getFecFinVigencia());
        }
        if (request.getBlnActivo() != null) {
            entidad.setBlnActivo(request.getBlnActivo());
        }
        if (request.getNombreAlterno() != null) {
            entidad.setNombreAlterno(request.getNombreAlterno());
        }
        if (request.getCveMoneda() != null) {
            final Optional<CatMoneda> moneda = monedaRepository.findById(request.getCveMoneda());
            if (moneda.isEmpty()) {
                return error(CodigoErrorConst.MONEDA_NO_ENCONTRADA,
                        "Moneda no encontrada: " + request.getCveMoneda());
            }
            entidad.setCveMoneda(moneda.get());
        }
        return exito(paisMapper.toResponse(paisRepository.save(entidad)));
    }

    /**
     * Retorna lista de monedas en formato clave-nombre para dropdowns.
     *
     * @return Lista de seleccion de monedas en BaseResponse.
     */
    @Override
    public BaseResponse<List<SelectResponse>> listarMonedas() {
        final List<SelectResponse> monedas = monedaRepository.findAll().stream()
                .map(m -> SelectResponse.builder()
                        .cve(m.getCveMoneda())
                        .nombre(m.getNombre())
                        .build())
                .toList();
        return exito(monedas);
    }

    private <T> BaseResponse<T> exito(final T resultado) {
        final BaseResponse<T> r = new BaseResponse<>();
        r.setCodigo(GeneralConst.CODIGO_EXITO);
        r.setMensaje(GeneralConst.MENSAJE_OPERACION_EXITOSA);
        r.setResultado(resultado);
        return r;
    }

    private <T> BaseResponse<T> error(final String codigo, final String mensaje) {
        final BaseResponse<T> r = new BaseResponse<>();
        r.setCodigo(codigo);
        r.setMensaje(GeneralConst.MENSAJE_OPERACION_FALLIDA);
        r.setError(mensaje);
        return r;
    }
}
