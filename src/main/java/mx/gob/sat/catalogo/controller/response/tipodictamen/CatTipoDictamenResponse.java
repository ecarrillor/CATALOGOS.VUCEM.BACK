package mx.gob.sat.catalogo.controller.response.tipodictamen;

import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

/**
 * <b>Class:</b> CatTipoDictamenResponse.java <br>
 * <b>Description:</b>
 * <p>DTO de salida para el catalogo de tipos de dictamen.</p>
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
public class CatTipoDictamenResponse {

    /** Identificador del tipo de dictamen. */
    private Long idTipoDictamen;

    /** Nombre del tipo de dictamen. */
    private String nombre;

    /** Orden del tipo de dictamen. */
    private Short orden;

    /** Fecha de inicio de vigencia. */
    private Instant fecIniVigencia;

    /** Fecha de fin de vigencia. */
    private Instant fecFinVigencia;

    /** Indicador de activo. */
    private Boolean blnActivo;
}
