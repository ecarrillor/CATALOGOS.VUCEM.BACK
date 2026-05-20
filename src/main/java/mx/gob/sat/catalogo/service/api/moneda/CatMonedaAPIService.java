package mx.gob.sat.catalogo.service.api.moneda;

import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.catalogo.controller.request.moneda.CatMonedaRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.moneda.CatMonedaResponse;

/**
 * <b>Class:</b> CatMonedaAPIService.java <br>
 * <b>Description:</b>
 * <p>Contrato del servicio API para el catalogo de monedas.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Servicio API
 */
public interface CatMonedaAPIService {

    /**
     * Lista las monedas de forma paginada con soporte de busqueda y ordenamiento.
     *
     * @param pagina Numero de pagina (base 0).
     * @param tamano Cantidad de elementos por pagina.
     * @param busqueda Texto a buscar en nombre o clave (opcional).
     * @param sortBy Columna de ordenamiento (opcional).
     * @param sortDir Direccion de ordenamiento: {@code asc} o {@code desc} (opcional).
     * @return Pagina de monedas envuelta en BaseResponse.
     */
    BaseResponse<PaginaResponse<CatMonedaResponse>> listar(
            int pagina, int tamano, String busqueda, String sortBy, String sortDir);

    /**
     * Crea una nueva moneda.
     *
     * @param request Datos de la moneda a crear.
     * @return Moneda creada envuelta en BaseResponse.
     */
    BaseResponse<CatMonedaResponse> crear(CatMonedaRequest request);

    /**
     * Busca una moneda por su clave.
     *
     * @param cveMoneda Clave de la moneda.
     * @return Moneda encontrada envuelta en BaseResponse.
     */
    BaseResponse<CatMonedaResponse> findById(String cveMoneda);

    /**
     * Actualiza los campos no nulos de una moneda existente.
     *
     * @param cveMoneda Clave de la moneda a actualizar.
     * @param request Datos a actualizar.
     * @return Moneda actualizada envuelta en BaseResponse.
     */
    BaseResponse<CatMonedaResponse> actualizar(String cveMoneda, CatMonedaRequest request);
}
