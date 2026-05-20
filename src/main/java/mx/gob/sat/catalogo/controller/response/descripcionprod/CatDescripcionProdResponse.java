package mx.gob.sat.catalogo.controller.response.descripcionprod;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

/**
 * <b>Class:</b> CatDescripcionProdResponse.java <br>
 * <b>Description:</b>
 * <p>DTO de salida para el catalogo de descripciones de producto.</p>
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
public class CatDescripcionProdResponse {

    /** Identificador de la descripcion de producto. */
    private Integer idDescripcionProd;

    /** Descripcion del producto. */
    private String descripcionProducto;

    /** Fecha de captura. */
    private LocalDate fecCaptura;

    /** Fecha de inicio de vigencia. */
    private LocalDate fecIniVigencia;

    /** Fecha de fin de vigencia. */
    private LocalDate fecFinVigencia;

    /** Indicador de activo. */
    private Boolean blnActivo;
}
