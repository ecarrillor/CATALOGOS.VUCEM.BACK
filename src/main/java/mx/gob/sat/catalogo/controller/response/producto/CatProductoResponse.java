package mx.gob.sat.catalogo.controller.response.producto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

/**
 * <b>Class:</b> CatProductoResponse.java <br>
 * <b>Description:</b>
 * <p>DTO de salida para el catalogo de productos.</p>
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
public class CatProductoResponse {

    /** Clave del producto. */
    private String cveProducto;

    /** Sigla del producto. */
    private String sigla;

    /** Nombre del producto. */
    private String nombre;

    /** Descripcion del producto. */
    private String descripcion;

    /** Fecha de captura. */
    private LocalDate fecCaptura;

    /** Fecha de fin de vigencia. */
    private LocalDate fecFinVigencia;

    /** Fecha de inicio de vigencia. */
    private LocalDate fecIniVigencia;

    /** Indicador de activo. */
    private Boolean blnActivo;
}
