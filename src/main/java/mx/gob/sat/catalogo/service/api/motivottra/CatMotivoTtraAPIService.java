package mx.gob.sat.catalogo.service.api.motivottra;

import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.catalogo.controller.request.motivottra.CatMotivoTtraRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.motivottra.CatMotivoTtraResponse;

public interface CatMotivoTtraAPIService {

    BaseResponse<PaginaResponse<CatMotivoTtraResponse>> listar(int pagina, int tamano, String busqueda, String sortBy, String sortDir);

    BaseResponse<CatMotivoTtraResponse> findById(Long idMotivoTtra);

    BaseResponse<CatMotivoTtraResponse> crear(CatMotivoTtraRequest request);

    BaseResponse<CatMotivoTtraResponse> actualizar(Long idMotivoTtra, CatMotivoTtraRequest request);
}
