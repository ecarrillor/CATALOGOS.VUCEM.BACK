package mx.gob.sat.catalogo.service.api.subpartidafraccion;

import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.catalogo.controller.request.subpartidafraccion.CatSubpartidaFraccionRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.subpartidafraccion.CatSubpartidaFraccionResponse;

/**
 * Interfaz del servicio para el catalogo de subpartida fraccion.
 *
 * @author Javier Chávez Barrios
 * @since 18 de mayo del 2026
 */
public interface CatSubpartidaFraccionAPIService {
    BaseResponse<PaginaResponse<CatSubpartidaFraccionResponse>> listar(int pagina, int tamano, String busqueda, String sortBy, String sortDir);
    BaseResponse<CatSubpartidaFraccionResponse> findById(String cveSubpartidaFraccion, String cveCapituloFraccion, String cvePartidaFraccion);
    BaseResponse<CatSubpartidaFraccionResponse> crear(CatSubpartidaFraccionRequest request);
    BaseResponse<CatSubpartidaFraccionResponse> actualizar(String cveSubpartidaFraccion, String cveCapituloFraccion, String cvePartidaFraccion, CatSubpartidaFraccionRequest request);
}
