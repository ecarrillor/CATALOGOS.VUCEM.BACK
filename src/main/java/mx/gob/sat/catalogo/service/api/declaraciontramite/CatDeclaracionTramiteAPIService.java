package mx.gob.sat.catalogo.service.api.declaraciontramite;

import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.catalogo.controller.request.declaraciontramite.CatDeclaracionTramiteRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.declaraciontramite.CatDeclaracionTramiteResponse;

public interface CatDeclaracionTramiteAPIService {
    BaseResponse<PaginaResponse<CatDeclaracionTramiteResponse>> listar(int pagina, int tamano, String busqueda, String sortBy, String sortDir);
    BaseResponse<CatDeclaracionTramiteResponse> findById(String cveDeclaracion, Long idTipoTramite);
    BaseResponse<CatDeclaracionTramiteResponse> crear(CatDeclaracionTramiteRequest request);
    BaseResponse<CatDeclaracionTramiteResponse> actualizar(String cveDeclaracion, Long idTipoTramite, CatDeclaracionTramiteRequest request);
}
