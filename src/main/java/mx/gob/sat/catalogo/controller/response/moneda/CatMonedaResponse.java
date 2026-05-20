package mx.gob.sat.catalogo.controller.response.moneda;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

/**
 * <b>Class:</b> CatMonedaResponse.java <br>
 * <b>Description:</b>
 * <p>DTO de salida con la informacion de una moneda.</p>
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
public class CatMonedaResponse {

    /** Clave de la moneda. */
    private String cveMoneda;

    /** Nombre de la moneda. */
    private String nombre;

    /** Fecha de captura del registro. */
    private LocalDate fecCaptura;

    /** Fecha de inicio de vigencia. */
    private LocalDate fecIniVigencia;

    /** Fecha de fin de vigencia. */
    private LocalDate fecFinVigencia;

    /** Indica si el registro esta activo. */
    private Boolean blnActivo;
}
