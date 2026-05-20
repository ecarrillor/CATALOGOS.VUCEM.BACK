package mx.gob.sat.catalogo.service.api.entidad;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.basems.utils.consts.GeneralConst;
import mx.gob.sat.catalogo.controller.request.entidad.CatEntidadRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.SelectResponse;
import mx.gob.sat.catalogo.controller.response.aduana.CatEntidadResponse;
import mx.gob.sat.catalogo.controller.response.pais.CatPaisResponse;
import mx.gob.sat.catalogo.model.entity.CatEntidad;
import mx.gob.sat.catalogo.model.entity.CatPais;
import mx.gob.sat.catalogo.repository.CatEntidadRepository;
import mx.gob.sat.catalogo.repository.CatPaisRepository;
import mx.gob.sat.catalogo.util.SortValidator;
import mx.gob.sat.catalogo.util.consts.CodigoErrorConst;
import mx.gob.sat.catalogo.util.mapper.CatEntidadMapper;
import mx.gob.sat.catalogo.util.mapper.CatPaisMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * <b>Class:</b> CatEntidadAPIServiceImpl.java <br>
 * <b>Description:</b>
 * <p>Implementacion del servicio API para el catalogo de entidades federativas.</p>
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
class CatEntidadAPIServiceImpl implements CatEntidadAPIService {

    /** Columnas permitidas para ordenamiento. */
    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of(
            "cveEntidad", "cveEntidad",
            "nombre", "nombre",
            "codEntidadIdc", "codEntidadIdc",
            "cvePais", "cvePais.nombre"
    );

    /** Columna de ordenamiento predeterminada. */
    private static final String DEFAULT_SORT = "nombre";

    private final CatEntidadRepository entidadRepository;
    private final CatPaisRepository paisRepository;
    private final CatEntidadMapper entidadMapper;
    private final CatPaisMapper paisMapper;

    /**
     * Lista las entidades de forma paginada.
     *
     * @param pagina Numero de pagina.
     * @param tamano Tamano de pagina.
     * @param busqueda Texto de busqueda.
     * @param sortBy Columna de ordenamiento.
     * @param sortDir Direccion de ordenamiento.
     * @return Pagina de entidades en BaseResponse.
     */
    @Override
    public BaseResponse<PaginaResponse<CatEntidadResponse>> listar(
            final int pagina, final int tamano,
            final String busqueda, final String sortBy, final String sortDir) {

        final Sort sort = SortValidator.buildSort(sortBy, sortDir, ALLOWED_SORT_COLUMNS);
        final Pageable pageable = sort.isSorted()
                ? PageRequest.of(pagina, tamano, sort)
                : PageRequest.of(pagina, tamano);

        final Page<CatEntidad> paginaResultado = (busqueda == null || busqueda.isBlank())
                ? entidadRepository.findAll(pageable)
                : entidadRepository.findByNombreContainingIgnoreCaseOrCveEntidadContainingIgnoreCase(
                        busqueda, busqueda, pageable);

        final List<CatEntidadResponse> contenido = entidadMapper.toResponseList(paginaResultado.getContent());
        return exito(PaginaResponse.<CatEntidadResponse>builder()
                .contenido(contenido)
                .pagina(paginaResultado.getNumber())
                .tamano(paginaResultado.getSize())
                .total(paginaResultado.getTotalElements())
                .totalPaginas(paginaResultado.getTotalPages())
                .ultima(paginaResultado.isLast())
                .build());
    }

    /**
     * Crea una nueva entidad federativa.
     *
     * @param request Datos de la entidad.
     * @return Entidad creada en BaseResponse.
     */
    @Override
    public BaseResponse<CatEntidadResponse> crear(final CatEntidadRequest request) {
        final CatEntidad entidad = entidadMapper.toEntity(request);
        if (request.getCvePais() != null) {
            final CatPais pais = new CatPais();
            pais.setCvePais(request.getCvePais());
            entidad.setCvePais(pais);
        }
        final CatEntidad guardada = entidadRepository.save(entidad);
        log.info("Entidad creada: {}", guardada.getCveEntidad());
        return exito(entidadMapper.toResponse(guardada));
    }

    /**
     * Busca una entidad por su clave.
     *
     * @param cveEntidad Clave de la entidad.
     * @return Entidad en BaseResponse.
     */
    @Override
    public BaseResponse<CatEntidadResponse> findById(final String cveEntidad) {
        final Optional<CatEntidad> resultado = entidadRepository.findById(cveEntidad);
        if (resultado.isEmpty()) {
            return error(CodigoErrorConst.ENTIDAD_NO_ENCONTRADA, "Entidad no encontrada: " + cveEntidad);
        }
        return exito(entidadMapper.toResponse(resultado.get()));
    }

    /**
     * Actualiza los campos no nulos de una entidad existente.
     *
     * @param cveEntidad Clave de la entidad.
     * @param request Datos de actualizacion.
     * @return Entidad actualizada en BaseResponse.
     */
    @Override
    public BaseResponse<CatEntidadResponse> actualizar(
            final String cveEntidad, final CatEntidadRequest request) {

        final Optional<CatEntidad> opt = entidadRepository.findById(cveEntidad);
        if (opt.isEmpty()) {
            return error(CodigoErrorConst.ENTIDAD_NO_ENCONTRADA, "Entidad no encontrada: " + cveEntidad);
        }
        final CatEntidad entidad = opt.get();
        if (request.getNombre() != null) {
            entidad.setNombre(request.getNombre());
        }
        if (request.getCodEntidadIdc() != null) {
            entidad.setCodEntidadIdc(request.getCodEntidadIdc());
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
        if (request.getCvePais() != null) {
            final CatPais pais = new CatPais();
            pais.setCvePais(request.getCvePais());
            entidad.setCvePais(pais);
        }
        return exito(entidadMapper.toResponse(entidadRepository.save(entidad)));
    }

    /**
     * Retorna todos los paises disponibles.
     *
     * @return Lista de paises en BaseResponse.
     */
    @Override
    public BaseResponse<List<CatPaisResponse>> listarPaises() {
        return exito(paisMapper.toResponseList(paisRepository.findAll()));
    }

    /**
     * Retorna lista de entidades en formato clave-nombre para dropdowns.
     *
     * @return Lista de seleccion en BaseResponse.
     */
    @Override
    public BaseResponse<List<SelectResponse>> listarNombres() {
        final List<SelectResponse> lista = entidadRepository.findAll().stream()
                .map(e -> SelectResponse.builder()
                        .cve(e.getCveEntidad())
                        .nombre(e.getNombre())
                        .build())
                .toList();
        return exito(lista);
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
