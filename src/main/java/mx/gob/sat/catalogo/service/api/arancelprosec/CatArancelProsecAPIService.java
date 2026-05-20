package mx.gob.sat.catalogo.service.api.arancelprosec;

import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.catalogo.controller.request.arancelprosec.CatArancelProsecRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.arancelprosec.CatArancelProsecResponse;

public interface CatArancelProsecAPIService {
    BaseResponse<PaginaResponse<CatArancelProsecResponse>> listar(int pagina, int tamano, String busqueda, String sortBy, String sortDir);
    BaseResponse<CatArancelProsecResponse> crear(CatArancelProsecRequest request);
}
