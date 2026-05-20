package mx.gob.sat.catalogo.controller.response.identificadorprevalidador;

import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

/**
 * <b>Class:</b> CatIdentificadorPrevalidadorResponse.java <br>
 * <b>Description:</b>
 * <p>DTO de salida para el catalogo de identificadores de prevalidador.</p>
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
public class CatIdentificadorPrevalidadorResponse {

    /** Identificador del identificador de prevalidador. */
    private Long idIdentificadorPrevalidador;

    /** Caracter de identificacion. */
    private String caracterIdentificacion;

    /** Fecha de inicio de vigencia. */
    private Instant fecIniVigencia;

    /** Fecha de fin de vigencia. */
    private Instant fecFinVigencia;

    /** Indicador de utilizado. */
    private Boolean blnUtilizado;

    /** Indicador de activo. */
    private Boolean blnActivo;
}
