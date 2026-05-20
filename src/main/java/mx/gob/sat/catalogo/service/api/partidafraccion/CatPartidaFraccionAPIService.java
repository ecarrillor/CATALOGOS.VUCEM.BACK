package mx.gob.sat.catalogo.service.api.partidafraccion;

import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.catalogo.controller.request.partidafraccion.CatPartidaFraccionRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.partidafraccion.CatPartidaFraccionResponse;

/**
 * Interfaz del servicio para el catalogo de partida fraccion.
 *
 * @author Javier Chávez Barrios
 * @since 18 de mayo del 2026
 */
public interface CatPartidaFraccionAPIService {
    BaseResponse<PaginaResponse<CatPartidaFraccionResponse>> listar(int pagina, int tamano, String busqueda, String sortBy, String sortDir);
    BaseResponse<CatPartidaFraccionResponse> findById(String cveCapituloFraccion, String cvePartidaFraccion);
    BaseResponse<CatPartidaFraccionResponse> crear(CatPartidaFraccionRequest request);
    BaseResponse<CatPartidaFraccionResponse> actualizar(String cveCapituloFraccion, String cvePartidaFraccion, CatPartidaFraccionRequest request);
}
