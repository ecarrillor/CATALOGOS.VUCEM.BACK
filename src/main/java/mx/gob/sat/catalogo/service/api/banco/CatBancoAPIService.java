package mx.gob.sat.catalogo.service.api.banco;

import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.catalogo.controller.request.banco.CatBancoRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.banco.CatBancoResponse;

/**
 * <b>Class:</b> CatBancoAPIService.java <br>
 * <b>Description:</b>
 * <p>Contrato del servicio API para el catalogo de bancos.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Servicio API
 */
public interface CatBancoAPIService {

    /**
     * Lista los bancos activos de forma paginada con soporte de busqueda y ordenamiento.
     *
     * @param pagina Numero de pagina (base 0).
     * @param tamano Cantidad de elementos por pagina.
     * @param busqueda Texto a buscar en nombre o clave (opcional).
     * @param sortBy Columna de ordenamiento (opcional).
     * @param sortDir Direccion de ordenamiento (opcional).
     * @return Pagina de bancos envuelta en BaseResponse.
     */
    BaseResponse<PaginaResponse<CatBancoResponse>> listar(
            int pagina, int tamano, String busqueda, String sortBy, String sortDir);

    /**
     * Busca un banco por su clave.
     *
     * @param cveBanco Clave del banco.
     * @return Banco encontrado envuelto en BaseResponse.
     */
    BaseResponse<CatBancoResponse> findById(String cveBanco);

    /**
     * Crea un nuevo banco.
     *
     * @param request Datos del banco a crear.
     * @return Banco creado envuelto en BaseResponse.
     */
    BaseResponse<CatBancoResponse> crear(CatBancoRequest request);

    /**
     * Actualiza los campos no nulos de un banco existente.
     *
     * @param cveBanco Clave del banco a actualizar.
     * @param request Datos a actualizar.
     * @return Banco actualizado envuelto en BaseResponse.
     */
    BaseResponse<CatBancoResponse> actualizar(String cveBanco, CatBancoRequest request);
}
