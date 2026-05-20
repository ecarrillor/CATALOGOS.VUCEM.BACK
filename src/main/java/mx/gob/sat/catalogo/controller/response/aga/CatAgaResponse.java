package mx.gob.sat.catalogo.controller.response.aga;

import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

/**
 * <b>Class:</b> CatAgaResponse.java <br>
 * <b>Description:</b>
 * <p>DTO de salida para el catalogo de AGA.</p>
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
public class CatAgaResponse {

    /** Clave del parametro AGA. */
    private String cveParametro;

    /** Descripcion del parametro. */
    private String descripcion;

    /** Valor del parametro. */
    private String valor;

    /** Fecha de inicio de vigencia. */
    private Instant fecIniVigencia;

    /** Fecha de fin de vigencia. */
    private Instant fecFinVigencia;

    /** Indicador de activo. */
    private Boolean blnActivo;
}
