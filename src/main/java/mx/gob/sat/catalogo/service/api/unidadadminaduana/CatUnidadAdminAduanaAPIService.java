package mx.gob.sat.catalogo.service.api.unidadadminaduana;

import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.catalogo.controller.request.unidadadminaduana.CatUnidadAdminAduanaRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.unidadadminaduana.CatUnidadAdminAduanaResponse;

/**
 * <b>Interface:</b> CatUnidadAdminAduanaAPIService.java <br>
 * <b>Description:</b>
 * <p>Contrato de servicio para el catalogo de unidades administrativas de aduana.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Service
 */
public interface CatUnidadAdminAduanaAPIService {
    BaseResponse<PaginaResponse<CatUnidadAdminAduanaResponse>> listar(int pagina, int tamano, String busqueda, String sortBy, String sortDir);
    BaseResponse<CatUnidadAdminAduanaResponse> findById(String cveUnidadAdministrativa, String cveAduana);
    BaseResponse<CatUnidadAdminAduanaResponse> crear(CatUnidadAdminAduanaRequest request);
    BaseResponse<CatUnidadAdminAduanaResponse> actualizar(String cveUnidadAdministrativa, String cveAduana, CatUnidadAdminAduanaRequest request);
}
