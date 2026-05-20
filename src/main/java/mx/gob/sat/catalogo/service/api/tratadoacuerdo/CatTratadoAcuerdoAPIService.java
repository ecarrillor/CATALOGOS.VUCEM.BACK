package mx.gob.sat.catalogo.service.api.tratadoacuerdo;

import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.catalogo.controller.request.tratadoacuerdo.CatTratadoAcuerdoRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.tratadoacuerdo.CatTratadoAcuerdoResponse;

/**
 * <b>Class:</b> CatTratadoAcuerdoAPIService.java <br>
 * <b>Description:</b>
 * <p>Contrato del servicio API para el catalogo de TratadoAcuerdo.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Servicio API
 */
public interface CatTratadoAcuerdoAPIService {

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
    BaseResponse<PaginaResponse<CatTratadoAcuerdoResponse>> listar(
            int pagina, int tamano, String busqueda, String sortBy, String sortDir);

    /**
     * Busca un registro por su identificador.
     *
     * @param idTratadoAcuerdo Identificador del registro.
     * @return Registro encontrado envuelto en BaseResponse.
     */
    BaseResponse<CatTratadoAcuerdoResponse> findById(Short idTratadoAcuerdo);

    /**
     * Crea un nuevo registro.
     *
     * @param request Datos del registro a crear.
     * @return Registro creado envuelto en BaseResponse.
     */
    BaseResponse<CatTratadoAcuerdoResponse> crear(CatTratadoAcuerdoRequest request);

    /**
     * Actualiza los campos no nulos de un registro existente.
     *
     * @param idTratadoAcuerdo Identificador del registro a actualizar.
     * @param request Datos a actualizar.
     * @return Registro actualizado envuelto en BaseResponse.
     */
    BaseResponse<CatTratadoAcuerdoResponse> actualizar(Short idTratadoAcuerdo, CatTratadoAcuerdoRequest request);
}
