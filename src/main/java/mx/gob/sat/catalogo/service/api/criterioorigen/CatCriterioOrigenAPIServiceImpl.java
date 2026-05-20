package mx.gob.sat.catalogo.service.api.criterioorigen;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.basems.utils.consts.GeneralConst;
import mx.gob.sat.catalogo.controller.request.criterioorigen.CatCriterioOrigenRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.criterioorigen.CatCriterioOrigenResponse;
import mx.gob.sat.catalogo.model.entity.CatCriterioOrigen;
import mx.gob.sat.catalogo.repository.CatCriterioOrigenRepository;
import mx.gob.sat.catalogo.util.SortValidator;
import mx.gob.sat.catalogo.util.consts.CodigoErrorConst;
import mx.gob.sat.catalogo.util.mapper.CatCriterioOrigenMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * <b>Class:</b> CatCriterioOrigenAPIServiceImpl.java <br>
 * <b>Description:</b>
 * <p>Implementacion del servicio API para el catalogo de criterios de origen.</p>
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
class CatCriterioOrigenAPIServiceImpl implements CatCriterioOrigenAPIService {

    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of(
            "cveCriterioOrigen", "cveCriterioOrigen", "nombre", "nombre");
    private static final String DEFAULT_SORT = "cveCriterioOrigen";
    private final CatCriterioOrigenRepository criterioOrigenRepository;
    private final CatCriterioOrigenMapper criterioOrigenMapper;

    @Override
    public BaseResponse<PaginaResponse<CatCriterioOrigenResponse>> listar(
            final int pagina, final int tamano, final String busqueda, final String sortBy, final String sortDir) {
        final Sort sort = SortValidator.buildSort(sortBy, sortDir, ALLOWED_SORT_COLUMNS);
        final Pageable pageable = sort.isSorted()
                ? PageRequest.of(pagina, tamano, sort)
                : PageRequest.of(pagina, tamano, Sort.by(Sort.Direction.ASC, DEFAULT_SORT));
        final Page<CatCriterioOrigen> paginaResultado = (busqueda == null || busqueda.isBlank())
                ? criterioOrigenRepository.findAll(pageable)
                : criterioOrigenRepository.findByNombreContainingIgnoreCaseOrCveCriterioOrigenContainingIgnoreCase(
                        busqueda, busqueda, pageable);
        final List<CatCriterioOrigenResponse> contenido = criterioOrigenMapper.toResponseList(paginaResultado.getContent());
        return exito(PaginaResponse.<CatCriterioOrigenResponse>builder()
                .contenido(contenido).pagina(paginaResultado.getNumber()).tamano(paginaResultado.getSize())
                .total(paginaResultado.getTotalElements()).totalPaginas(paginaResultado.getTotalPages())
                .ultima(paginaResultado.isLast()).build());
    }

    @Override
    public BaseResponse<CatCriterioOrigenResponse> findById(final String cveCriterioOrigen) {
        final Optional<CatCriterioOrigen> resultado = criterioOrigenRepository.findById(cveCriterioOrigen);
        if (resultado.isEmpty()) {
            return error(CodigoErrorConst.CRITERIO_ORIGEN_NO_ENCONTRADO, "Criterio origen no encontrado: " + cveCriterioOrigen);
        }
        return exito(criterioOrigenMapper.toResponse(resultado.get()));
    }

    @Override
    public BaseResponse<CatCriterioOrigenResponse> crear(final CatCriterioOrigenRequest request) {
        final CatCriterioOrigen guardado = criterioOrigenRepository.save(criterioOrigenMapper.toEntity(request));
        log.info("Criterio origen creado: {}", guardado.getCveCriterioOrigen());
        return exito(criterioOrigenMapper.toResponse(guardado));
    }

    @Override
    public BaseResponse<CatCriterioOrigenResponse> actualizar(final String cveCriterioOrigen, final CatCriterioOrigenRequest request) {
        final Optional<CatCriterioOrigen> opt = criterioOrigenRepository.findById(cveCriterioOrigen);
        if (opt.isEmpty()) {
            return error(CodigoErrorConst.CRITERIO_ORIGEN_NO_ENCONTRADO, "Criterio origen no encontrado: " + cveCriterioOrigen);
        }
        final CatCriterioOrigen entidad = opt.get();
        if (request.getNombre() != null) { entidad.setNombre(request.getNombre()); }
        if (request.getFecIniVigencia() != null) { entidad.setFecIniVigencia(request.getFecIniVigencia()); }
        if (request.getFecFinVigencia() != null) { entidad.setFecFinVigencia(request.getFecFinVigencia()); }
        if (request.getBlnActivo() != null) { entidad.setBlnActivo(request.getBlnActivo()); }
        return exito(criterioOrigenMapper.toResponse(criterioOrigenRepository.save(entidad)));
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
