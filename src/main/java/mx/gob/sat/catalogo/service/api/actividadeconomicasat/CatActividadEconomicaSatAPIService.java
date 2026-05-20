package mx.gob.sat.catalogo.service.api.actividadeconomicasat;

import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.catalogo.controller.request.actividadeconomicasat.CatActividadEconomicaSatRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.actividadeconomicasat.CatActividadEconomicaSatResponse;

public interface CatActividadEconomicaSatAPIService {

    BaseResponse<PaginaResponse<CatActividadEconomicaSatResponse>> listar(int pagina, int tamano, String busqueda, String sortBy, String sortDir);

    BaseResponse<CatActividadEconomicaSatResponse> findById(Long idActividadEconomicaSat);

    BaseResponse<CatActividadEconomicaSatResponse> crear(CatActividadEconomicaSatRequest request);

    BaseResponse<CatActividadEconomicaSatResponse> actualizar(Long idActividadEconomicaSat, CatActividadEconomicaSatRequest request);
}
