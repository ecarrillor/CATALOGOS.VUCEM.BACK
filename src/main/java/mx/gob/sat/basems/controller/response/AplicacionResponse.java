package mx.gob.sat.basems.controller.response;

import java.io.Serializable;
import lombok.Builder;
import lombok.Getter;

/**
 * Stub local de AplicacionResponse. Reemplazar con libreria base-ms en CI/CD.
 * Modelo de respuesta con informacion de la aplicacion.
 */
@Getter
@Builder
public class AplicacionResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nombre;
    private String version;
    private String descripcion;
}
