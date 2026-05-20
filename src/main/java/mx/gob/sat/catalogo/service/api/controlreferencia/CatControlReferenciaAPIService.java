package mx.gob.sat.catalogo.service.api.controlreferencia;

import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.catalogo.controller.request.controlreferencia.CatControlReferenciaRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.controlreferencia.CatControlReferenciaResponse;

/**
 * Servicio API para el catalogo de control de referencia.
 */
public interface CatControlReferenciaAPIService {

    BaseResponse<PaginaResponse<CatControlReferenciaResponse>> listar(int pagina, int tamano, String busqueda, String sortBy, String sortDir);

    BaseResponse<CatControlReferenciaResponse> findById(Integer idControlReferencia);

    BaseResponse<CatControlReferenciaResponse> crear(CatControlReferenciaRequest request);

    BaseResponse<CatControlReferenciaResponse> actualizar(Integer idControlReferencia, CatControlReferenciaRequest request);
}
