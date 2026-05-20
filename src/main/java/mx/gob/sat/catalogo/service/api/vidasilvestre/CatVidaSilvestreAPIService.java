package mx.gob.sat.catalogo.service.api.vidasilvestre;

import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.catalogo.controller.request.vidasilvestre.CatVidaSilvestreRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.vidasilvestre.CatVidaSilvestreResponse;

/**
 * <b>Class:</b> CatVidaSilvestreAPIService.java <br>
 * <b>Description:</b>
 * <p>Contrato del servicio API para el catalogo de VidaSilvestre.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Servicio API
 */
public interface CatVidaSilvestreAPIService {

    /**
     * Lista los registros de forma paginada.
     *
     * @param pagina Numero de pagina (base 0).
     * @param tamano Cantidad de elementos por pagina.
     * @param busqueda Texto a buscar (opcional).
     * @param sortBy Columna de ordenamiento (opcional).
     * @param sortDir Direccion de ordenamiento (opcional).
     * @return Pagina de registros envuelta en BaseResponse.
     */
    BaseResponse<PaginaResponse<CatVidaSilvestreResponse>> listar(
            int pagina, int tamano, String busqueda, String sortBy, String sortDir);

    /**
     * Busca un registro por su identificador.
     *
     * @param idVidaSilvestre Identificador del registro.
     * @return Registro encontrado envuelto en BaseResponse.
     */
    BaseResponse<CatVidaSilvestreResponse> findById(Integer idVidaSilvestre);

    /**
     * Crea un nuevo registro.
     *
     * @param request Datos del registro a crear.
     * @return Registro creado envuelto en BaseResponse.
     */
    BaseResponse<CatVidaSilvestreResponse> crear(CatVidaSilvestreRequest request);

    /**
     * Actualiza los campos no nulos de un registro existente.
     *
     * @param idVidaSilvestre Identificador del registro a actualizar.
     * @param request Datos a actualizar.
     * @return Registro actualizado envuelto en BaseResponse.
     */
    BaseResponse<CatVidaSilvestreResponse> actualizar(Integer idVidaSilvestre, CatVidaSilvestreRequest request);
}
