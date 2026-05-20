package mx.gob.sat.catalogo.controller.response.delegmun;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

/**
 * <b>Class:</b> CatDelegMunResponse.java <br>
 * <b>Description:</b>
 * <p>DTO de salida para el catalogo de delegaciones y municipios.</p>
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
public class CatDelegMunResponse {

    /** Clave de la delegacion o municipio. */
    private String cveDelegMun;

    /** Nombre de la delegacion o municipio. */
    private String nombre;

    /** Fecha de captura. */
    private LocalDate fecCaptura;

    /** Fecha de fin de vigencia. */
    private LocalDate fecFinVigencia;

    /** Municipio SAT. */
    private String satMunicipality;

    /** Fecha de inicio de vigencia. */
    private LocalDate fecIniVigencia;

    /** Indicador de activo. */
    private Boolean blnActivo;

    /** Clave de la entidad federativa. */
    private String cveEntidad;

    /** Nombre de la entidad federativa. */
    private String nombreEntidad;
}
