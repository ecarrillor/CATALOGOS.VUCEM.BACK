package mx.gob.sat.catalogo.controller.response.patenteaduanal;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

/**
 * <b>Class:</b> CatPatenteAduanalResponse.java <br>
 * <b>Description:</b>
 * <p>DTO de salida para el catalogo de patentes aduanales.</p>
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
public class CatPatenteAduanalResponse {

    /** Clave de la patente aduanal. */
    private String cvePatenteAduanal;

    /** RFC del agente aduanal. */
    private String rfc;

    /** Fecha de fin de vigencia. */
    private LocalDate fecFinVigencia;

    /** Fecha de inicio de vigencia. */
    private LocalDate fecIniVigencia;

    /** Indicador de activo. */
    private Boolean blnActivo;

    /** Identificador de estado de patente autorizada. */
    private String ideEstPatenteAut;
}
