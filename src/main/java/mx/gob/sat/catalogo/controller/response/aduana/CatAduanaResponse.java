package mx.gob.sat.catalogo.controller.response.aduana;

import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

/**
 * <b>Class:</b> CatAduanaResponse.java <br>
 * <b>Description:</b>
 * <p>DTO de salida con la informacion completa de una aduana.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Response DTO
 */
@Getter
@Builder
public class CatAduanaResponse {

    /** Clave de la aduana. */
    private String cveAduana;

    /** Nombre de la aduana. */
    private String nombre;

    /** Fecha de captura del registro. */
    private Instant fecCaptura;

    /** Fecha de inicio de vigencia. */
    private Instant fecIniVigencia;

    /** Fecha de fin de vigencia. */
    private Instant fecFinVigencia;

    /** Indica si el registro esta activo. */
    private Boolean blnActivo;

    /** Correo electronico de contacto. */
    private String correoElectronico;

    /** Informacion del tipo de aduana. */
    private CatTipoAduanaResponse tipoAduana;

    /** Informacion de la entidad federativa. */
    private CatEntidadResponse entidad;
}
