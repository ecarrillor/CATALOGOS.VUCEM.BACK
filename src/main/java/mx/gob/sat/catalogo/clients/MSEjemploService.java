package mx.gob.sat.catalogo.clients;

import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.catalogo.controller.request.EjemploRequest;

/**
 * <b>Class:</b> MSEjemploService.java <br>
 * <b>Description:</b>
 * <p> Interfaz que permite accesar a los servicios del microservicio ms-tramite-flujo. </p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 01 de julio del 2025
 * @version 1.0
 * @category Interfaz de servicio
 */
public interface MSEjemploService {
    
    /**
     * Servicio que permite la generación y firmado de un requerimiento.
     * 
     * @param numFolioTramite Representa el numero de folio del tramite.
     * @param request Representa el objeto de entrada {@ode GenerarFirmaRequerimientoRequest}.
     * @return Retorna el estado de la operación.
     */
    BaseResponse<String> firmarGenerarRequerimiento(String numFolioTramite, 
        EjemploRequest request);
}
