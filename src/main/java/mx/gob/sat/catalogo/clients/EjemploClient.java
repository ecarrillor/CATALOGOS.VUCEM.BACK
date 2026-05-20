package mx.gob.sat.catalogo.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestBody;
import mx.gob.sat.basems.clients.GlobalErrorDecoder;
import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.catalogo.controller.request.EjemploRequest;

/**
 * <b>Class:</b> EjemploClient.java <br>
 * <b>Description:</b>
 * <p> Interfaz que permite consumir los servicios del microservicio ms-ejemplo. </p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 20 de junio del 2025
 * @version 1.0
 * @category Interfaz de consumo de microservicio
 */
@FeignClient(name = "EjemploClient", url = "${ms.ejemplo.url}", configuration = GlobalErrorDecoder.class)
public interface EjemploClient {

    /**
     * Servicio que permite generar un requerimiento.
     *
     * @param numFolioTramite Representa el número de folio de trámite
     * @param request Representa el objeto de entrada {@ode GenerarFirmaRequerimientoRequest}.
     * @return Retorna el estado de la operación.
     */
    @PostMapping(value = "/{numFolioTramite}/requerimiento/generar/firmar",
        produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    BaseResponse<String> firmarGenerarRequerimiento(
        @PathVariable(value = "numFolioTramite") String numFolioTramite, 
        @RequestBody EjemploRequest request);
}
