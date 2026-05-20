package mx.gob.sat.catalogo.service.api.semanalaboral;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.basems.utils.consts.GeneralConst;
import mx.gob.sat.catalogo.controller.request.semanalaboral.CatSemanaLaboralRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.semanalaboral.CatSemanaLaboralResponse;
import mx.gob.sat.catalogo.model.entity.CatSemanaLaboral;
import mx.gob.sat.catalogo.repository.CatSemanaLaboralRepository;
import mx.gob.sat.catalogo.util.SortValidator;
import mx.gob.sat.catalogo.util.consts.CodigoErrorConst;
import mx.gob.sat.catalogo.util.mapper.CatSemanaLaboralMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * <b>Class:</b> CatSemanaLaboralAPIServiceImpl.java <br>
 * <b>Description:</b>
 * <p>Implementacion del servicio API para el catalogo de semanas laborales.</p>
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
class CatSemanaLaboralAPIServiceImpl implements CatSemanaLaboralAPIService {

    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of(
            "idSemanaLaboral", "idSemanaLaboral", "descripcion", "descripcion");
    private static final String DEFAULT_SORT = "idSemanaLaboral";
    private final CatSemanaLaboralRepository semanaLaboralRepository;
    private final CatSemanaLaboralMapper semanaLaboralMapper;

    @Override
    public BaseResponse<PaginaResponse<CatSemanaLaboralResponse>> listar(
            final int pagina, final int tamano, final String busqueda, final String sortBy, final String sortDir) {
        final Sort sort = SortValidator.buildSort(sortBy, sortDir, ALLOWED_SORT_COLUMNS);
        final Pageable pageable = sort.isSorted()
                ? PageRequest.of(pagina, tamano, sort)
                : PageRequest.of(pagina, tamano, Sort.by(Sort.Direction.ASC, DEFAULT_SORT));
        final Page<CatSemanaLaboral> paginaResultado = (busqueda == null || busqueda.isBlank())
                ? semanaLaboralRepository.findAll(pageable)
                : semanaLaboralRepository.findByDescripcionContainingIgnoreCase(busqueda, pageable);
        final List<CatSemanaLaboralResponse> contenido = semanaLaboralMapper.toResponseList(paginaResultado.getContent());
        return exito(PaginaResponse.<CatSemanaLaboralResponse>builder()
                .contenido(contenido).pagina(paginaResultado.getNumber()).tamano(paginaResultado.getSize())
                .total(paginaResultado.getTotalElements()).totalPaginas(paginaResultado.getTotalPages())
                .ultima(paginaResultado.isLast()).build());
    }

    @Override
    public BaseResponse<CatSemanaLaboralResponse> findById(final Short idSemanaLaboral) {
        final Optional<CatSemanaLaboral> resultado = semanaLaboralRepository.findById(idSemanaLaboral.intValue());
        if (resultado.isEmpty()) {
            return error(CodigoErrorConst.SEMANA_LABORAL_NO_ENCONTRADA, "Semana laboral no encontrada: " + idSemanaLaboral);
        }
        return exito(semanaLaboralMapper.toResponse(resultado.get()));
    }

    @Override
    public BaseResponse<CatSemanaLaboralResponse> crear(final CatSemanaLaboralRequest request) {
        final CatSemanaLaboral guardada = semanaLaboralRepository.save(semanaLaboralMapper.toEntity(request));
        log.info("Semana laboral creada: {}", guardada.getIdSemanaLaboral());
        return exito(semanaLaboralMapper.toResponse(guardada));
    }

    @Override
    public BaseResponse<CatSemanaLaboralResponse> actualizar(final Short idSemanaLaboral, final CatSemanaLaboralRequest request) {
        final Optional<CatSemanaLaboral> opt = semanaLaboralRepository.findById(idSemanaLaboral.intValue());
        if (opt.isEmpty()) {
            return error(CodigoErrorConst.SEMANA_LABORAL_NO_ENCONTRADA, "Semana laboral no encontrada: " + idSemanaLaboral);
        }
        final CatSemanaLaboral entidad = opt.get();
        if (request.getDescripcion() != null) { entidad.setDescripcion(request.getDescripcion()); }
        if (request.getBlnActivo() != null) { entidad.setBlnActivo(request.getBlnActivo()); }
        return exito(semanaLaboralMapper.toResponse(semanaLaboralRepository.save(entidad)));
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
