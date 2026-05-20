package mx.gob.sat.catalogo.service.api.plazottra;

import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.catalogo.controller.request.plazottra.CatPlazoTtraRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.plazottra.CatPlazoTtraResponse;

public interface CatPlazoTtraAPIService {
    BaseResponse<PaginaResponse<CatPlazoTtraResponse>> listar(int pagina, int tamano, String busqueda, String sortBy, String sortDir);
    BaseResponse<CatPlazoTtraResponse> findById(Long idTipoTramite, String idePlazoVigencia);
    BaseResponse<CatPlazoTtraResponse> crear(CatPlazoTtraRequest request);
    BaseResponse<CatPlazoTtraResponse> actualizar(Long idTipoTramite, String idePlazoVigencia, CatPlazoTtraRequest request);
}
