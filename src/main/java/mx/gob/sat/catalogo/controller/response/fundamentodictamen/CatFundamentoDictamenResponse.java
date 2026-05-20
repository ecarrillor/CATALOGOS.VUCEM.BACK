package mx.gob.sat.catalogo.controller.response.fundamentodictamen;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

/**
 * <b>Class:</b> CatFundamentoDictamenResponse.java <br>
 * <b>Description:</b>
 * <p>DTO de salida para el catalogo de fundamentos de dictamen.</p>
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
public class CatFundamentoDictamenResponse {

    /** Identificador del fundamento de dictamen. */
    private Long idFundamentoDictamen;

    /** Descripcion del fundamento de dictamen. */
    private String descripcion;

    /** Indicador de sentido del fundamento. */
    private Boolean blnSentidoFundamento;

    /** Fecha de inicio de vigencia. */
    private LocalDate fecIniVigencia;

    /** Fecha de fin de vigencia. */
    private LocalDate fecFinVigencia;

    /** Indicador de activo. */
    private Boolean blnActivo;
}
