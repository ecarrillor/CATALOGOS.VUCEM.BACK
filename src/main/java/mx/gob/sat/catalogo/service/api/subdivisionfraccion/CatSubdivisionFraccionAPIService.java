package mx.gob.sat.catalogo.service.api.subdivisionfraccion;

import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.catalogo.controller.request.subdivisionfraccion.CatSubdivisionFraccionRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.subdivisionfraccion.CatSubdivisionFraccionResponse;

public interface CatSubdivisionFraccionAPIService {
    BaseResponse<PaginaResponse<CatSubdivisionFraccionResponse>> listar(int pagina, int tamano, String busqueda, String sortBy, String sortDir);
    BaseResponse<CatSubdivisionFraccionResponse> findById(String cveSubdivision);
    BaseResponse<CatSubdivisionFraccionResponse> crear(CatSubdivisionFraccionRequest request);
    BaseResponse<CatSubdivisionFraccionResponse> actualizar(String cveSubdivision, CatSubdivisionFraccionRequest request);
}
