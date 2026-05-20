package mx.gob.sat.catalogo.service.api.unidadmedida;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.basems.utils.consts.GeneralConst;
import mx.gob.sat.catalogo.controller.request.unidadmedida.CatUnidadMedidaRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.unidadmedida.CatUnidadMedidaResponse;
import mx.gob.sat.catalogo.model.entity.CatUnidadMedida;
import mx.gob.sat.catalogo.repository.CatUnidadMedidaRepository;
import mx.gob.sat.catalogo.util.SortValidator;
import mx.gob.sat.catalogo.util.consts.CodigoErrorConst;
import mx.gob.sat.catalogo.util.mapper.CatUnidadMedidaMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * <b>Class:</b> CatUnidadMedidaAPIServiceImpl.java <br>
 * <b>Description:</b>
 * <p>Implementacion del servicio API para el catalogo de unidades de medida.</p>
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
class CatUnidadMedidaAPIServiceImpl implements CatUnidadMedidaAPIService {

    /** Columnas permitidas para ordenamiento. */
    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of(
            "cveUnidadMedida", "cveUnidadMedida",
            "descripcion", "descripcion",
            "sigla", "sigla"
    );

    /** Columna de ordenamiento predeterminada. */
    private static final String DEFAULT_SORT = "cveUnidadMedida";

    private final CatUnidadMedidaRepository unidadMedidaRepository;
    private final CatUnidadMedidaMapper unidadMedidaMapper;

    /**
     * Lista las unidades de medida de forma paginada con filtro de texto.
     *
     * @param pagina Numero de pagina.
     * @param tamano Tamano de pagina.
     * @param busqueda Texto de busqueda.
     * @param sortBy Columna de ordenamiento.
     * @param sortDir Direccion de ordenamiento.
     * @return Pagina de unidades de medida en BaseResponse.
     */
    @Override
    public BaseResponse<PaginaResponse<CatUnidadMedidaResponse>> listar(
            final int pagina, final int tamano,
            final String busqueda, final String sortBy, final String sortDir) {

        final Sort sort = SortValidator.buildSort(sortBy, sortDir, ALLOWED_SORT_COLUMNS);
        final Pageable pageable = sort.isSorted()
                ? PageRequest.of(pagina, tamano, sort)
                : PageRequest.of(pagina, tamano, Sort.by(Sort.Direction.ASC, DEFAULT_SORT));

        final Page<CatUnidadMedida> paginaResultado = (busqueda == null || busqueda.isBlank())
                ? unidadMedidaRepository.findAll(pageable)
                : unidadMedidaRepository
                        .findByDescripcionContainingIgnoreCaseOrCveUnidadMedidaContainingIgnoreCaseOrSiglaContainingIgnoreCase(
                                busqueda, busqueda, busqueda, pageable);

        final List<CatUnidadMedidaResponse> contenido =
                unidadMedidaMapper.toResponseList(paginaResultado.getContent());
        return exito(PaginaResponse.<CatUnidadMedidaResponse>builder()
                .contenido(contenido)
                .pagina(paginaResultado.getNumber())
                .tamano(paginaResultado.getSize())
                .total(paginaResultado.getTotalElements())
                .totalPaginas(paginaResultado.getTotalPages())
                .ultima(paginaResultado.isLast())
                .build());
    }

    /**
     * Busca una unidad de medida por su clave.
     *
     * @param cveUnidadMedida Clave de la unidad de medida.
     * @return Unidad de medida en BaseResponse.
     */
    @Override
    public BaseResponse<CatUnidadMedidaResponse> findById(final String cveUnidadMedida) {
        final Optional<CatUnidadMedida> resultado = unidadMedidaRepository.findById(cveUnidadMedida);
        if (resultado.isEmpty()) {
            return error(CodigoErrorConst.UNIDAD_MEDIDA_NO_ENCONTRADA,
                    "Unidad de medida no encontrada: " + cveUnidadMedida);
        }
        return exito(unidadMedidaMapper.toResponse(resultado.get()));
    }

    /**
     * Crea una nueva unidad de medida.
     *
     * @param request Datos de la unidad de medida.
     * @return Unidad de medida creada en BaseResponse.
     */
    @Override
    public BaseResponse<CatUnidadMedidaResponse> crear(final CatUnidadMedidaRequest request) {
        final CatUnidadMedida guardada = unidadMedidaRepository.save(unidadMedidaMapper.toEntity(request));
        log.info("Unidad de medida creada: {}", guardada.getCveUnidadMedida());
        return exito(unidadMedidaMapper.toResponse(guardada));
    }

    /**
     * Actualiza los campos no nulos de una unidad de medida existente.
     *
     * @param cveUnidadMedida Clave de la unidad de medida.
     * @param request Datos de actualizacion.
     * @return Unidad de medida actualizada en BaseResponse.
     */
    @Override
    public BaseResponse<CatUnidadMedidaResponse> actualizar(
            final String cveUnidadMedida, final CatUnidadMedidaRequest request) {

        final Optional<CatUnidadMedida> opt = unidadMedidaRepository.findById(cveUnidadMedida);
        if (opt.isEmpty()) {
            return error(CodigoErrorConst.UNIDAD_MEDIDA_NO_ENCONTRADA,
                    "Unidad de medida no encontrada: " + cveUnidadMedida);
        }
        final CatUnidadMedida entidad = opt.get();
        if (request.getDescripcion() != null) {
            entidad.setDescripcion(request.getDescripcion());
        }
        if (request.getFecCaptura() != null) {
            entidad.setFecCaptura(request.getFecCaptura());
        }
        if (request.getFecFinVigencia() != null) {
            entidad.setFecFinVigencia(request.getFecFinVigencia());
        }
        if (request.getFecIniVigencia() != null) {
            entidad.setFecIniVigencia(request.getFecIniVigencia());
        }
        if (request.getBlnActivo() != null) {
            entidad.setBlnActivo(request.getBlnActivo());
        }
        if (request.getIdeOrigenUnidadMedida() != null) {
            entidad.setIdeOrigenUnidadMedida(request.getIdeOrigenUnidadMedida());
        }
        if (request.getSigla() != null) {
            entidad.setSigla(request.getSigla());
        }
        if (request.getIdOma() != null) {
            entidad.setIdOma(request.getIdOma());
        }
        if (request.getSiglaCbp() != null) {
            entidad.setSiglaCbp(request.getSiglaCbp());
        }
        return exito(unidadMedidaMapper.toResponse(unidadMedidaRepository.save(entidad)));
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
