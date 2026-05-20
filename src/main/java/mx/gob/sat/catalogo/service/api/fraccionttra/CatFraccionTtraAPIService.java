package mx.gob.sat.catalogo.service.api.fraccionttra;

import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.catalogo.controller.request.fraccionttra.CatFraccionTtraRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.fraccionttra.CatFraccionTtraResponse;

public interface CatFraccionTtraAPIService {
    BaseResponse<PaginaResponse<CatFraccionTtraResponse>> listar(int pagina, int tamano, String busqueda, String sortBy, String sortDir);
    BaseResponse<CatFraccionTtraResponse> findById(Long idFraccionGob);
    BaseResponse<CatFraccionTtraResponse> crear(CatFraccionTtraRequest request);
    BaseResponse<CatFraccionTtraResponse> actualizar(Long idFraccionGob, CatFraccionTtraRequest request);
}
