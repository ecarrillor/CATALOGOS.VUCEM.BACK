package mx.gob.sat.basems.service.api.aplicacion;

import mx.gob.sat.basems.controller.response.AplicacionResponse;

/**
 * Stub local de AplicacionAPIService. Reemplazar con libreria base-ms en CI/CD.
 * Interfaz que define el servicio de informacion de la aplicacion.
 */
public interface AplicacionAPIService {

    /**
     * Retorna informacion general del microservicio.
     *
     * @return Informacion de nombre y version de la aplicacion.
     */
    AplicacionResponse getInformacionMS();
}
