package mx.gob.sat.catalogo.exceptions.business;

/**
 * <b>Class:</b> CatAduanaException.java <br>
 * <b>Description:</b>
 * <p>Excepcion de negocio lanzada cuando ocurre un error relacionado con el catalogo de aduanas.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Excepcion
 */
public class CatAduanaException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /** Codigo de error asociado a la excepcion. */
    private final String codigo;

    /**
     * Construye la excepcion con el codigo de error y el mensaje descriptivo.
     *
     * @param codigo Codigo de error definido en {@code CodigoErrorConst}.
     * @param mensaje Descripcion del error de negocio.
     */
    public CatAduanaException(final String codigo, final String mensaje) {
        super(mensaje);
        this.codigo = codigo;
    }

    /**
     * Retorna el codigo de error asociado a esta excepcion.
     *
     * @return Codigo de error.
     */
    public String getCodigo() {
        return codigo;
    }
}
