package mx.gob.sat.catalogo.service.api.fraccionttradescrprod;

import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.catalogo.controller.request.fraccionttradescrprod.CatFraccionTtraDescProdRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.fraccionttradescrprod.CatFraccionTtraDescProdResponse;

public interface CatFraccionTtraDescProdAPIService {
    BaseResponse<PaginaResponse<CatFraccionTtraDescProdResponse>> listar(int pagina, int tamano, String busqueda, String sortBy, String sortDir);
    BaseResponse<CatFraccionTtraDescProdResponse> findById(Long idFraccionTtraDescProd);
    BaseResponse<CatFraccionTtraDescProdResponse> crear(CatFraccionTtraDescProdRequest request);
    BaseResponse<CatFraccionTtraDescProdResponse> actualizar(Long idFraccionTtraDescProd, CatFraccionTtraDescProdRequest request);
}
