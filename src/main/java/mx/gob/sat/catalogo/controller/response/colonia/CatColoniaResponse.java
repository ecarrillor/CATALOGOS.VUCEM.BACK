package mx.gob.sat.catalogo.controller.response.colonia;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

/**
 * <b>Class:</b> CatColoniaResponse.java <br>
 * <b>Description:</b>
 * <p>DTO de salida para el catalogo de colonias.</p>
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
public class CatColoniaResponse {

    /** Clave de la colonia. */
    private String cveColonia;

    /** Nombre de la colonia. */
    private String nombre;

    /** Fecha de captura. */
    private LocalDate fecCaptura;

    /** Fecha de inicio de vigencia. */
    private LocalDate fecIniVigencia;

    /** Fecha de fin de vigencia. */
    private LocalDate fecFinVigencia;

    /** Codigo postal. */
    private String cp;

    /** Codigo de colonia SAT. */
    private String satColonyCd;

    /** Indicador de activo. */
    private Boolean blnActivo;

    /** Clave de la delegacion o municipio. */
    private String cveDelegMun;

    /** Clave de la localidad. */
    private String cveLocalidad;
}
