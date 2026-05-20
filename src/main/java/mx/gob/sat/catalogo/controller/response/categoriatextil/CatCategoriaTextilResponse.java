package mx.gob.sat.catalogo.controller.response.categoriatextil;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

/**
 * <b>Class:</b> CatCategoriaTextilResponse.java <br>
 * <b>Description:</b>
 * <p>DTO de salida para el catalogo de categorias textiles.</p>
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
public class CatCategoriaTextilResponse {

    /** Identificador de la categoria textil. */
    private Long idCategoriaTextil;

    /** Descripcion de la categoria textil. */
    private String descripcion;

    /** Codigo de la categoria textil. */
    private String codCategoriaTextil;

    /** Indicador NPA. */
    private Boolean blnNpa;

    /** Factor de conversion. */
    private Double factConversion;

    /** Fecha de captura. */
    private LocalDate fecCaptura;

    /** Fecha de inicio de vigencia. */
    private LocalDate fecIniVigencia;

    /** Fecha de fin de vigencia. */
    private LocalDate fecFinVigencia;

    /** Fecha de actualizacion. */
    private LocalDate fecActualizacion;

    /** Indicador de activo. */
    private Boolean blnActivo;

    /** Clave de la unidad de medida principal. */
    private String cveUnidadMedida;

    /** Nombre de la unidad de medida principal. */
    private String nombreUnidadMedida;

    /** Clave de la unidad de medida equivalente. */
    private String cveUnidadMedidaEquivalente;

    /** Nombre de la unidad de medida equivalente. */
    private String nombreUnidadMedidaEquivalente;
}
