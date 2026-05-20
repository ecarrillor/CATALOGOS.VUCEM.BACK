package mx.gob.sat.catalogo.service.api.combinacionsg;

import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.catalogo.controller.request.combinacionsg.CatCombinacionSgRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.combinacionsg.CatCombinacionSgResponse;

public interface CatCombinacionSgAPIService {
    BaseResponse<PaginaResponse<CatCombinacionSgResponse>> listar(int pagina, int tamano, String busqueda, String sortBy, String sortDir);
    BaseResponse<CatCombinacionSgResponse> findById(Long idCombinacionSg);
    BaseResponse<CatCombinacionSgResponse> crear(CatCombinacionSgRequest request);
    BaseResponse<CatCombinacionSgResponse> actualizar(Long idCombinacionSg, CatCombinacionSgRequest request);
}
