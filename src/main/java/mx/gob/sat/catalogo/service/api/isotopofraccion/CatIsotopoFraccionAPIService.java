package mx.gob.sat.catalogo.service.api.isotopofraccion;

import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.catalogo.controller.request.isotopofraccion.CatIsotopoFraccionRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.isotopofraccion.CatIsotopoFraccionResponse;

public interface CatIsotopoFraccionAPIService {
    BaseResponse<PaginaResponse<CatIsotopoFraccionResponse>> listar(int pagina, int tamano, String busqueda, String sortBy, String sortDir);
    BaseResponse<CatIsotopoFraccionResponse> findById(Short idIsotopo);
    BaseResponse<CatIsotopoFraccionResponse> crear(CatIsotopoFraccionRequest request);
    BaseResponse<CatIsotopoFraccionResponse> actualizar(Short idIsotopo, CatIsotopoFraccionRequest request);
}
