package mx.gob.sat.catalogo.service.api.documentotramite;

import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.catalogo.controller.request.documentotramite.CatDocumentoTramiteRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.documentotramite.CatDocumentoTramiteResponse;

public interface CatDocumentoTramiteAPIService {
    BaseResponse<PaginaResponse<CatDocumentoTramiteResponse>> listar(int pagina, int tamano, String busqueda, String sortBy, String sortDir);
    BaseResponse<CatDocumentoTramiteResponse> findById(Short idTipoDoc, Long idTipoTramite);
    BaseResponse<CatDocumentoTramiteResponse> crear(CatDocumentoTramiteRequest request);
    BaseResponse<CatDocumentoTramiteResponse> actualizar(Short idTipoDoc, Long idTipoTramite, CatDocumentoTramiteRequest request);
}
