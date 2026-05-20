package mx.gob.sat.catalogo.service.api.equivalenciaaelc;

import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.catalogo.controller.request.equivalenciaaelc.CatEquivalenciaAelcRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.equivalenciaaelc.CatEquivalenciaAelcResponse;
import java.time.LocalDate;

public interface CatEquivalenciaAelcAPIService {
    BaseResponse<PaginaResponse<CatEquivalenciaAelcResponse>> listar(int pagina, int tamano, String busqueda, String sortBy, String sortDir);
    BaseResponse<CatEquivalenciaAelcResponse> findById(LocalDate fecIniVigencia, String cveMoneda);
    BaseResponse<CatEquivalenciaAelcResponse> crear(CatEquivalenciaAelcRequest request);
    BaseResponse<CatEquivalenciaAelcResponse> actualizar(LocalDate fecIniVigencia, String cveMoneda, CatEquivalenciaAelcRequest request);
}
