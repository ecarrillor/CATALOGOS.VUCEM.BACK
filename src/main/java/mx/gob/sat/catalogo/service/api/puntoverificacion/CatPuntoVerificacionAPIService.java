package mx.gob.sat.catalogo.service.api.puntoverificacion;

import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.catalogo.controller.request.puntoverificacion.CatPuntoVerificacionRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.puntoverificacion.CatPuntoVerificacionResponse;

/**
 * <b>Class:</b> CatPuntoVerificacionAPIService.java <br>
 * <b>Description:</b>
 * <p>Contrato del servicio API para el catalogo de PuntoVerificacion.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Servicio API
 */
public interface CatPuntoVerificacionAPIService {

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
    BaseResponse<PaginaResponse<CatPuntoVerificacionResponse>> listar(
            int pagina, int tamano, String busqueda, String sortBy, String sortDir);

    /**
     * Busca un registro por su identificador.
     *
     * @param idPuntoVerificacion Identificador del registro.
     * @return Registro encontrado envuelto en BaseResponse.
     */
    BaseResponse<CatPuntoVerificacionResponse> findById(Integer idPuntoVerificacion);

    /**
     * Crea un nuevo registro.
     *
     * @param request Datos del registro a crear.
     * @return Registro creado envuelto en BaseResponse.
     */
    BaseResponse<CatPuntoVerificacionResponse> crear(CatPuntoVerificacionRequest request);

    /**
     * Actualiza los campos no nulos de un registro existente.
     *
     * @param idPuntoVerificacion Identificador del registro a actualizar.
     * @param request Datos a actualizar.
     * @return Registro actualizado envuelto en BaseResponse.
     */
    BaseResponse<CatPuntoVerificacionResponse> actualizar(Integer idPuntoVerificacion, CatPuntoVerificacionRequest request);
}
