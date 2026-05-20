package mx.gob.sat.catalogo.controller.response.unidadmedidattra;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

/**
 * <b>Class:</b> CatUnidadMedidaTtraResponse.java <br>
 * <b>Description:</b>
 * <p>DTO de salida para el catalogo de unidad de medida ttra.</p>
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
public class CatUnidadMedidaTtraResponse {

    /** Identificador de la unidad de medida ttra. */
    private Integer idUnidadMedidaTtra;

    /** Clave de la unidad de medida asociada. */
    private String cveUnidadMedida;

    /** Identificador del tipo de tramite. */
    private Long idTipoTramite;

    /** Fecha de inicio de vigencia. */
    private LocalDate fecIniVigencia;

    /** Fecha de fin de vigencia. */
    private LocalDate fecFinVigencia;

    /** Indicador de activo. */
    private Boolean blnActivo;
}
