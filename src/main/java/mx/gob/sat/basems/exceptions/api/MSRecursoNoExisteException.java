package mx.gob.sat.basems.exceptions.api;

/**
 * Stub local de MSRecursoNoExisteException. Reemplazar con libreria base-ms en CI/CD.
 * Excepcion lanzada cuando el recurso solicitado no existe en el microservicio.
 */
public class MSRecursoNoExisteException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Construye la excepcion con el mensaje dado.
     *
     * @param mensaje Descripcion del recurso no encontrado.
     */
    public MSRecursoNoExisteException(final String mensaje) {
        super(mensaje);
    }
}
