package mx.gob.sat.catalogo.service.api.tratadobloquepai;

import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.catalogo.controller.request.tratadobloquepai.CatTratadoBloquePaiRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.tratadobloquepai.CatTratadoBloquePaiResponse;

/**
 * Interfaz del servicio para el catalogo de tratado bloque pais.
 *
 * @author Javier Chávez Barrios
 * @since 18 de mayo del 2026
 */
public interface CatTratadoBloquePaiAPIService {
    BaseResponse<PaginaResponse<CatTratadoBloquePaiResponse>> listar(int pagina, int tamano, String busqueda, String sortBy, String sortDir);
    BaseResponse<CatTratadoBloquePaiResponse> findById(String cvePais, Short idTratadoAcuerdo);
    BaseResponse<CatTratadoBloquePaiResponse> crear(CatTratadoBloquePaiRequest request);
    BaseResponse<CatTratadoBloquePaiResponse> actualizar(String cvePais, Short idTratadoAcuerdo, CatTratadoBloquePaiRequest request);
}
