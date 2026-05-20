package mx.gob.sat.catalogo.service.api.dictamentramite;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.basems.utils.consts.GeneralConst;
import mx.gob.sat.catalogo.controller.request.dictamentramite.CatDictamenTramiteRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.dictamentramite.CatDictamenTramiteResponse;
import mx.gob.sat.catalogo.model.entity.CatDictamenTramite;
import mx.gob.sat.catalogo.model.entity.CatDictamenTramiteId;
import mx.gob.sat.catalogo.repository.CatDictamenTramiteRepository;
import mx.gob.sat.catalogo.util.SortValidator;
import mx.gob.sat.catalogo.util.consts.CodigoErrorConst;
import mx.gob.sat.catalogo.util.mapper.CatDictamenTramiteMapper;
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
class CatDictamenTramiteAPIServiceImpl implements CatDictamenTramiteAPIService {

    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of("idTipoTramite", "id.idTipoTramite");
    private static final String DEFAULT_SORT = "id.idTipoTramite";

    private final CatDictamenTramiteRepository dictamenTramiteRepository;
    private final CatDictamenTramiteMapper dictamenTramiteMapper;

    @Override
    public BaseResponse<PaginaResponse<CatDictamenTramiteResponse>> listar(final int pagina, final int tamano, final String busqueda, final String sortBy, final String sortDir) {
        final Sort sort = SortValidator.buildSort(sortBy, sortDir, ALLOWED_SORT_COLUMNS);
        final Pageable pageable = sort.isSorted() ? PageRequest.of(pagina, tamano, sort) : PageRequest.of(pagina, tamano, Sort.by(Sort.Direction.ASC, DEFAULT_SORT));
        final Page<CatDictamenTramite> paginaResultado = dictamenTramiteRepository.findAll(pageable);
        final List<CatDictamenTramiteResponse> contenido = dictamenTramiteMapper.toResponseList(paginaResultado.getContent());
        return exito(PaginaResponse.<CatDictamenTramiteResponse>builder()
                .contenido(contenido).pagina(paginaResultado.getNumber()).tamano(paginaResultado.getSize())
                .total(paginaResultado.getTotalElements()).totalPaginas(paginaResultado.getTotalPages())
                .ultima(paginaResultado.isLast()).build());
    }

    @Override
    public BaseResponse<CatDictamenTramiteResponse> findById(final Long idTipoTramite, final Long idTipoDictamen) {
        final CatDictamenTramiteId id = new CatDictamenTramiteId();
        id.setIdTipoTramite(idTipoTramite);
        id.setIdTipoDictamen(idTipoDictamen);
        final Optional<CatDictamenTramite> resultado = dictamenTramiteRepository.findById(id);
        if (resultado.isEmpty()) {
            return error(CodigoErrorConst.DICTAMEN_TRAMITE_NO_ENCONTRADO, "Dictamen Tramite no encontrado: " + idTipoTramite + "/" + idTipoDictamen);
        }
        return exito(dictamenTramiteMapper.toResponse(resultado.get()));
    }

    @Override
    public BaseResponse<CatDictamenTramiteResponse> crear(final CatDictamenTramiteRequest request) {
        final CatDictamenTramite entidad = new CatDictamenTramite();
        final CatDictamenTramiteId id = new CatDictamenTramiteId();
        id.setIdTipoTramite(request.getIdTipoTramite());
        id.setIdTipoDictamen(request.getIdTipoDictamen());
        entidad.setId(id);
        entidad.setFecIniVigencia(request.getFecIniVigencia());
        entidad.setFecFinVigencia(request.getFecFinVigencia());
        entidad.setBlnActivo(request.getBlnActivo());
        final CatDictamenTramite guardado = dictamenTramiteRepository.save(entidad);
        log.info("Dictamen Tramite creado: {}/{}", guardado.getId().getIdTipoTramite(), guardado.getId().getIdTipoDictamen());
        return exito(dictamenTramiteMapper.toResponse(guardado));
    }

    @Override
    public BaseResponse<CatDictamenTramiteResponse> actualizar(final Long idTipoTramite, final Long idTipoDictamen, final CatDictamenTramiteRequest request) {
        final CatDictamenTramiteId id = new CatDictamenTramiteId();
        id.setIdTipoTramite(idTipoTramite);
        id.setIdTipoDictamen(idTipoDictamen);
        final Optional<CatDictamenTramite> opt = dictamenTramiteRepository.findById(id);
        if (opt.isEmpty()) {
            return error(CodigoErrorConst.DICTAMEN_TRAMITE_NO_ENCONTRADO, "Dictamen Tramite no encontrado: " + idTipoTramite + "/" + idTipoDictamen);
        }
        final CatDictamenTramite entidad = opt.get();
        if (request.getFecIniVigencia() != null) { entidad.setFecIniVigencia(request.getFecIniVigencia()); }
        if (request.getFecFinVigencia() != null) { entidad.setFecFinVigencia(request.getFecFinVigencia()); }
        if (request.getBlnActivo() != null) { entidad.setBlnActivo(request.getBlnActivo()); }
        return exito(dictamenTramiteMapper.toResponse(dictamenTramiteRepository.save(entidad)));
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
