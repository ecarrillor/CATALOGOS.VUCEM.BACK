package mx.gob.sat.catalogo.controller.response.paisrestriccionttra;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

/**
 * <b>Class:</b> CatPaisRestriccionTtraResponse.java <br>
 * <b>Description:</b>
 * <p>DTO de salida para el catalogo de paises restriccion TTRA.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Response DTO
 */
@Getter
@Builder
public class CatPaisRestriccionTtraResponse {

    /** Identificador de pais restriccion TTRA. */
    private Integer idPaisRestriccionTtra;

    /** Identificador de tipo de restriccion pais TTRA. */
    private String ideTipoRestriccionPaisTtra;

    /** Fecha de inicio de vigencia. */
    private LocalDate fecIniVigencia;

    /** Fecha de fin de vigencia. */
    private LocalDate fecFinVigencia;

    /** Indicador de activo. */
    private Boolean blnActivo;

    /** Identificador del tipo de tramite. */
    private Long idTipoTramite;

    /** Clave del pais. */
    private String cvePais;
}
