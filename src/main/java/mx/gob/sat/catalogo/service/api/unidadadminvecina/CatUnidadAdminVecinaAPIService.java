package mx.gob.sat.catalogo.service.api.unidadadminvecina;

import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.catalogo.controller.request.unidadadminvecina.CatUnidadAdminVecinaRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.unidadadminvecina.CatUnidadAdminVecinaResponse;

/**
 * <b>Interface:</b> CatUnidadAdminVecinaAPIService.java <br>
 * <b>Description:</b>
 * <p>Contrato de servicio para el catalogo de unidades administrativas vecinas.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Service
 */
public interface CatUnidadAdminVecinaAPIService {
    BaseResponse<PaginaResponse<CatUnidadAdminVecinaResponse>> listar(int pagina, int tamano, String busqueda, String sortBy, String sortDir);
    BaseResponse<CatUnidadAdminVecinaResponse> findById(String cveUnidadAdministrativa, String cveEntidad);
    BaseResponse<CatUnidadAdminVecinaResponse> crear(CatUnidadAdminVecinaRequest request);
    BaseResponse<CatUnidadAdminVecinaResponse> actualizar(String cveUnidadAdministrativa, String cveEntidad, CatUnidadAdminVecinaRequest request);
}
