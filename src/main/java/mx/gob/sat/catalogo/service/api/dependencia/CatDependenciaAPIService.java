package mx.gob.sat.catalogo.service.api.dependencia;

import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.catalogo.controller.request.dependencia.CatDependenciaRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.SelectResponse;
import mx.gob.sat.catalogo.controller.response.dependencia.CatDependenciaResponse;

import java.util.List;

/**
 * <b>Class:</b> CatDependenciaAPIService.java <br>
 * <b>Description:</b>
 * <p>Contrato del servicio API para el catalogo de dependencias.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Servicio API
 */
public interface CatDependenciaAPIService {

    /**
     * Lista las dependencias de forma paginada. Si busqueda es "activo" o "inactivo"
     * filtra por estado.
     *
     * @param pagina Numero de pagina (base 0).
     * @param tamano Cantidad de elementos por pagina.
     * @param busqueda Texto de busqueda o "activo"/"inactivo" (opcional).
     * @param sortBy Columna de ordenamiento (opcional).
     * @param sortDir Direccion de ordenamiento (opcional).
     * @return Pagina de dependencias envuelta en BaseResponse.
     */
    BaseResponse<PaginaResponse<CatDependenciaResponse>> listar(
            int pagina, int tamano, String busqueda, String sortBy, String sortDir);

    /**
     * Busca una dependencia por su identificador.
     *
     * @param id Identificador de la dependencia.
     * @return Dependencia encontrada envuelta en BaseResponse.
     */
    BaseResponse<CatDependenciaResponse> findById(Long id);

    /**
     * Crea una nueva dependencia.
     *
     * @param request Datos de la dependencia a crear.
     * @return Dependencia creada envuelta en BaseResponse.
     */
    BaseResponse<CatDependenciaResponse> crear(CatDependenciaRequest request);

    /**
     * Actualiza los campos de una dependencia existente.
     *
     * @param id Identificador de la dependencia a actualizar.
     * @param request Datos a actualizar.
     * @return Dependencia actualizada envuelta en BaseResponse.
     */
    BaseResponse<CatDependenciaResponse> actualizar(Long id, CatDependenciaRequest request);

    /**
     * Retorna lista de calendarios en formato clave-nombre para dropdowns.
     *
     * @return Lista de seleccion de calendarios en BaseResponse.
     */
    BaseResponse<List<SelectResponse>> listarCalendarios();

    /**
     * Retorna lista de dependencias activas en formato id-nombre para dropdowns.
     *
     * @return Lista de seleccion de dependencias en BaseResponse.
     */
    BaseResponse<List<SelectResponse>> listarDependencias();
}
