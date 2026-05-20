package mx.gob.sat.catalogo.controller.response.localidad;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

/**
 * <b>Class:</b> CatLocalidadResponse.java <br>
 * <b>Description:</b>
 * <p>DTO de salida para el catalogo de localidades.</p>
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
public class CatLocalidadResponse {

    /** Clave de la localidad. */
    private String cveLocalidad;

    /** Nombre de la localidad. */
    private String nombre;

    /** Fecha de captura. */
    private LocalDate fecCaptura;

    /** Fecha de inicio de vigencia. */
    private LocalDate fecIniVigencia;

    /** Fecha de fin de vigencia. */
    private LocalDate fecFinVigencia;

    /** Codigo postal. */
    private String cp;

    /** Codigo de localidad SAT. */
    private String satTownCode;

    /** Indicador de activo. */
    private Boolean blnActivo;

    /** Clave de la delegacion o municipio. */
    private String cveDelegMun;
}
