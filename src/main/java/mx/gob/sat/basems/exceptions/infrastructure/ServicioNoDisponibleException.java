package mx.gob.sat.basems.exceptions.infrastructure;

/**
 * Stub local de ServicioNoDisponibleException. Reemplazar con libreria base-ms en CI/CD.
 * Excepcion lanzada cuando un servicio externo no esta disponible.
 */
public class ServicioNoDisponibleException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Construye la excepcion con el mensaje dado.
     *
     * @param mensaje Descripcion del servicio no disponible.
     */
    public ServicioNoDisponibleException(final String mensaje) {
        super(mensaje);
    }
}
