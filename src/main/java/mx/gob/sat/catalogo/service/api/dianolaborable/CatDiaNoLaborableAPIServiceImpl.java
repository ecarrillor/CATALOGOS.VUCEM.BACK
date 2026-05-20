package mx.gob.sat.catalogo.service.api.dianolaborable;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.basems.utils.consts.GeneralConst;
import mx.gob.sat.catalogo.controller.request.dianolaborable.CatDiaNoLaborableRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.dianolaborable.CatDiaNoLaborableResponse;
import mx.gob.sat.catalogo.model.entity.CatDiaNoLaborable;
import mx.gob.sat.catalogo.model.entity.CatDiaNoLaborableId;
import mx.gob.sat.catalogo.repository.CatDiaNoLaborableRepository;
import mx.gob.sat.catalogo.util.SortValidator;
import mx.gob.sat.catalogo.util.consts.CodigoErrorConst;
import mx.gob.sat.catalogo.util.mapper.CatDiaNoLaborableMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
class CatDiaNoLaborableAPIServiceImpl implements CatDiaNoLaborableAPIService {

    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of("fecNoLaborable", "id.fecNoLaborable");
    private static final String DEFAULT_SORT = "id.fecNoLaborable";

    private final CatDiaNoLaborableRepository diaNoLaborableRepository;
    private final CatDiaNoLaborableMapper diaNoLaborableMapper;

    @Override
    public BaseResponse<PaginaResponse<CatDiaNoLaborableResponse>> listar(final int pagina, final int tamano, final String busqueda, final String sortBy, final String sortDir) {
        final Sort sort = SortValidator.buildSort(sortBy, sortDir, ALLOWED_SORT_COLUMNS);
        final Pageable pageable = sort.isSorted() ? PageRequest.of(pagina, tamano, sort) : PageRequest.of(pagina, tamano, Sort.by(Sort.Direction.ASC, DEFAULT_SORT));
        final Page<CatDiaNoLaborable> paginaResultado = diaNoLaborableRepository.findAll(pageable);
        final List<CatDiaNoLaborableResponse> contenido = diaNoLaborableMapper.toResponseList(paginaResultado.getContent());
        return exito(PaginaResponse.<CatDiaNoLaborableResponse>builder()
                .contenido(contenido).pagina(paginaResultado.getNumber()).tamano(paginaResultado.getSize())
                .total(paginaResultado.getTotalElements()).totalPaginas(paginaResultado.getTotalPages())
                .ultima(paginaResultado.isLast()).build());
    }

    @Override
    public BaseResponse<CatDiaNoLaborableResponse> findById(final LocalDate fecNoLaborable, final String cveCalendario) {
        final CatDiaNoLaborableId id = new CatDiaNoLaborableId();
        id.setFecNoLaborable(fecNoLaborable);
        id.setCveCalendario(cveCalendario);
        final Optional<CatDiaNoLaborable> resultado = diaNoLaborableRepository.findById(id);
        if (resultado.isEmpty()) {
            return error(CodigoErrorConst.DIA_NO_LABORABLE_NO_ENCONTRADO, "Dia No Laborable no encontrado: " + fecNoLaborable + "/" + cveCalendario);
        }
        return exito(diaNoLaborableMapper.toResponse(resultado.get()));
    }

    @Override
    public BaseResponse<CatDiaNoLaborableResponse> crear(final CatDiaNoLaborableRequest request) {
        final CatDiaNoLaborable entidad = new CatDiaNoLaborable();
        final CatDiaNoLaborableId id = new CatDiaNoLaborableId();
        id.setFecNoLaborable(request.getFecNoLaborable());
        id.setCveCalendario(request.getCveCalendario());
        entidad.setId(id);
        entidad.setDescripcion(request.getDescripcion());
        entidad.setFecIniVigencia(request.getFecIniVigencia());
        entidad.setFecFinVigencia(request.getFecFinVigencia());
        entidad.setBlnActivo(request.getBlnActivo());
        final CatDiaNoLaborable guardado = diaNoLaborableRepository.save(entidad);
        log.info("Dia No Laborable creado: {}/{}", guardado.getId().getFecNoLaborable(), guardado.getId().getCveCalendario());
        return exito(diaNoLaborableMapper.toResponse(guardado));
    }

    @Override
    public BaseResponse<CatDiaNoLaborableResponse> actualizar(final LocalDate fecNoLaborable, final String cveCalendario, final CatDiaNoLaborableRequest request) {
        final CatDiaNoLaborableId id = new CatDiaNoLaborableId();
        id.setFecNoLaborable(fecNoLaborable);
        id.setCveCalendario(cveCalendario);
        final Optional<CatDiaNoLaborable> opt = diaNoLaborableRepository.findById(id);
        if (opt.isEmpty()) {
            return error(CodigoErrorConst.DIA_NO_LABORABLE_NO_ENCONTRADO, "Dia No Laborable no encontrado: " + fecNoLaborable + "/" + cveCalendario);
        }
        final CatDiaNoLaborable entidad = opt.get();
        if (request.getDescripcion() != null) { entidad.setDescripcion(request.getDescripcion()); }
        if (request.getFecIniVigencia() != null) { entidad.setFecIniVigencia(request.getFecIniVigencia()); }
        if (request.getFecFinVigencia() != null) { entidad.setFecFinVigencia(request.getFecFinVigencia()); }
        if (request.getBlnActivo() != null) { entidad.setBlnActivo(request.getBlnActivo()); }
        return exito(diaNoLaborableMapper.toResponse(diaNoLaborableRepository.save(entidad)));
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
