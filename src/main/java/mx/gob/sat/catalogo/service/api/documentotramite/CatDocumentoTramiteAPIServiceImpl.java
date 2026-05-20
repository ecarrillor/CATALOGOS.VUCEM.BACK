package mx.gob.sat.catalogo.service.api.documentotramite;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.basems.utils.consts.GeneralConst;
import mx.gob.sat.catalogo.controller.request.documentotramite.CatDocumentoTramiteRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.documentotramite.CatDocumentoTramiteResponse;
import mx.gob.sat.catalogo.model.entity.CatDocumentoTramite;
import mx.gob.sat.catalogo.model.entity.CatDocumentoTramiteId;
import mx.gob.sat.catalogo.repository.CatDocumentoTramiteRepository;
import mx.gob.sat.catalogo.util.SortValidator;
import mx.gob.sat.catalogo.util.consts.CodigoErrorConst;
import mx.gob.sat.catalogo.util.mapper.CatDocumentoTramiteMapper;
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
class CatDocumentoTramiteAPIServiceImpl implements CatDocumentoTramiteAPIService {

    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of("idTipoTramite", "id.idTipoTramite");
    private static final String DEFAULT_SORT = "id.idTipoTramite";

    private final CatDocumentoTramiteRepository documentoTramiteRepository;
    private final CatDocumentoTramiteMapper documentoTramiteMapper;

    @Override
    public BaseResponse<PaginaResponse<CatDocumentoTramiteResponse>> listar(final int pagina, final int tamano, final String busqueda, final String sortBy, final String sortDir) {
        final Sort sort = SortValidator.buildSort(sortBy, sortDir, ALLOWED_SORT_COLUMNS);
        final Pageable pageable = sort.isSorted() ? PageRequest.of(pagina, tamano, sort) : PageRequest.of(pagina, tamano, Sort.by(Sort.Direction.ASC, DEFAULT_SORT));
        final Page<CatDocumentoTramite> paginaResultado = documentoTramiteRepository.findAll(pageable);
        final List<CatDocumentoTramiteResponse> contenido = documentoTramiteMapper.toResponseList(paginaResultado.getContent());
        return exito(PaginaResponse.<CatDocumentoTramiteResponse>builder()
                .contenido(contenido).pagina(paginaResultado.getNumber()).tamano(paginaResultado.getSize())
                .total(paginaResultado.getTotalElements()).totalPaginas(paginaResultado.getTotalPages())
                .ultima(paginaResultado.isLast()).build());
    }

    @Override
    public BaseResponse<CatDocumentoTramiteResponse> findById(final Short idTipoDoc, final Long idTipoTramite) {
        final CatDocumentoTramiteId id = new CatDocumentoTramiteId();
        id.setIdTipoDoc(idTipoDoc);
        id.setIdTipoTramite(idTipoTramite);
        final Optional<CatDocumentoTramite> resultado = documentoTramiteRepository.findById(id);
        if (resultado.isEmpty()) {
            return error(CodigoErrorConst.DOCUMENTO_TRAMITE_NO_ENCONTRADO, "Documento Tramite no encontrado: " + idTipoDoc + "/" + idTipoTramite);
        }
        return exito(documentoTramiteMapper.toResponse(resultado.get()));
    }

    @Override
    public BaseResponse<CatDocumentoTramiteResponse> crear(final CatDocumentoTramiteRequest request) {
        final CatDocumentoTramite entidad = new CatDocumentoTramite();
        final CatDocumentoTramiteId id = new CatDocumentoTramiteId();
        id.setIdTipoDoc(request.getIdTipoDoc());
        id.setIdTipoTramite(request.getIdTipoTramite());
        entidad.setId(id);
        entidad.setBlnEspecifico(request.getBlnEspecifico());
        entidad.setIdeClasificacionDocumento(request.getIdeClasificacionDocumento());
        entidad.setIdeTipoSolicitanteRfe(request.getIdeTipoSolicitanteRfe());
        entidad.setFecIniVigencia(request.getFecIniVigencia());
        entidad.setFecFinVigencia(request.getFecFinVigencia());
        entidad.setBlnActivo(request.getBlnActivo());
        entidad.setBlnSoloAnexar(request.getBlnSoloAnexar());
        entidad.setIdeReglaAnexado(request.getIdeReglaAnexado());
        final CatDocumentoTramite guardado = documentoTramiteRepository.save(entidad);
        log.info("Documento Tramite creado: {}/{}", guardado.getId().getIdTipoDoc(), guardado.getId().getIdTipoTramite());
        return exito(documentoTramiteMapper.toResponse(guardado));
    }

    @Override
    public BaseResponse<CatDocumentoTramiteResponse> actualizar(final Short idTipoDoc, final Long idTipoTramite, final CatDocumentoTramiteRequest request) {
        final CatDocumentoTramiteId id = new CatDocumentoTramiteId();
        id.setIdTipoDoc(idTipoDoc);
        id.setIdTipoTramite(idTipoTramite);
        final Optional<CatDocumentoTramite> opt = documentoTramiteRepository.findById(id);
        if (opt.isEmpty()) {
            return error(CodigoErrorConst.DOCUMENTO_TRAMITE_NO_ENCONTRADO, "Documento Tramite no encontrado: " + idTipoDoc + "/" + idTipoTramite);
        }
        final CatDocumentoTramite entidad = opt.get();
        if (request.getBlnEspecifico() != null) { entidad.setBlnEspecifico(request.getBlnEspecifico()); }
        if (request.getIdeClasificacionDocumento() != null) { entidad.setIdeClasificacionDocumento(request.getIdeClasificacionDocumento()); }
        if (request.getIdeTipoSolicitanteRfe() != null) { entidad.setIdeTipoSolicitanteRfe(request.getIdeTipoSolicitanteRfe()); }
        if (request.getFecIniVigencia() != null) { entidad.setFecIniVigencia(request.getFecIniVigencia()); }
        if (request.getFecFinVigencia() != null) { entidad.setFecFinVigencia(request.getFecFinVigencia()); }
        if (request.getBlnActivo() != null) { entidad.setBlnActivo(request.getBlnActivo()); }
        if (request.getBlnSoloAnexar() != null) { entidad.setBlnSoloAnexar(request.getBlnSoloAnexar()); }
        if (request.getIdeReglaAnexado() != null) { entidad.setIdeReglaAnexado(request.getIdeReglaAnexado()); }
        return exito(documentoTramiteMapper.toResponse(documentoTramiteRepository.save(entidad)));
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
