package mx.gob.sat.catalogo.controller.response.declaracion;

import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

/**
 * <b>Class:</b> CatDeclaracionResponse.java <br>
 * <b>Description:</b>
 * <p>DTO de salida para el catalogo de declaraciones.</p>
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
public class CatDeclaracionResponse {

    /** Clave de la declaracion. */
    private String cveDeclaracion;

    /** Descripcion de la declaracion. */
    private String descDeclaracion;

    /** Fecha de captura. */
    private Instant fecCaptura;

    /** Fecha de fin de vigencia. */
    private Instant fecFinVigencia;

    /** Fecha de inicio de vigencia. */
    private Instant fecIniVigencia;

    /** Indicador de activo. */
    private Boolean blnActivo;

    /** Clave de referencia. */
    private String cveReferencia;
}
