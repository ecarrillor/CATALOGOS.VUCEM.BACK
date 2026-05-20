package mx.gob.sat.catalogo.controller.response.unidadmedida;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

/**
 * <b>Class:</b> CatUnidadMedidaResponse.java <br>
 * <b>Description:</b>
 * <p>DTO de salida para el catalogo de unidades de medida.</p>
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
public class CatUnidadMedidaResponse {

    /** Clave de la unidad de medida. */
    private String cveUnidadMedida;

    /** Descripcion de la unidad de medida. */
    private String descripcion;

    /** Fecha de captura. */
    private LocalDate fecCaptura;

    /** Fecha de fin de vigencia. */
    private LocalDate fecFinVigencia;

    /** Fecha de inicio de vigencia. */
    private LocalDate fecIniVigencia;

    /** Indicador de activo. */
    private Boolean blnActivo;

    /** Identificador de origen de unidad de medida. */
    private String ideOrigenUnidadMedida;

    /** Sigla de la unidad de medida. */
    private String sigla;

    /** Identificador OMA. */
    private String idOma;

    /** Sigla CBP. */
    private String siglaCbp;
}
