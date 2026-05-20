package mx.gob.sat.catalogo.service.api.tipoaduana;

import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.catalogo.controller.request.tipoaduana.CatTipoAduanaRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.aduana.CatTipoAduanaResponse;

/**
 * <b>Class:</b> CatTipoAduanaAPIService.java <br>
 * <b>Description:</b>
 * <p>Contrato del servicio API para el catalogo de tipos de aduana.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Servicio API
 */
public interface CatTipoAduanaAPIService {

    /**
     * Lista los tipos de aduana de forma paginada con soporte de busqueda y ordenamiento.
     *
     * @param pagina Numero de pagina (base 0).
     * @param tamano Cantidad de elementos por pagina.
     * @param busqueda Texto a buscar en nombre o clave (opcional).
     * @param sortBy Columna de ordenamiento (opcional).
     * @param sortDir Direccion de ordenamiento: {@code asc} o {@code desc} (opcional).
     * @return Pagina de tipos de aduana envuelta en BaseResponse.
     */
    BaseResponse<PaginaResponse<CatTipoAduanaResponse>> listar(
            int pagina, int tamano, String busqueda, String sortBy, String sortDir);

    /**
     * Crea un nuevo tipo de aduana.
     *
     * @param request Datos del tipo de aduana a crear.
     * @return Tipo de aduana creado envuelto en BaseResponse.
     */
    BaseResponse<CatTipoAduanaResponse> crear(CatTipoAduanaRequest request);

    /**
     * Busca un tipo de aduana por su clave.
     *
     * @param cveTipoAduana Clave del tipo de aduana.
     * @return Tipo de aduana encontrado envuelto en BaseResponse.
     */
    BaseResponse<CatTipoAduanaResponse> findById(String cveTipoAduana);

    /**
     * Actualiza los campos no nulos de un tipo de aduana existente.
     *
     * @param cveTipoAduana Clave del tipo de aduana a actualizar.
     * @param request Datos a actualizar.
     * @return Tipo de aduana actualizado envuelto en BaseResponse.
     */
    BaseResponse<CatTipoAduanaResponse> actualizar(String cveTipoAduana, CatTipoAduanaRequest request);
}
