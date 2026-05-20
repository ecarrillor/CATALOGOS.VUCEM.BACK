package mx.gob.sat.catalogo.controller.response.puntoverificacion;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

/**
 * <b>Class:</b> CatPuntoVerificacionResponse.java <br>
 * <b>Description:</b>
 * <p>DTO de salida para el catalogo de puntos de verificacion.</p>
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
public class CatPuntoVerificacionResponse {

    /** Identificador del punto de verificacion. */
    private Integer idPuntoVerificacion;

    /** Nombre del punto de verificacion. */
    private String nomPuntoVerif;

    /** Fecha de inicio de vigencia. */
    private LocalDate fecIniVigencia;

    /** Fecha de fin de vigencia. */
    private LocalDate fecFinVigencia;

    /** Indicador de activo. */
    private Boolean blnActivo;
}
