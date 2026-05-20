package mx.gob.sat.catalogo.service.api.moneda;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.basems.utils.consts.GeneralConst;
import mx.gob.sat.catalogo.controller.request.moneda.CatMonedaRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.moneda.CatMonedaResponse;
import mx.gob.sat.catalogo.model.entity.CatMoneda;
import mx.gob.sat.catalogo.repository.CatMonedaRepository;
import mx.gob.sat.catalogo.util.SortValidator;
import mx.gob.sat.catalogo.util.consts.CodigoErrorConst;
import mx.gob.sat.catalogo.util.mapper.CatMonedaMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * <b>Class:</b> CatMonedaAPIServiceImpl.java <br>
 * <b>Description:</b>
 * <p>Implementacion del servicio API para el catalogo de monedas.</p>
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
class CatMonedaAPIServiceImpl implements CatMonedaAPIService {

    /** Columnas permitidas para ordenamiento. */
    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of(
            "cveMoneda", "cveMoneda",
            "nombre", "nombre"
    );

    /** Columna de ordenamiento predeterminada. */
    private static final String DEFAULT_SORT = "nombre";

    private final CatMonedaRepository monedaRepository;
    private final CatMonedaMapper monedaMapper;

    /**
     * Lista las monedas de forma paginada.
     *
     * @param pagina Numero de pagina.
     * @param tamano Tamano de pagina.
     * @param busqueda Texto de busqueda.
     * @param sortBy Columna de ordenamiento.
     * @param sortDir Direccion de ordenamiento.
     * @return Pagina de monedas en BaseResponse.
     */
    @Override
    public BaseResponse<PaginaResponse<CatMonedaResponse>> listar(
            final int pagina, final int tamano,
            final String busqueda, final String sortBy, final String sortDir) {

        final Sort sort = SortValidator.buildSort(sortBy, sortDir, ALLOWED_SORT_COLUMNS);
        final Pageable pageable = sort.isSorted()
                ? PageRequest.of(pagina, tamano, sort)
                : PageRequest.of(pagina, tamano, Sort.by(Sort.Direction.ASC, DEFAULT_SORT));

        final Page<CatMoneda> paginaResultado = (busqueda == null || busqueda.isBlank())
                ? monedaRepository.findAll(pageable)
                : monedaRepository.findByNombreContainingIgnoreCaseOrCveMonedaContainingIgnoreCase(
                        busqueda, busqueda, pageable);

        final List<CatMonedaResponse> contenido = monedaMapper.toResponseList(paginaResultado.getContent());
        return exito(PaginaResponse.<CatMonedaResponse>builder()
                .contenido(contenido)
                .pagina(paginaResultado.getNumber())
                .tamano(paginaResultado.getSize())
                .total(paginaResultado.getTotalElements())
                .totalPaginas(paginaResultado.getTotalPages())
                .ultima(paginaResultado.isLast())
                .build());
    }

    /**
     * Crea una nueva moneda.
     *
     * @param request Datos de la moneda.
     * @return Moneda creada en BaseResponse.
     */
    @Override
    public BaseResponse<CatMonedaResponse> crear(final CatMonedaRequest request) {
        final CatMoneda guardada = monedaRepository.save(monedaMapper.toEntity(request));
        log.info("Moneda creada: {}", guardada.getCveMoneda());
        return exito(monedaMapper.toResponse(guardada));
    }

    /**
     * Busca una moneda por su clave.
     *
     * @param cveMoneda Clave de la moneda.
     * @return Moneda en BaseResponse.
     */
    @Override
    public BaseResponse<CatMonedaResponse> findById(final String cveMoneda) {
        final Optional<CatMoneda> resultado = monedaRepository.findById(cveMoneda);
        if (resultado.isEmpty()) {
            return error(CodigoErrorConst.MONEDA_NO_ENCONTRADA, "Moneda no encontrada: " + cveMoneda);
        }
        return exito(monedaMapper.toResponse(resultado.get()));
    }

    /**
     * Actualiza los campos no nulos de una moneda existente.
     *
     * @param cveMoneda Clave de la moneda.
     * @param request Datos de actualizacion.
     * @return Moneda actualizada en BaseResponse.
     */
    @Override
    public BaseResponse<CatMonedaResponse> actualizar(final String cveMoneda, final CatMonedaRequest request) {
        final Optional<CatMoneda> opt = monedaRepository.findById(cveMoneda);
        if (opt.isEmpty()) {
            return error(CodigoErrorConst.MONEDA_NO_ENCONTRADA, "Moneda no encontrada: " + cveMoneda);
        }
        final CatMoneda entidad = opt.get();
        if (request.getNombre() != null) {
            entidad.setNombre(request.getNombre());
        }
        if (request.getFecCaptura() != null) {
            entidad.setFecCaptura(request.getFecCaptura());
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
        return exito(monedaMapper.toResponse(monedaRepository.save(entidad)));
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
