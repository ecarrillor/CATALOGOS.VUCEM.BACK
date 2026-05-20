package mx.gob.sat.catalogo.model.view;

/**
 * <b>Class:</b> ProductorResumenView.java <br>
 * <b>Description:</b>  
 * <p>
 * Modelo que mapea los datos de la vista <b>vuc_persona_sol</b>.
 * </p>
 * 
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 * 
 * @created 31 de Julio del 2025
 * @version 1.0
 * @category Vista 
 */
public interface ProductorResumenView {

    /**
     * Representa el nombre del productor.
     * 
     * @return String
     */
    String getNombre();

    /**
     * Representa la razón social del productor.
     * 
     * @return String
     */
    String getRazonSocial();

    /**
     * Representa el domicilio del productor.
     * 
     * @return String
     */
    String getDomicilio();

    /**
     * Representa el tipo de persona del productor.
     * 
     * @return boolean
     */
    boolean getTipoPersona();

    /**
     * Representa el apellido paterno del productor.
     * 
     * @return String
     */
    String getApellidoPaterno();

    /**
     * Representa el apellido materno del productor.
     * 
     * @return String
     */
    String getApellidoMaterno();    

    /**
     * Representa el RFC del productor.
     * 
     * @return String
     */
    String getRfc();

    /**
     * Representa el país del productor.
     * 
     * @return String
     */
    String getPais();

}
