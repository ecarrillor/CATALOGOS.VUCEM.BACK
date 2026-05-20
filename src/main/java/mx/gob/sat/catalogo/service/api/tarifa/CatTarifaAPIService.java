package mx.gob.sat.catalogo.service.api.tarifa;

import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.catalogo.controller.request.tarifa.CatTarifaRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.tarifa.CatTarifaResponse;
import java.time.LocalDate;

public interface CatTarifaAPIService {
    BaseResponse<PaginaResponse<CatTarifaResponse>> listar(int pagina, int tamano, String busqueda, String sortBy, String sortDir);
    BaseResponse<CatTarifaResponse> findById(Long idTipoTramite, LocalDate fecIniVigencia, String ideTipoTarifa);
    BaseResponse<CatTarifaResponse> crear(CatTarifaRequest request);
    BaseResponse<CatTarifaResponse> actualizar(Long idTipoTramite, LocalDate fecIniVigencia, String ideTipoTarifa, CatTarifaRequest request);
}
