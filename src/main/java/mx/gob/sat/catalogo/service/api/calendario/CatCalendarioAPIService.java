package mx.gob.sat.catalogo.service.api.calendario;

import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.catalogo.controller.request.calendario.CatCalendarioRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.calendario.CatCalendarioResponse;

/**
 * <b>Class:</b> CatCalendarioAPIService.java <br>
 * <b>Description:</b>
 * <p>Contrato del servicio API para el catalogo de calendarios.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Servicio API
 */
public interface CatCalendarioAPIService {

    /**
     * Lista los calendarios de forma paginada con soporte de busqueda y ordenamiento.
     *
     * @param pagina Numero de pagina (base 0).
     * @param tamano Cantidad de elementos por pagina.
     * @param busqueda Texto a buscar en nombre o clave (opcional).
     * @param sortBy Columna de ordenamiento (opcional).
     * @param sortDir Direccion de ordenamiento (opcional).
     * @return Pagina de calendarios envuelta en BaseResponse.
     */
    BaseResponse<PaginaResponse<CatCalendarioResponse>> listar(
            int pagina, int tamano, String busqueda, String sortBy, String sortDir);

    /**
     * Busca un calendario por su clave.
     *
     * @param cveCalendario Clave del calendario.
     * @return Calendario encontrado envuelto en BaseResponse.
     */
    BaseResponse<CatCalendarioResponse> findById(String cveCalendario);

    /**
     * Crea un nuevo calendario.
     *
     * @param request Datos del calendario a crear.
     * @return Calendario creado envuelto en BaseResponse.
     */
    BaseResponse<CatCalendarioResponse> crear(CatCalendarioRequest request);

    /**
     * Actualiza los campos no nulos de un calendario existente.
     *
     * @param cveCalendario Clave del calendario a actualizar.
     * @param request Datos a actualizar.
     * @return Calendario actualizado envuelto en BaseResponse.
     */
    BaseResponse<CatCalendarioResponse> actualizar(String cveCalendario, CatCalendarioRequest request);
}
