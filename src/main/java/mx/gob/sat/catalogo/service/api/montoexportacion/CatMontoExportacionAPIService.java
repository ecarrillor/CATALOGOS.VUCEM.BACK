package mx.gob.sat.catalogo.service.api.montoexportacion;

import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.catalogo.controller.request.montoexportacion.CatMontoExportacionRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.montoexportacion.CatMontoExportacionResponse;
import java.time.LocalDate;

public interface CatMontoExportacionAPIService {
    BaseResponse<PaginaResponse<CatMontoExportacionResponse>> listar(int pagina, int tamano, String busqueda, String sortBy, String sortDir);
    BaseResponse<CatMontoExportacionResponse> findById(String rfcExportador, LocalDate fecMontoExportacion);
    BaseResponse<CatMontoExportacionResponse> crear(CatMontoExportacionRequest request);
    BaseResponse<CatMontoExportacionResponse> actualizar(String rfcExportador, LocalDate fecMontoExportacion, CatMontoExportacionRequest request);
}
