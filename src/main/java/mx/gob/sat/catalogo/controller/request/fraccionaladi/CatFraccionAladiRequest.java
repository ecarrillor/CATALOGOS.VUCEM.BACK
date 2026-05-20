package mx.gob.sat.catalogo.controller.request.fraccionaladi;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * <b>Class:</b> CatFraccionAladiRequest.java <br>
 * <b>Description:</b>
 * <p>DTO de entrada para el catalogo de fracciones ALADI.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Request DTO
 */
@Getter
@Setter
public class CatFraccionAladiRequest {

    /** Tipo de fraccion ALADI. */
    @Size(max = 20)
    private String ideTipoFraccionAladi;

    /** Clave de la fraccion. */
    @NotBlank
    @Size(max = 8)
    private String cveFraccion;

    /** Descripcion de la fraccion ALADI. */
    @Size(max = 1000)
    private String descripcion;

    /** Fecha de captura. */
    private LocalDate fecCaptura;

    /** Fecha de fin de vigencia. */
    private LocalDate fecFinVigencia;

    /** Fecha de inicio de vigencia. */
    @NotNull
    private LocalDate fecIniVigencia;

    /** Indicador de activo. */
    @NotNull
    private Boolean blnActivo;
}
