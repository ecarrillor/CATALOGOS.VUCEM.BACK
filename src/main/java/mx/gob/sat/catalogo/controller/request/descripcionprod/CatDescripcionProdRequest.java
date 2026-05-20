package mx.gob.sat.catalogo.controller.request.descripcionprod;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * <b>Class:</b> CatDescripcionProdRequest.java <br>
 * <b>Description:</b>
 * <p>DTO de entrada para el catalogo de descripciones de producto.</p>
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
public class CatDescripcionProdRequest {

    /** Descripcion del producto. */
    @Size(max = 256)
    private String descripcionProducto;

    /** Fecha de captura. */
    private LocalDate fecCaptura;

    /** Fecha de inicio de vigencia. */
    @NotNull
    private LocalDate fecIniVigencia;

    /** Fecha de fin de vigencia. */
    private LocalDate fecFinVigencia;

    /** Indicador de activo. */
    @NotNull
    private Boolean blnActivo;
}
