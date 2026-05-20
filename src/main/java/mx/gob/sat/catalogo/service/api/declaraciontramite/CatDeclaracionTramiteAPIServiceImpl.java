package mx.gob.sat.catalogo.service.api.declaraciontramite;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.basems.utils.consts.GeneralConst;
import mx.gob.sat.catalogo.controller.request.declaraciontramite.CatDeclaracionTramiteRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.declaraciontramite.CatDeclaracionTramiteResponse;
import mx.gob.sat.catalogo.model.entity.CatDeclaracionTramite;
import mx.gob.sat.catalogo.model.entity.CatDeclaracionTramiteId;
import mx.gob.sat.catalogo.repository.CatDeclaracionTramiteRepository;
import mx.gob.sat.catalogo.util.SortValidator;
import mx.gob.sat.catalogo.util.consts.CodigoErrorConst;
import mx.gob.sat.catalogo.util.mapper.CatDeclaracionTramiteMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
class CatDeclaracionTramiteAPIServiceImpl implements CatDeclaracionTramiteAPIService {

    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of("cveDeclaracion", "id.cveDeclaracion");
    private static final String DEFAULT_SORT = "id.cveDeclaracion";

    private final CatDeclaracionTramiteRepository declaracionTramiteRepository;
    private final CatDeclaracionTramiteMapper declaracionTramiteMapper;

    @Override
    public BaseResponse<PaginaResponse<CatDeclaracionTramiteResponse>> listar(final int pagina, final int tamano, final String busqueda, final String sortBy, final String sortDir) {
        final Sort sort = SortValidator.buildSort(sortBy, sortDir, ALLOWED_SORT_COLUMNS);
        final Pageable pageable = sort.isSorted() ? PageRequest.of(pagina, tamano, sort) : PageRequest.of(pagina, tamano, Sort.by(Sort.Direction.ASC, DEFAULT_SORT));
        final Page<CatDeclaracionTramite> paginaResultado = declaracionTramiteRepository.findAll(pageable);
        final List<CatDeclaracionTramiteResponse> contenido = declaracionTramiteMapper.toResponseList(paginaResultado.getContent());
        return exito(PaginaResponse.<CatDeclaracionTramiteResponse>builder()
                .contenido(contenido).pagina(paginaResultado.getNumber()).tamano(paginaResultado.getSize())
                .total(paginaResultado.getTotalElements()).totalPaginas(paginaResultado.getTotalPages())
                .ultima(paginaResultado.isLast()).build());
    }

    @Override
    public BaseResponse<CatDeclaracionTramiteResponse> findById(final String cveDeclaracion, final Long idTipoTramite) {
        final CatDeclaracionTramiteId id = new CatDeclaracionTramiteId();
        id.setCveDeclaracion(cveDeclaracion);
        id.setIdTipoTramite(idTipoTramite);
        final Optional<CatDeclaracionTramite> resultado = declaracionTramiteRepository.findById(id);
        if (resultado.isEmpty()) {
            return error(CodigoErrorConst.DECLARACION_TRAMITE_NO_ENCONTRADO, "Declaracion Tramite no encontrado: " + cveDeclaracion + "/" + idTipoTramite);
        }
        return exito(declaracionTramiteMapper.toResponse(resultado.get()));
    }

    @Override
    public BaseResponse<CatDeclaracionTramiteResponse> crear(final CatDeclaracionTramiteRequest request) {
        final CatDeclaracionTramite entidad = new CatDeclaracionTramite();
        final CatDeclaracionTramiteId id = new CatDeclaracionTramiteId();
        id.setCveDeclaracion(request.getCveDeclaracion());
        id.setIdTipoTramite(request.getIdTipoTramite());
        entidad.setId(id);
        entidad.setFecIniVigencia(request.getFecIniVigencia());
        entidad.setFecFinVigencia(request.getFecFinVigencia());
        entidad.setBlnActivo(request.getBlnActivo());
        final CatDeclaracionTramite guardado = declaracionTramiteRepository.save(entidad);
        log.info("Declaracion Tramite creado: {}/{}", guardado.getId().getCveDeclaracion(), guardado.getId().getIdTipoTramite());
        return exito(declaracionTramiteMapper.toResponse(guardado));
    }

    @Override
    public BaseResponse<CatDeclaracionTramiteResponse> actualizar(final String cveDeclaracion, final Long idTipoTramite, final CatDeclaracionTramiteRequest request) {
        final CatDeclaracionTramiteId id = new CatDeclaracionTramiteId();
        id.setCveDeclaracion(cveDeclaracion);
        id.setIdTipoTramite(idTipoTramite);
        final Optional<CatDeclaracionTramite> opt = declaracionTramiteRepository.findById(id);
        if (opt.isEmpty()) {
            return error(CodigoErrorConst.DECLARACION_TRAMITE_NO_ENCONTRADO, "Declaracion Tramite no encontrado: " + cveDeclaracion + "/" + idTipoTramite);
        }
        final CatDeclaracionTramite entidad = opt.get();
        if (request.getFecIniVigencia() != null) { entidad.setFecIniVigencia(request.getFecIniVigencia()); }
        if (request.getFecFinVigencia() != null) { entidad.setFecFinVigencia(request.getFecFinVigencia()); }
        if (request.getBlnActivo() != null) { entidad.setBlnActivo(request.getBlnActivo()); }
        return exito(declaracionTramiteMapper.toResponse(declaracionTramiteRepository.save(entidad)));
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
