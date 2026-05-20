package mx.gob.sat.catalogo.service.api.clasiftoxicologicattra;

import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.catalogo.controller.request.clasiftoxicologicattra.CatClasifToxicologicaTtraRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.clasiftoxicologicattra.CatClasifToxicologicaTtraResponse;

/**
 * <b>Class:</b> CatClasifToxicologicaTtraAPIService.java <br>
 * <b>Description:</b>
 * <p>Contrato del servicio API para el catalogo de ClasifToxicologicaTtra.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Servicio API
 */
public interface CatClasifToxicologicaTtraAPIService {

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
    BaseResponse<PaginaResponse<CatClasifToxicologicaTtraResponse>> listar(
            int pagina, int tamano, String busqueda, String sortBy, String sortDir);

    /**
     * Busca un registro por su identificador.
     *
     * @param idClasifToxicologicaTtra Identificador del registro.
     * @return Registro encontrado envuelto en BaseResponse.
     */
    BaseResponse<CatClasifToxicologicaTtraResponse> findById(Short idClasifToxicologicaTtra);

    /**
     * Crea un nuevo registro.
     *
     * @param request Datos del registro a crear.
     * @return Registro creado envuelto en BaseResponse.
     */
    BaseResponse<CatClasifToxicologicaTtraResponse> crear(CatClasifToxicologicaTtraRequest request);

    /**
     * Actualiza los campos no nulos de un registro existente.
     *
     * @param idClasifToxicologicaTtra Identificador del registro a actualizar.
     * @param request Datos a actualizar.
     * @return Registro actualizado envuelto en BaseResponse.
     */
    BaseResponse<CatClasifToxicologicaTtraResponse> actualizar(Short idClasifToxicologicaTtra, CatClasifToxicologicaTtraRequest request);
}
