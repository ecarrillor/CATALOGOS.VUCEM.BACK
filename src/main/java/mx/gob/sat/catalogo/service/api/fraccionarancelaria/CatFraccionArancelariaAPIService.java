package mx.gob.sat.catalogo.service.api.fraccionarancelaria;

import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.catalogo.controller.request.fraccionarancelaria.CatFraccionArancelariaRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.fraccionarancelaria.CatFraccionArancelariaResponse;

public interface CatFraccionArancelariaAPIService {
    BaseResponse<PaginaResponse<CatFraccionArancelariaResponse>> listar(int pagina, int tamano, String busqueda, String sortBy, String sortDir);
    BaseResponse<CatFraccionArancelariaResponse> findById(String cveFraccion);
    BaseResponse<CatFraccionArancelariaResponse> crear(CatFraccionArancelariaRequest request);
    BaseResponse<CatFraccionArancelariaResponse> actualizar(String cveFraccion, CatFraccionArancelariaRequest request);
}
