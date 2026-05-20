package mx.gob.sat.catalogo.service.api.dianolaborable;

import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.catalogo.controller.request.dianolaborable.CatDiaNoLaborableRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.dianolaborable.CatDiaNoLaborableResponse;
import java.time.LocalDate;

public interface CatDiaNoLaborableAPIService {
    BaseResponse<PaginaResponse<CatDiaNoLaborableResponse>> listar(int pagina, int tamano, String busqueda, String sortBy, String sortDir);
    BaseResponse<CatDiaNoLaborableResponse> findById(LocalDate fecNoLaborable, String cveCalendario);
    BaseResponse<CatDiaNoLaborableResponse> crear(CatDiaNoLaborableRequest request);
    BaseResponse<CatDiaNoLaborableResponse> actualizar(LocalDate fecNoLaborable, String cveCalendario, CatDiaNoLaborableRequest request);
}
