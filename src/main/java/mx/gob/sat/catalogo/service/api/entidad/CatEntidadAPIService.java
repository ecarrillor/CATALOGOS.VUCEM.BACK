package mx.gob.sat.catalogo.service.api.entidad;

import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.catalogo.controller.request.entidad.CatEntidadRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.SelectResponse;
import mx.gob.sat.catalogo.controller.response.aduana.CatEntidadResponse;
import mx.gob.sat.catalogo.controller.response.pais.CatPaisResponse;

import java.util.List;

/**
 * <b>Class:</b> CatEntidadAPIService.java <br>
 * <b>Description:</b>
 * <p>Contrato del servicio API para el catalogo de entidades federativas.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Servicio API
 */
public interface CatEntidadAPIService {

    /**
     * Lista las entidades de forma paginada con soporte de busqueda y ordenamiento.
     *
     * @param pagina Numero de pagina (base 0).
     * @param tamano Cantidad de elementos por pagina.
     * @param busqueda Texto a buscar en nombre o clave (opcional).
     * @param sortBy Columna de ordenamiento (opcional).
     * @param sortDir Direccion de ordenamiento (opcional).
     * @return Pagina de entidades envuelta en BaseResponse.
     */
    BaseResponse<PaginaResponse<CatEntidadResponse>> listar(
            int pagina, int tamano, String busqueda, String sortBy, String sortDir);

    /**
     * Crea una nueva entidad federativa.
     *
     * @param request Datos de la entidad a crear.
     * @return Entidad creada envuelta en BaseResponse.
     */
    BaseResponse<CatEntidadResponse> crear(CatEntidadRequest request);

    /**
     * Busca una entidad por su clave.
     *
     * @param cveEntidad Clave de la entidad.
     * @return Entidad encontrada envuelta en BaseResponse.
     */
    BaseResponse<CatEntidadResponse> findById(String cveEntidad);

    /**
     * Actualiza los campos no nulos de una entidad existente.
     *
     * @param cveEntidad Clave de la entidad a actualizar.
     * @param request Datos a actualizar.
     * @return Entidad actualizada envuelta en BaseResponse.
     */
    BaseResponse<CatEntidadResponse> actualizar(String cveEntidad, CatEntidadRequest request);

    /**
     * Retorna todos los paises disponibles para asociar a una entidad.
     *
     * @return Lista de paises envuelta en BaseResponse.
     */
    BaseResponse<List<CatPaisResponse>> listarPaises();

    /**
     * Retorna lista de entidades en formato clave-nombre para dropdowns.
     *
     * @return Lista de seleccion envuelta en BaseResponse.
     */
    BaseResponse<List<SelectResponse>> listarNombres();
}
