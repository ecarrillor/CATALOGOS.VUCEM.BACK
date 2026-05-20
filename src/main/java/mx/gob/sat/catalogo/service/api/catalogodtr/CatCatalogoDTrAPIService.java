package mx.gob.sat.catalogo.service.api.catalogodtr;
import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.catalogo.controller.request.catalogodtr.CatCatalogoDTrRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.catalogodtr.CatCatalogoDTrResponse;

public interface CatCatalogoDTrAPIService {
    BaseResponse<PaginaResponse<CatCatalogoDTrResponse>> listar(int pagina, int tamano, String busqueda, String sortBy, String sortDir);
    BaseResponse<CatCatalogoDTrResponse> findById(String cveCatalogo, String cveLenguaje);
    BaseResponse<CatCatalogoDTrResponse> crear(CatCatalogoDTrRequest request);
    BaseResponse<CatCatalogoDTrResponse> actualizar(String cveCatalogo, String cveLenguaje, CatCatalogoDTrRequest request);
}
