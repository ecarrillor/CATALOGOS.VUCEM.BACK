package mx.gob.sat.catalogo.service.api.justificacionttra;

import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.catalogo.controller.request.justificacionttra.CatJustificacionTtraRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.justificacionttra.CatJustificacionTtraResponse;

public interface CatJustificacionTtraAPIService {

    BaseResponse<PaginaResponse<CatJustificacionTtraResponse>> listar(int pagina, int tamano, String busqueda, String sortBy, String sortDir);

    BaseResponse<CatJustificacionTtraResponse> findById(Long idJustificacionTtra);

    BaseResponse<CatJustificacionTtraResponse> crear(CatJustificacionTtraRequest request);

    BaseResponse<CatJustificacionTtraResponse> actualizar(Long idJustificacionTtra, CatJustificacionTtraRequest request);
}
