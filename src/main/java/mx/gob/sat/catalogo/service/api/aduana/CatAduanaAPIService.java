package mx.gob.sat.catalogo.service.api.aduana;

import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.catalogo.controller.request.aduana.CatAduanaRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.aduana.CatAduanaResponse;
import mx.gob.sat.catalogo.controller.response.aduana.CatEntidadResponse;
import mx.gob.sat.catalogo.controller.response.aduana.CatTipoAduanaResponse;

import java.util.List;

/**
 * <b>Class:</b> CatAduanaAPIService.java <br>
 * <b>Description:</b>
 * <p>Contrato del servicio API para el catalogo de aduanas.
 * Define las operaciones de consulta y mantenimiento disponibles.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Servicio API
 */
public interface CatAduanaAPIService {

    /**
     * Lista las aduanas de forma paginada con soporte de busqueda y ordenamiento.
     *
     * @param pagina Numero de pagina (base 0).
     * @param tamano Cantidad de elementos por pagina.
     * @param busqueda Texto a buscar en nombre o clave (opcional).
     * @param sortBy Columna de ordenamiento (opcional).
     * @param sortDir Direccion de ordenamiento: {@code asc} o {@code desc} (opcional).
     * @return Pagina de aduanas envuelta en {@code BaseResponse}.
     */
    BaseResponse<PaginaResponse<CatAduanaResponse>> listarAduanas(
            int pagina, int tamano, String busqueda, String sortBy, String sortDir);

    /**
     * Crea una nueva aduana.
     *
     * @param request Datos de la aduana a crear.
     * @return Aduana creada envuelta en {@code BaseResponse}.
     */
    BaseResponse<CatAduanaResponse> crearAduana(CatAduanaRequest request);

    /**
     * Busca una aduana por su clave.
     *
     * @param cveAduana Clave de la aduana.
     * @return Aduana encontrada envuelta en {@code BaseResponse}.
     */
    BaseResponse<CatAduanaResponse> findByCveAduana(String cveAduana);

    /**
     * Actualiza los campos no nulos de una aduana existente.
     *
     * @param cveAduana Clave de la aduana a actualizar.
     * @param request Datos a actualizar (solo campos no nulos se aplican).
     * @return Aduana actualizada envuelta en {@code BaseResponse}.
     */
    BaseResponse<CatAduanaResponse> actualizarAduana(String cveAduana, CatAduanaRequest request);

    /**
     * Retorna todos los tipos de aduana disponibles.
     *
     * @return Lista de tipos de aduana envuelta en {@code BaseResponse}.
     */
    BaseResponse<List<CatTipoAduanaResponse>> listarTiposAduana();

    /**
     * Retorna todas las entidades federativas disponibles.
     *
     * @return Lista de entidades envuelta en {@code BaseResponse}.
     */
    BaseResponse<List<CatEntidadResponse>> listarEntidades();

    /**
     * Retorna la ultima clave de aduana registrada segun orden descendente.
     *
     * @return Ultima clave de aduana envuelta en {@code BaseResponse}.
     */
    BaseResponse<String> obtenerUltimaCveAduana();
}
