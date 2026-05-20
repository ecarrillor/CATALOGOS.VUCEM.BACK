package mx.gob.sat.catalogo.controller.request.fraccionhtsusa;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * <b>Class:</b> CatFraccionHtsUsaRequest.java <br>
 * <b>Description:</b>
 * <p>DTO de entrada para el catalogo de fracciones HTS USA.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Request DTO
 */
@Getter
@Setter
public class CatFraccionHtsUsaRequest {

    /** Clave de la fraccion HTS USA. */
    @NotNull
    @Size(max = 10)
    private String cveFraccionHtsUsa;

    /** Descripcion de la fraccion HTS USA. */
    @Size(max = 1000)
    private String descripcion;

    /** Fecha de captura. */
    private LocalDate fecCaptura;

    /** Fecha de inicio de vigencia. */
    @NotNull
    private LocalDate fecIniVigencia;

    /** Fecha de fin de vigencia. */
    private LocalDate fecFinVigencia;

    /** Identificador de tipo de bien fraccion. */
    @Size(max = 20)
    private String ideTipoBienFraccion;

    /** Indicador de exenta. */
    private Boolean blnExenta;

    /** Indicador de activo. */
    @NotNull
    private Boolean blnActivo;

    /** Clave de unidad de medida. */
    private String cveUnidadMedida;
}
