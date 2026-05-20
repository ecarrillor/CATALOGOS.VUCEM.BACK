package mx.gob.sat.catalogo.controller.response.fraccionhtsusa;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

/**
 * <b>Class:</b> CatFraccionHtsUsaResponse.java <br>
 * <b>Description:</b>
 * <p>DTO de salida para el catalogo de fracciones HTS USA.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Response DTO
 */
@Getter
@Builder
public class CatFraccionHtsUsaResponse {

    /** Identificador de la fraccion HTS USA. */
    private Long idFraccionHtsUsa;

    /** Clave de la fraccion HTS USA. */
    private String cveFraccionHtsUsa;

    /** Descripcion de la fraccion HTS USA. */
    private String descripcion;

    /** Fecha de captura. */
    private LocalDate fecCaptura;

    /** Fecha de inicio de vigencia. */
    private LocalDate fecIniVigencia;

    /** Fecha de fin de vigencia. */
    private LocalDate fecFinVigencia;

    /** Identificador de tipo de bien fraccion. */
    private String ideTipoBienFraccion;

    /** Indicador de exenta. */
    private Boolean blnExenta;

    /** Indicador de activo. */
    private Boolean blnActivo;

    /** Clave de unidad de medida. */
    private String cveUnidadMedida;
}
