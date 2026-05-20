package mx.gob.sat.catalogo.service.api.lenguaje;

import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.catalogo.controller.request.lenguaje.CatLenguajeRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.lenguaje.CatLenguajeResponse;

/**
 * <b>Class:</b> CatLenguajeAPIService.java <br>
 * <b>Description:</b>
 * <p>Contrato del servicio API para el catalogo de lenguajes.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Servicio API
 */
public interface CatLenguajeAPIService {

    /**
     * Lista los lenguajes de forma paginada.
     *
     * @param pagina Numero de pagina (base 0).
     * @param tamano Cantidad de elementos por pagina.
     * @param busqueda Texto a buscar en nombre o clave (opcional).
     * @param sortBy Columna de ordenamiento (opcional).
     * @param sortDir Direccion de ordenamiento (opcional).
     * @return Pagina de lenguajes envuelta en BaseResponse.
     */
    BaseResponse<PaginaResponse<CatLenguajeResponse>> listar(
            int pagina, int tamano, String busqueda, String sortBy, String sortDir);

    /**
     * Busca un lenguaje por su clave.
     *
     * @param cveLenguaje Clave del lenguaje.
     * @return Lenguaje encontrado envuelto en BaseResponse.
     */
    BaseResponse<CatLenguajeResponse> findById(String cveLenguaje);

    /**
     * Crea un nuevo lenguaje.
     *
     * @param request Datos del lenguaje a crear.
     * @return Lenguaje creado envuelto en BaseResponse.
     */
    BaseResponse<CatLenguajeResponse> crear(CatLenguajeRequest request);

    /**
     * Actualiza los campos no nulos de un lenguaje existente.
     *
     * @param cveLenguaje Clave del lenguaje a actualizar.
     * @param request Datos a actualizar.
     * @return Lenguaje actualizado envuelto en BaseResponse.
     */
    BaseResponse<CatLenguajeResponse> actualizar(String cveLenguaje, CatLenguajeRequest request);
}
