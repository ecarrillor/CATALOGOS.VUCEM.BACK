package mx.gob.sat.basems.exceptions.api;

/**
 * Stub local de MSPeticionInvalidaException. Reemplazar con libreria base-ms en CI/CD.
 * Excepcion lanzada cuando la peticion enviada al microservicio es invalida.
 */
public class MSPeticionInvalidaException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Construye la excepcion con el mensaje dado.
     *
     * @param mensaje Descripcion del error de peticion invalida.
     */
    public MSPeticionInvalidaException(final String mensaje) {
        super(mensaje);
    }
}
