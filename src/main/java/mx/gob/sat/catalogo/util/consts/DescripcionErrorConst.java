package mx.gob.sat.catalogo.util.consts;

/**
 * <b>Class:</b> DescripcionErrorConst.java <br>
 * <b>Description:</b>
 * <p>Contiene las descripciones de error de la excepciones que maneja la aplicación.
 * </p>
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 04 de julio del 2025
 * @version 1.0
 * @category Constantes
 */
public final class DescripcionErrorConst {
    
    private DescripcionErrorConst() {
        throw new IllegalStateException("Clase DescripcionErrorConst no puede ser instanciada");
    }

    /** Representa el mensaje de error. */
    public static final String PERSONA_NO_EXISTE = "No existe la persona";
    
}
