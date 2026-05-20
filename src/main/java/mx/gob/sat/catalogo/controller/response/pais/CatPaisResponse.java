package mx.gob.sat.catalogo.controller.response.pais;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

/**
 * <b>Class:</b> CatPaisResponse.java <br>
 * <b>Description:</b>
 * <p>DTO de salida con la informacion de un pais.</p>
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
public class CatPaisResponse {

    /** Clave del pais. */
    private String cvePais;

    /** Nombre del pais. */
    private String nombre;

    /** Clave de la moneda asociada. */
    private String cveMoneda;

    /** Nombre de la moneda asociada. */
    private String nombreMoneda;

    /** Clave del pais en formato WCO. */
    private String cvePaisWco;

    /** Fecha de inicio de vigencia. */
    private LocalDate fecIniVigencia;

    /** Fecha de fin de vigencia. */
    private LocalDate fecFinVigencia;

    /** Indica si el registro esta activo. */
    private Boolean blnActivo;

    /** Indica si el pais tiene restriccion. */
    private Boolean blnRestriccion;

    /** Nombre alterno del pais. */
    private String nombreAlterno;
}
