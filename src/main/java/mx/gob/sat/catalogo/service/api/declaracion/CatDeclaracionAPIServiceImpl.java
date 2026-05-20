package mx.gob.sat.catalogo.service.api.declaracion;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.basems.utils.consts.GeneralConst;
import mx.gob.sat.catalogo.controller.request.declaracion.CatDeclaracionRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.declaracion.CatDeclaracionResponse;
import mx.gob.sat.catalogo.model.entity.CatDeclaracion;
import mx.gob.sat.catalogo.repository.CatDeclaracionRepository;
import mx.gob.sat.catalogo.util.SortValidator;
import mx.gob.sat.catalogo.util.consts.CodigoErrorConst;
import mx.gob.sat.catalogo.util.mapper.CatDeclaracionMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * <b>Class:</b> CatDeclaracionAPIServiceImpl.java <br>
 * <b>Description:</b>
 * <p>Implementacion del servicio API para el catalogo de declaraciones.</p>
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
class CatDeclaracionAPIServiceImpl implements CatDeclaracionAPIService {

    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of(
            "cveDeclaracion", "cveDeclaracion", "descDeclaracion", "descDeclaracion");
    private static final String DEFAULT_SORT = "cveDeclaracion";
    private final CatDeclaracionRepository declaracionRepository;
    private final CatDeclaracionMapper declaracionMapper;

    @Override
    public BaseResponse<PaginaResponse<CatDeclaracionResponse>> listar(
            final int pagina, final int tamano, final String busqueda, final String sortBy, final String sortDir) {
        final Sort sort = SortValidator.buildSort(sortBy, sortDir, ALLOWED_SORT_COLUMNS);
        final Pageable pageable = sort.isSorted()
                ? PageRequest.of(pagina, tamano, sort)
                : PageRequest.of(pagina, tamano, Sort.by(Sort.Direction.ASC, DEFAULT_SORT));
        final Page<CatDeclaracion> paginaResultado = (busqueda == null || busqueda.isBlank())
                ? declaracionRepository.findAll(pageable)
                : declaracionRepository.findByDescDeclaracionContainingIgnoreCaseOrCveDeclaracionContainingIgnoreCase(
                        busqueda, busqueda, pageable);
        final List<CatDeclaracionResponse> contenido = declaracionMapper.toResponseList(paginaResultado.getContent());
        return exito(PaginaResponse.<CatDeclaracionResponse>builder()
                .contenido(contenido).pagina(paginaResultado.getNumber()).tamano(paginaResultado.getSize())
                .total(paginaResultado.getTotalElements()).totalPaginas(paginaResultado.getTotalPages())
                .ultima(paginaResultado.isLast()).build());
    }

    @Override
    public BaseResponse<CatDeclaracionResponse> findById(final String cveDeclaracion) {
        final Optional<CatDeclaracion> resultado = declaracionRepository.findById(cveDeclaracion);
        if (resultado.isEmpty()) {
            return error(CodigoErrorConst.DECLARACION_NO_ENCONTRADA, "Declaracion no encontrada: " + cveDeclaracion);
        }
        return exito(declaracionMapper.toResponse(resultado.get()));
    }

    @Override
    public BaseResponse<CatDeclaracionResponse> crear(final CatDeclaracionRequest request) {
        final CatDeclaracion guardado = declaracionRepository.save(declaracionMapper.toEntity(request));
        log.info("Declaracion creada: {}", guardado.getCveDeclaracion());
        return exito(declaracionMapper.toResponse(guardado));
    }

    @Override
    public BaseResponse<CatDeclaracionResponse> actualizar(final String cveDeclaracion, final CatDeclaracionRequest request) {
        final Optional<CatDeclaracion> opt = declaracionRepository.findById(cveDeclaracion);
        if (opt.isEmpty()) {
            return error(CodigoErrorConst.DECLARACION_NO_ENCONTRADA, "Declaracion no encontrada: " + cveDeclaracion);
        }
        final CatDeclaracion entidad = opt.get();
        if (request.getDescDeclaracion() != null) { entidad.setDescDeclaracion(request.getDescDeclaracion()); }
        if (request.getCveReferencia() != null) { entidad.setCveReferencia(request.getCveReferencia()); }
        if (request.getFecCaptura() != null) { entidad.setFecCaptura(request.getFecCaptura()); }
        if (request.getFecIniVigencia() != null) { entidad.setFecIniVigencia(request.getFecIniVigencia()); }
        if (request.getFecFinVigencia() != null) { entidad.setFecFinVigencia(request.getFecFinVigencia()); }
        if (request.getBlnActivo() != null) { entidad.setBlnActivo(request.getBlnActivo()); }
        return exito(declaracionMapper.toResponse(declaracionRepository.save(entidad)));
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
