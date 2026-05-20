package mx.gob.sat.catalogo.service.api.clasificacionregimen;

import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.catalogo.controller.request.clasificacionregimen.CatClasificacionRegimenRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.clasificacionregimen.CatClasificacionRegimenResponse;

/**
 * Interfaz del servicio para el catalogo de clasificacion de regimen.
 *
 * @author Javier Chávez Barrios
 * @since 18 de mayo del 2026
 */
public interface CatClasificacionRegimenAPIService {
    BaseResponse<PaginaResponse<CatClasificacionRegimenResponse>> listar(int pagina, int tamano, String busqueda, String sortBy, String sortDir);
    BaseResponse<CatClasificacionRegimenResponse> findById(String cveClasificacionRegimen, String cveRegimen);
    BaseResponse<CatClasificacionRegimenResponse> crear(CatClasificacionRegimenRequest request);
    BaseResponse<CatClasificacionRegimenResponse> actualizar(String cveClasificacionRegimen, String cveRegimen, CatClasificacionRegimenRequest request);
}
