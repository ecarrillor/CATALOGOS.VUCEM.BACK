package mx.gob.sat.basems.exceptions.validation;

/**
 * Stub local de ModeloInvalidoException. Reemplazar con libreria base-ms en CI/CD.
 * Excepcion lanzada cuando el modelo de datos recibido no cumple con la estructura esperada.
 */
public class ModeloInvalidoException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Construye la excepcion con el mensaje dado.
     *
     * @param mensaje Descripcion del modelo invalido.
     */
    public ModeloInvalidoException(final String mensaje) {
        super(mensaje);
    }
}
