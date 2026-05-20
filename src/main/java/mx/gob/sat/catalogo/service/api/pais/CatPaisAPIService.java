package mx.gob.sat.catalogo.service.api.pais;

import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.catalogo.controller.request.pais.CatPaisRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.SelectResponse;
import mx.gob.sat.catalogo.controller.response.pais.CatPaisResponse;

import java.util.List;

/**
 * <b>Class:</b> CatPaisAPIService.java <br>
 * <b>Description:</b>
 * <p>Contrato del servicio API para el catalogo de paises.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Servicio API
 */
public interface CatPaisAPIService {

    /**
     * Lista los paises de forma paginada con filtro de texto y estado.
     * Si el texto de busqueda es "activo" o "inactivo", filtra por {@code blnActivo}.
     *
     * @param pagina Numero de pagina (base 0).
     * @param tamano Cantidad de elementos por pagina.
     * @param busqueda Texto de busqueda o "activo"/"inactivo" para filtrar por estado.
     * @param sortBy Columna de ordenamiento (opcional).
     * @param sortDir Direccion de ordenamiento (opcional).
     * @return Pagina de paises envuelta en BaseResponse.
     */
    BaseResponse<PaginaResponse<CatPaisResponse>> listar(
            int pagina, int tamano, String busqueda, String sortBy, String sortDir);

    /**
     * Busca un pais por su clave.
     *
     * @param cvePais Clave del pais.
     * @return Pais encontrado envuelto en BaseResponse.
     */
    BaseResponse<CatPaisResponse> findById(String cvePais);

    /**
     * Crea un nuevo pais. Asigna automaticamente fecCaptura=hoy, blnActivo=true,
     * blnRestriccion=true y nombreAlterno=nombre.
     *
     * @param request Datos del pais a crear.
     * @return Pais creado envuelto en BaseResponse.
     */
    BaseResponse<CatPaisResponse> crear(CatPaisRequest request);

    /**
     * Actualiza los campos no nulos de un pais existente.
     *
     * @param cvePais Clave del pais a actualizar.
     * @param request Datos a actualizar.
     * @return Pais actualizado envuelto en BaseResponse.
     */
    BaseResponse<CatPaisResponse> actualizar(String cvePais, CatPaisRequest request);

    /**
     * Retorna lista de monedas en formato clave-nombre para dropdowns.
     *
     * @return Lista de seleccion de monedas envuelta en BaseResponse.
     */
    BaseResponse<List<SelectResponse>> listarMonedas();
}
