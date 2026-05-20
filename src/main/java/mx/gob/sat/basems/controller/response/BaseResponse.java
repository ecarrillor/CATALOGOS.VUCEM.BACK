package mx.gob.sat.basems.controller.response;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * Stub local de BaseResponse. Reemplazar con libreria base-ms en CI/CD.
 * Contenedor generico de respuesta estandar para todos los endpoints.
 *
 * @param <T> Tipo del resultado
 */
@Getter
@Setter
public class BaseResponse<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private String codigo;
    private String mensaje;
    private String error;
    private String causa;
    private T resultado;
}
