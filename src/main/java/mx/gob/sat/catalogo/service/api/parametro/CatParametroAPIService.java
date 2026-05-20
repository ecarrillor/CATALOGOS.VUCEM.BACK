package mx.gob.sat.catalogo.service.api.parametro;

import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.catalogo.controller.request.parametro.CatParametroRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.SelectResponse;
import mx.gob.sat.catalogo.controller.response.parametro.CatParametroResponse;

import java.util.List;

/**
 * <b>Class:</b> CatParametroAPIService.java <br>
 * <b>Description:</b>
 * <p>Contrato del servicio API para el catalogo de parametros del sistema.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Servicio API
 */
public interface CatParametroAPIService {

    /**
     * Lista los parametros de forma paginada. Si busqueda es "activo" o "inactivo"
     * filtra por estado.
     *
     * @param pagina Numero de pagina (base 0).
     * @param tamano Cantidad de elementos por pagina.
     * @param busqueda Texto de busqueda o "activo"/"inactivo" (opcional).
     * @param sortBy Columna de ordenamiento (opcional).
     * @param sortDir Direccion de ordenamiento (opcional).
     * @return Pagina de parametros envuelta en BaseResponse.
     */
    BaseResponse<PaginaResponse<CatParametroResponse>> listar(
            int pagina, int tamano, String busqueda, String sortBy, String sortDir);

    /**
     * Busca un parametro por su clave.
     *
     * @param cveParametro Clave del parametro.
     * @return Parametro encontrado envuelto en BaseResponse.
     */
    BaseResponse<CatParametroResponse> findById(String cveParametro);

    /**
     * Crea un nuevo parametro.
     *
     * @param request Datos del parametro a crear.
     * @return Parametro creado envuelto en BaseResponse.
     */
    BaseResponse<CatParametroResponse> crear(CatParametroRequest request);

    /**
     * Actualiza los campos de un parametro existente.
     *
     * @param cveParametro Clave del parametro a actualizar.
     * @param request Datos a actualizar.
     * @return Parametro actualizado envuelto en BaseResponse.
     */
    BaseResponse<CatParametroResponse> actualizar(String cveParametro, CatParametroRequest request);

    /**
     * Retorna lista de dependencias activas en formato id-nombre para dropdowns.
     *
     * @return Lista de seleccion de dependencias en BaseResponse.
     */
    BaseResponse<List<SelectResponse>> listarDependencias();
}
