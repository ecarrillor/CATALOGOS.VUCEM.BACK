package mx.gob.sat.catalogo.service.api.aga;

import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.catalogo.controller.request.aga.CatAgaRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.aga.CatAgaResponse;

/**
 * <b>Class:</b> CatAgaAPIService.java <br>
 * <b>Description:</b>
 * <p>Contrato del servicio API para el catalogo de AGA.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Servicio API
 */
public interface CatAgaAPIService {

    /**
     * Lista los registros AGA de forma paginada.
     *
     * @param pagina Numero de pagina (base 0).
     * @param tamano Cantidad de elementos por pagina.
     * @param busqueda Texto a buscar en descripcion o clave (opcional).
     * @param sortBy Columna de ordenamiento (opcional).
     * @param sortDir Direccion de ordenamiento (opcional).
     * @return Pagina de registros AGA envuelta en BaseResponse.
     */
    BaseResponse<PaginaResponse<CatAgaResponse>> listar(
            int pagina, int tamano, String busqueda, String sortBy, String sortDir);

    /**
     * Busca un registro AGA por su clave.
     *
     * @param cveParametro Clave del parametro AGA.
     * @return Registro AGA encontrado envuelto en BaseResponse.
     */
    BaseResponse<CatAgaResponse> findById(String cveParametro);

    /**
     * Crea un nuevo registro AGA.
     *
     * @param request Datos del registro AGA a crear.
     * @return Registro AGA creado envuelto en BaseResponse.
     */
    BaseResponse<CatAgaResponse> crear(CatAgaRequest request);

    /**
     * Actualiza los campos no nulos de un registro AGA existente.
     *
     * @param cveParametro Clave del parametro AGA a actualizar.
     * @param request Datos a actualizar.
     * @return Registro AGA actualizado envuelto en BaseResponse.
     */
    BaseResponse<CatAgaResponse> actualizar(String cveParametro, CatAgaRequest request);
}
