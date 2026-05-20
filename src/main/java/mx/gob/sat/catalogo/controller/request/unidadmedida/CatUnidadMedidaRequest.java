package mx.gob.sat.catalogo.controller.request.unidadmedida;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * <b>Class:</b> CatUnidadMedidaRequest.java <br>
 * <b>Description:</b>
 * <p>DTO de entrada para el catalogo de unidades de medida.</p>
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
public class CatUnidadMedidaRequest {

    /** Clave de la unidad de medida. */
    @Size(max = 10)
    private String cveUnidadMedida;

    /** Descripcion de la unidad de medida. */
    @Size(max = 100)
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

    /** Identificador de origen de unidad de medida. */
    @Size(max = 20)
    private String ideOrigenUnidadMedida;

    /** Sigla de la unidad de medida. */
    @Size(max = 50)
    private String sigla;

    /** Identificador OMA. */
    @Size(max = 10)
    private String idOma;

    /** Sigla CBP. */
    @Size(max = 50)
    private String siglaCbp;
}
