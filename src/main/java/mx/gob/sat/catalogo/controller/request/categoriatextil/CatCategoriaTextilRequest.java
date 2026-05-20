package mx.gob.sat.catalogo.controller.request.categoriatextil;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * <b>Class:</b> CatCategoriaTextilRequest.java <br>
 * <b>Description:</b>
 * <p>DTO de entrada para el catalogo de categorias textiles.</p>
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
public class CatCategoriaTextilRequest {

    /** Descripcion de la categoria textil. */
    @Size(max = 120)
    private String descripcion;

    /** Codigo de la categoria textil. */
    @Size(max = 20)
    private String codCategoriaTextil;

    /** Indicador NPA. */
    private Boolean blnNpa;

    /** Factor de conversion. */
    private Double factConversion;

    /** Fecha de captura. */
    private LocalDate fecCaptura;

    /** Fecha de inicio de vigencia. */
    @NotNull
    private LocalDate fecIniVigencia;

    /** Fecha de fin de vigencia. */
    private LocalDate fecFinVigencia;

    /** Fecha de actualizacion. */
    private LocalDate fecActualizacion;

    /** Indicador de activo. */
    @NotNull
    private Boolean blnActivo;

    /** Clave de la unidad de medida principal. */
    private String cveUnidadMedida;

    /** Clave de la unidad de medida equivalente. */
    private String cveUnidadMedidaEquivalente;
}
