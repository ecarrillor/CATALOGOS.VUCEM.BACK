package mx.gob.sat.catalogo.service.api.unidadmedida;

import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.catalogo.controller.request.unidadmedida.CatUnidadMedidaRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.unidadmedida.CatUnidadMedidaResponse;

/**
 * <b>Class:</b> CatUnidadMedidaAPIService.java <br>
 * <b>Description:</b>
 * <p>Contrato del servicio API para el catalogo de unidades de medida.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Servicio API
 */
public interface CatUnidadMedidaAPIService {

    /**
     * Lista las unidades de medida de forma paginada.
     *
     * @param pagina Numero de pagina (base 0).
     * @param tamano Cantidad de elementos por pagina.
     * @param busqueda Texto a buscar en descripcion, clave o sigla (opcional).
     * @param sortBy Columna de ordenamiento (opcional).
     * @param sortDir Direccion de ordenamiento (opcional).
     * @return Pagina de unidades de medida envuelta en BaseResponse.
     */
    BaseResponse<PaginaResponse<CatUnidadMedidaResponse>> listar(
            int pagina, int tamano, String busqueda, String sortBy, String sortDir);

    /**
     * Busca una unidad de medida por su clave.
     *
     * @param cveUnidadMedida Clave de la unidad de medida.
     * @return Unidad de medida encontrada envuelta en BaseResponse.
     */
    BaseResponse<CatUnidadMedidaResponse> findById(String cveUnidadMedida);

    /**
     * Crea una nueva unidad de medida.
     *
     * @param request Datos de la unidad de medida a crear.
     * @return Unidad de medida creada envuelta en BaseResponse.
     */
    BaseResponse<CatUnidadMedidaResponse> crear(CatUnidadMedidaRequest request);

    /**
     * Actualiza los campos no nulos de una unidad de medida existente.
     *
     * @param cveUnidadMedida Clave de la unidad de medida a actualizar.
     * @param request Datos a actualizar.
     * @return Unidad de medida actualizada envuelta en BaseResponse.
     */
    BaseResponse<CatUnidadMedidaResponse> actualizar(String cveUnidadMedida, CatUnidadMedidaRequest request);
}
