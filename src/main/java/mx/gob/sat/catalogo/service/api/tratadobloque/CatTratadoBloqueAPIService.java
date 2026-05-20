package mx.gob.sat.catalogo.service.api.tratadobloque;

import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.catalogo.controller.request.tratadobloque.CatTratadoBloqueRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.tratadobloque.CatTratadoBloqueResponse;

/**
 * Interfaz del servicio para el catalogo de tratado bloque.
 *
 * @author Javier Chávez Barrios
 * @since 18 de mayo del 2026
 */
public interface CatTratadoBloqueAPIService {
    BaseResponse<PaginaResponse<CatTratadoBloqueResponse>> listar(int pagina, int tamano, String busqueda, String sortBy, String sortDir);
    BaseResponse<CatTratadoBloqueResponse> findById(Short idTratadoAcuerdo, Short idBloque);
    BaseResponse<CatTratadoBloqueResponse> crear(CatTratadoBloqueRequest request);
    BaseResponse<CatTratadoBloqueResponse> actualizar(Short idTratadoAcuerdo, Short idBloque, CatTratadoBloqueRequest request);
}
