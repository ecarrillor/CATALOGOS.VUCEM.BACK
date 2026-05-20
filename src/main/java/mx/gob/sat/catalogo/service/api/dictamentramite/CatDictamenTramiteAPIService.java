package mx.gob.sat.catalogo.service.api.dictamentramite;

import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.catalogo.controller.request.dictamentramite.CatDictamenTramiteRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.dictamentramite.CatDictamenTramiteResponse;

public interface CatDictamenTramiteAPIService {
    BaseResponse<PaginaResponse<CatDictamenTramiteResponse>> listar(int pagina, int tamano, String busqueda, String sortBy, String sortDir);
    BaseResponse<CatDictamenTramiteResponse> findById(Long idTipoTramite, Long idTipoDictamen);
    BaseResponse<CatDictamenTramiteResponse> crear(CatDictamenTramiteRequest request);
    BaseResponse<CatDictamenTramiteResponse> actualizar(Long idTipoTramite, Long idTipoDictamen, CatDictamenTramiteRequest request);
}
