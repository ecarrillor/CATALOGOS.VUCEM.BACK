package mx.gob.sat.catalogo.service.api.tiporfc;
import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.catalogo.controller.request.tiporfc.CatTipoRfcRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.tiporfc.CatTipoRfcResponse;

public interface CatTipoRfcAPIService {
    BaseResponse<PaginaResponse<CatTipoRfcResponse>> listar(int pagina, int tamano, String busqueda, String sortBy, String sortDir);
    BaseResponse<CatTipoRfcResponse> findById(String rfc, String ideTipoRfc);
    BaseResponse<CatTipoRfcResponse> crear(CatTipoRfcRequest request);
    BaseResponse<CatTipoRfcResponse> actualizar(String rfc, String ideTipoRfc, CatTipoRfcRequest request);
}
