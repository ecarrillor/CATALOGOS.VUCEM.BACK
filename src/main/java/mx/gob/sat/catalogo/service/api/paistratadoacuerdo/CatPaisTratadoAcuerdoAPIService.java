package mx.gob.sat.catalogo.service.api.paistratadoacuerdo;

import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.catalogo.controller.request.paistratadoacuerdo.CatPaisTratadoAcuerdoRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.paistratadoacuerdo.CatPaisTratadoAcuerdoResponse;

/**
 * Interfaz del servicio para el catalogo de pais tratado acuerdo.
 *
 * @author Javier Chávez Barrios
 * @since 18 de mayo del 2026
 */
public interface CatPaisTratadoAcuerdoAPIService {
    BaseResponse<PaginaResponse<CatPaisTratadoAcuerdoResponse>> listar(int pagina, int tamano, String busqueda, String sortBy, String sortDir);
    BaseResponse<CatPaisTratadoAcuerdoResponse> findById(String cvePais, Short idTratadoAcuerdo);
    BaseResponse<CatPaisTratadoAcuerdoResponse> crear(CatPaisTratadoAcuerdoRequest request);
    BaseResponse<CatPaisTratadoAcuerdoResponse> actualizar(String cvePais, Short idTratadoAcuerdo, CatPaisTratadoAcuerdoRequest request);
}
