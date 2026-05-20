package mx.gob.sat.catalogo.service.api.restricdescprod;

import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.catalogo.controller.request.restricdescprod.CatRestricDescProdRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.restricdescprod.CatRestricDescProdResponse;

public interface CatRestricDescProdAPIService {
    BaseResponse<PaginaResponse<CatRestricDescProdResponse>> listar(int pagina, int tamano, String busqueda, String sortBy, String sortDir);
    BaseResponse<CatRestricDescProdResponse> findById(Long idRestricDescProd);
    BaseResponse<CatRestricDescProdResponse> crear(CatRestricDescProdRequest request);
    BaseResponse<CatRestricDescProdResponse> actualizar(Long idRestricDescProd, CatRestricDescProdRequest request);
}
