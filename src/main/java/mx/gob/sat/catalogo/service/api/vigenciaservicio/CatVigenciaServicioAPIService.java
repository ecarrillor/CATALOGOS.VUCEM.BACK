package mx.gob.sat.catalogo.service.api.vigenciaservicio;

import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.catalogo.controller.request.vigenciaservicio.CatVigenciaServicioRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.vigenciaservicio.CatVigenciaServicioResponse;

public interface CatVigenciaServicioAPIService {
    BaseResponse<PaginaResponse<CatVigenciaServicioResponse>> listar(int pagina, int tamano, String busqueda, String sortBy, String sortDir);
    BaseResponse<CatVigenciaServicioResponse> findById(Short idVigenciaServicio);
    BaseResponse<CatVigenciaServicioResponse> crear(CatVigenciaServicioRequest request);
    BaseResponse<CatVigenciaServicioResponse> actualizar(Short idVigenciaServicio, CatVigenciaServicioRequest request);
}
