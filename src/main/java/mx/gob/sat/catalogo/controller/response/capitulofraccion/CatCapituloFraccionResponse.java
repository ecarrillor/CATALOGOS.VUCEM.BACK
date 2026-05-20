package mx.gob.sat.catalogo.controller.response.capitulofraccion;

import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

/**
 * <b>Class:</b> CatCapituloFraccionResponse.java <br>
 * <b>Description:</b>
 * <p>DTO de salida para el catalogo de capitulos de fraccion.</p>
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
public class CatCapituloFraccionResponse {

    /** Clave del capitulo de fraccion. */
    private String cveCapituloFraccion;

    /** Nombre del capitulo de fraccion. */
    private String nombre;

    /** Fecha de inicio de vigencia. */
    private Instant fecIniVigencia;

    /** Fecha de fin de vigencia. */
    private Instant fecFinVigencia;

    /** Indicador de activo. */
    private Boolean blnActivo;
}
