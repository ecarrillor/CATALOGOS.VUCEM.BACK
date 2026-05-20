package mx.gob.sat.catalogo.service.api.regimen;

import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.catalogo.controller.request.regimen.CatRegimenRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.regimen.CatRegimenResponse;

/**
 * <b>Class:</b> CatRegimenAPIService.java <br>
 * <b>Description:</b>
 * <p>Contrato del servicio API para el catalogo de regimenes.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Servicio API
 */
public interface CatRegimenAPIService {

    /**
     * Lista los regimenes de forma paginada.
     *
     * @param pagina Numero de pagina (base 0).
     * @param tamano Cantidad de elementos por pagina.
     * @param busqueda Texto a buscar en nombre o clave (opcional).
     * @param sortBy Columna de ordenamiento (opcional).
     * @param sortDir Direccion de ordenamiento (opcional).
     * @return Pagina de regimenes envuelta en BaseResponse.
     */
    BaseResponse<PaginaResponse<CatRegimenResponse>> listar(
            int pagina, int tamano, String busqueda, String sortBy, String sortDir);

    /**
     * Busca un regimen por su clave.
     *
     * @param cveRegimen Clave del regimen.
     * @return Regimen encontrado envuelto en BaseResponse.
     */
    BaseResponse<CatRegimenResponse> findById(String cveRegimen);

    /**
     * Crea un nuevo regimen.
     *
     * @param request Datos del regimen a crear.
     * @return Regimen creado envuelto en BaseResponse.
     */
    BaseResponse<CatRegimenResponse> crear(CatRegimenRequest request);

    /**
     * Actualiza los campos no nulos de un regimen existente.
     *
     * @param cveRegimen Clave del regimen a actualizar.
     * @param request Datos a actualizar.
     * @return Regimen actualizado envuelto en BaseResponse.
     */
    BaseResponse<CatRegimenResponse> actualizar(String cveRegimen, CatRegimenRequest request);
}
