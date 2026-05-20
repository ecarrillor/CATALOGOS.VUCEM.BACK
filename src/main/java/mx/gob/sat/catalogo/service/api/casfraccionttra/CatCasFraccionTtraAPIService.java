package mx.gob.sat.catalogo.service.api.casfraccionttra;

import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.catalogo.controller.request.casfraccionttra.CatCasFraccionTtraRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.casfraccionttra.CatCasFraccionTtraResponse;

public interface CatCasFraccionTtraAPIService {
    BaseResponse<PaginaResponse<CatCasFraccionTtraResponse>> listar(int pagina, int tamano, String busqueda, String sortBy, String sortDir);
    BaseResponse<CatCasFraccionTtraResponse> findById(Short idCasFraccionTtra);
    BaseResponse<CatCasFraccionTtraResponse> crear(CatCasFraccionTtraRequest request);
    BaseResponse<CatCasFraccionTtraResponse> actualizar(Short idCasFraccionTtra, CatCasFraccionTtraRequest request);
}
