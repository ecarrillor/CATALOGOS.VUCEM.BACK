package mx.gob.sat.catalogo.service.api.genero;

import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.catalogo.controller.request.genero.CatGeneroRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.genero.CatGeneroResponse;

/**
 * <b>Class:</b> CatGeneroAPIService.java <br>
 * <b>Description:</b>
 * <p>Contrato del servicio API para el catalogo de generos.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Servicio API
 */
public interface CatGeneroAPIService {

    /**
     * Lista los generos de forma paginada.
     *
     * @param pagina Numero de pagina (base 0).
     * @param tamano Cantidad de elementos por pagina.
     * @param busqueda Texto a buscar en descripcion (opcional).
     * @param sortBy Columna de ordenamiento (opcional).
     * @param sortDir Direccion de ordenamiento (opcional).
     * @return Pagina de generos envuelta en BaseResponse.
     */
    BaseResponse<PaginaResponse<CatGeneroResponse>> listar(
            int pagina, int tamano, String busqueda, String sortBy, String sortDir);

    /**
     * Busca un genero por su identificador.
     *
     * @param idGenero Identificador del genero.
     * @return Genero encontrado envuelto en BaseResponse.
     */
    BaseResponse<CatGeneroResponse> findById(Integer idGenero);

    /**
     * Crea un nuevo genero.
     *
     * @param request Datos del genero a crear.
     * @return Genero creado envuelto en BaseResponse.
     */
    BaseResponse<CatGeneroResponse> crear(CatGeneroRequest request);

    /**
     * Actualiza los campos no nulos de un genero existente.
     *
     * @param idGenero Identificador del genero a actualizar.
     * @param request Datos a actualizar.
     * @return Genero actualizado envuelto en BaseResponse.
     */
    BaseResponse<CatGeneroResponse> actualizar(Integer idGenero, CatGeneroRequest request);
}
