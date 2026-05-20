package mx.gob.sat.catalogo.controller.response.calendario;

import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

/**
 * <b>Class:</b> CatCalendarioResponse.java <br>
 * <b>Description:</b>
 * <p>DTO de salida para el catalogo de calendarios.</p>
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
public class CatCalendarioResponse {

    /** Clave del calendario. */
    private String cveCalendario;

    /** Nombre del calendario. */
    private String nombre;

    /** Fecha de inicio de vigencia. */
    private Instant fecIniVigencia;

    /** Fecha de fin de vigencia. */
    private Instant fecFinVigencia;

    /** Indicador de activo. */
    private Boolean blnActivo;
}
