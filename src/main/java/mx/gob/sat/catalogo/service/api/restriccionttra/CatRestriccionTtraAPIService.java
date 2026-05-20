package mx.gob.sat.catalogo.service.api.restriccionttra;

import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.catalogo.controller.request.restriccionttra.CatRestriccionTtraRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.restriccionttra.CatRestriccionTtraResponse;

public interface CatRestriccionTtraAPIService {

    BaseResponse<PaginaResponse<CatRestriccionTtraResponse>> listar(int pagina, int tamano, String busqueda, String sortBy, String sortDir);

    BaseResponse<CatRestriccionTtraResponse> findById(Short idRestriccionTtra);

    BaseResponse<CatRestriccionTtraResponse> crear(CatRestriccionTtraRequest request);

    BaseResponse<CatRestriccionTtraResponse> actualizar(Short idRestriccionTtra, CatRestriccionTtraRequest request);
}
