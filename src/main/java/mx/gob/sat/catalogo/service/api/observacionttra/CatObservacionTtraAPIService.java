package mx.gob.sat.catalogo.service.api.observacionttra;

import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.catalogo.controller.request.observacionttra.CatObservacionTtraRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.observacionttra.CatObservacionTtraResponse;

public interface CatObservacionTtraAPIService {

    BaseResponse<PaginaResponse<CatObservacionTtraResponse>> listar(int pagina, int tamano, String busqueda, String sortBy, String sortDir);

    BaseResponse<CatObservacionTtraResponse> findById(Long idObservacionTtra);

    BaseResponse<CatObservacionTtraResponse> crear(CatObservacionTtraRequest request);

    BaseResponse<CatObservacionTtraResponse> actualizar(Long idObservacionTtra, CatObservacionTtraRequest request);
}
