package mx.gob.sat.catalogo.controller.response.regimen;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

/**
 * <b>Class:</b> CatRegimenResponse.java <br>
 * <b>Description:</b>
 * <p>DTO de salida para el catalogo de regimenes.</p>
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
public class CatRegimenResponse {

    /** Clave del regimen. */
    private String cveRegimen;

    /** Nombre del regimen. */
    private String nombre;

    /** Fecha de inicio de vigencia. */
    private LocalDate fecIniVigencia;

    /** Fecha de fin de vigencia. */
    private LocalDate fecFinVigencia;

    /** Indicador de activo. */
    private Boolean blnActivo;

    /** Clave de enumeracion. */
    private String cveEnumeracion;
}
