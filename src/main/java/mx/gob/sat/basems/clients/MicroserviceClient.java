package mx.gob.sat.basems.clients;

import mx.gob.sat.basems.controller.response.BaseResponse;
import java.util.function.Supplier;

/**
 * Stub local de MicroserviceClient. Reemplazar con libreria base-ms en CI/CD.
 * Clase base para clientes de microservicios con manejo de errores estandar.
 */
public abstract class MicroserviceClient {

    private final String nombreAplicacion;

    protected MicroserviceClient(final String nombreAplicacion) {
        this.nombreAplicacion = nombreAplicacion;
    }

    protected <T> BaseResponse<T> ejecutar(final String metodo, final Supplier<BaseResponse<T>> accion) {
        try {
            return accion.get();
        } catch (Exception ex) {
            BaseResponse<T> response = new BaseResponse<>();
            response.setCodigo("99");
            response.setMensaje("Error en microservicio: " + nombreAplicacion + " / " + metodo);
            response.setError(ex.getMessage());
            return response;
        }
    }
}
