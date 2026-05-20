package mx.gob.sat.catalogo.controller.request.producto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * <b>Class:</b> CatProductoRequest.java <br>
 * <b>Description:</b>
 * <p>DTO de entrada para el catalogo de productos.</p>
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
public class CatProductoRequest {

    /** Clave del producto. */
    @Size(max = 10)
    private String cveProducto;

    /** Sigla del producto. */
    @Size(max = 3)
    private String sigla;

    /** Nombre del producto. */
    @Size(max = 60)
    private String nombre;

    /** Descripcion del producto. */
    @Size(max = 120)
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
