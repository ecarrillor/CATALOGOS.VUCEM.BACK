package mx.gob.sat.catalogo.service.api.aprobcertse;

import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.catalogo.controller.request.aprobcertse.CatAprobCertSeRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.aprobcertse.CatAprobCertSeResponse;

/**
 * <b>Class:</b> CatAprobCertSeAPIService.java <br>
 * <b>Description:</b>
 * <p>Contrato del servicio API para el catalogo de aprobaciones de certificado SE.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Servicio API
 */
public interface CatAprobCertSeAPIService {

    /**
     * Lista los registros de forma paginada.
     *
     * @param pagina   Numero de pagina (base 0).
     * @param tamano   Cantidad de elementos por pagina.
     * @param busqueda Texto a buscar (opcional).
     * @param sortBy   Columna de ordenamiento (opcional).
     * @param sortDir  Direccion de ordenamiento (opcional).
     * @return Pagina de registros envuelta en BaseResponse.
     */
    BaseResponse<PaginaResponse<CatAprobCertSeResponse>> listar(
            int pagina, int tamano, String busqueda, String sortBy, String sortDir);

    /**
     * Busca un registro por su identificador.
     *
     * @param idAprobCertSe Identificador del registro.
     * @return Registro encontrado envuelto en BaseResponse.
     */
    BaseResponse<CatAprobCertSeResponse> findById(Short idAprobCertSe);

    /**
     * Crea un nuevo registro.
     *
     * @param request Datos del registro a crear.
     * @return Registro creado envuelto en BaseResponse.
     */
    BaseResponse<CatAprobCertSeResponse> crear(CatAprobCertSeRequest request);

    /**
     * Actualiza los campos no nulos de un registro existente.
     *
     * @param idAprobCertSe Identificador del registro a actualizar.
     * @param request       Datos a actualizar.
     * @return Registro actualizado envuelto en BaseResponse.
     */
    BaseResponse<CatAprobCertSeResponse> actualizar(Short idAprobCertSe, CatAprobCertSeRequest request);
}
