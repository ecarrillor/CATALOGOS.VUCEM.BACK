package mx.gob.sat.catalogo.controller.request.unidadmedidattra;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * <b>Class:</b> CatUnidadMedidaTtraRequest.java <br>
 * <b>Description:</b>
 * <p>DTO de entrada para el catalogo de unidad de medida ttra.</p>
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
public class CatUnidadMedidaTtraRequest {

    /** Identificador de la unidad de medida ttra. */
    private Integer idUnidadMedidaTtra;

    /** Clave de la unidad de medida asociada. */
    @NotNull
    private String cveUnidadMedida;

    /** Identificador del tipo de tramite. */
    @NotNull
    private Long idTipoTramite;

    /** Fecha de inicio de vigencia. */
    @NotNull
    private LocalDate fecIniVigencia;

    /** Fecha de fin de vigencia. */
    private LocalDate fecFinVigencia;

    /** Indicador de activo. */
    @NotNull
    private Boolean blnActivo;
}
