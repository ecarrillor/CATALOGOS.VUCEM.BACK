package mx.gob.sat.catalogo.service.api.criteriodictaminacion;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.basems.utils.consts.GeneralConst;
import mx.gob.sat.catalogo.controller.request.criteriodictaminacion.CatCriterioDictaminacionRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.criteriodictaminacion.CatCriterioDictaminacionResponse;
import mx.gob.sat.catalogo.model.entity.CatCriterioDictaminacion;
import mx.gob.sat.catalogo.repository.CatCriterioDictaminacionRepository;
import mx.gob.sat.catalogo.util.SortValidator;
import mx.gob.sat.catalogo.util.consts.CodigoErrorConst;
import mx.gob.sat.catalogo.util.mapper.CatCriterioDictaminacionMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * <b>Class:</b> CatCriterioDictaminacionAPIServiceImpl.java <br>
 * <b>Description:</b>
 * <p>Implementacion del servicio API para el catalogo de criterios de dictaminacion.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Implementacion del servicio API
 */
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
class CatCriterioDictaminacionAPIServiceImpl implements CatCriterioDictaminacionAPIService {

    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of(
            "idCriterioDictaminacion", "idCriterioDictaminacion", "nombre", "nombre");
    private static final String DEFAULT_SORT = "idCriterioDictaminacion";
    private final CatCriterioDictaminacionRepository criterioDictaminacionRepository;
    private final CatCriterioDictaminacionMapper criterioDictaminacionMapper;

    @Override
    public BaseResponse<PaginaResponse<CatCriterioDictaminacionResponse>> listar(
            final int pagina, final int tamano, final String busqueda, final String sortBy, final String sortDir) {
        final Sort sort = SortValidator.buildSort(sortBy, sortDir, ALLOWED_SORT_COLUMNS);
        final Pageable pageable = sort.isSorted()
                ? PageRequest.of(pagina, tamano, sort)
                : PageRequest.of(pagina, tamano, Sort.by(Sort.Direction.ASC, DEFAULT_SORT));
        final Page<CatCriterioDictaminacion> paginaResultado = (busqueda == null || busqueda.isBlank())
                ? criterioDictaminacionRepository.findAll(pageable)
                : criterioDictaminacionRepository.findByNombreContainingIgnoreCase(busqueda, pageable);
        final List<CatCriterioDictaminacionResponse> contenido = criterioDictaminacionMapper.toResponseList(paginaResultado.getContent());
        return exito(PaginaResponse.<CatCriterioDictaminacionResponse>builder()
                .contenido(contenido).pagina(paginaResultado.getNumber()).tamano(paginaResultado.getSize())
                .total(paginaResultado.getTotalElements()).totalPaginas(paginaResultado.getTotalPages())
                .ultima(paginaResultado.isLast()).build());
    }

    @Override
    public BaseResponse<CatCriterioDictaminacionResponse> findById(final Short idCriterioDictaminacion) {
        final Optional<CatCriterioDictaminacion> resultado = criterioDictaminacionRepository.findById(idCriterioDictaminacion);
        if (resultado.isEmpty()) {
            return error(CodigoErrorConst.CRITERIO_DICTAMINACION_NO_ENCONTRADO, "Criterio dictaminacion no encontrado: " + idCriterioDictaminacion);
        }
        return exito(criterioDictaminacionMapper.toResponse(resultado.get()));
    }

    @Override
    public BaseResponse<CatCriterioDictaminacionResponse> crear(final CatCriterioDictaminacionRequest request) {
        final CatCriterioDictaminacion guardado = criterioDictaminacionRepository.save(criterioDictaminacionMapper.toEntity(request));
        log.info("Criterio dictaminacion creado: {}", guardado.getIdCriterioDictaminacion());
        return exito(criterioDictaminacionMapper.toResponse(guardado));
    }

    @Override
    public BaseResponse<CatCriterioDictaminacionResponse> actualizar(final Short idCriterioDictaminacion, final CatCriterioDictaminacionRequest request) {
        final Optional<CatCriterioDictaminacion> opt = criterioDictaminacionRepository.findById(idCriterioDictaminacion);
        if (opt.isEmpty()) {
            return error(CodigoErrorConst.CRITERIO_DICTAMINACION_NO_ENCONTRADO, "Criterio dictaminacion no encontrado: " + idCriterioDictaminacion);
        }
        final CatCriterioDictaminacion entidad = opt.get();
        if (request.getNombre() != null) { entidad.setNombre(request.getNombre()); }
        if (request.getFecIniVigencia() != null) { entidad.setFecIniVigencia(request.getFecIniVigencia()); }
        if (request.getFecFinVigencia() != null) { entidad.setFecFinVigencia(request.getFecFinVigencia()); }
        if (request.getBlnActivo() != null) { entidad.setBlnActivo(request.getBlnActivo()); }
        return exito(criterioDictaminacionMapper.toResponse(criterioDictaminacionRepository.save(entidad)));
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
