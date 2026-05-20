package mx.gob.sat.catalogo.service.api.sectorprosec;

import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.catalogo.controller.request.sectorprosec.CatSectorProsecRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.sectorprosec.CatSectorProsecResponse;

/**
 * <b>Class:</b> CatSectorProsecAPIService.java <br>
 * <b>Description:</b>
 * <p>Contrato del servicio API para el catalogo de SectorProsec.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Servicio API
 */
public interface CatSectorProsecAPIService {

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
    BaseResponse<PaginaResponse<CatSectorProsecResponse>> listar(
            int pagina, int tamano, String busqueda, String sortBy, String sortDir);

    /**
     * Busca un registro por su identificador.
     *
     * @param cveSectorProsec Identificador del registro.
     * @return Registro encontrado envuelto en BaseResponse.
     */
    BaseResponse<CatSectorProsecResponse> findById(String cveSectorProsec);

    /**
     * Crea un nuevo registro.
     *
     * @param request Datos del registro a crear.
     * @return Registro creado envuelto en BaseResponse.
     */
    BaseResponse<CatSectorProsecResponse> crear(CatSectorProsecRequest request);

    /**
     * Actualiza los campos no nulos de un registro existente.
     *
     * @param cveSectorProsec Identificador del registro a actualizar.
     * @param request Datos a actualizar.
     * @return Registro actualizado envuelto en BaseResponse.
     */
    BaseResponse<CatSectorProsecResponse> actualizar(String cveSectorProsec, CatSectorProsecRequest request);
}
