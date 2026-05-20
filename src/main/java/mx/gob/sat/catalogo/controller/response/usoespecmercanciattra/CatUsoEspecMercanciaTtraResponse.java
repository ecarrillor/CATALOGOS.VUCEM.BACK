package mx.gob.sat.catalogo.controller.response.usoespecmercanciattra;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

/**
 * <b>Class:</b> CatUsoEspecMercanciaTtraResponse.java <br>
 * <b>Description:</b>
 * <p>DTO de salida para el catalogo de uso especial de mercancia ttra.</p>
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
public class CatUsoEspecMercanciaTtraResponse {

    /** Identificador del uso especial de mercancia ttra. */
    private Short idUsoEspecMercanciaTtra;

    /** Descripcion del uso especial de mercancia. */
    private String descUsoEspMercancia;

    /** Fecha de inicio de vigencia. */
    private LocalDate fecIniVigencia;

    /** Fecha de fin de vigencia. */
    private LocalDate fecFinVigencia;

    /** Indicador de activo. */
    private Boolean blnActivo;

    /** Indicador de requerimiento de registro sanitario. */
    private Boolean blnReqRegistroSanitario;

    /** Identificador del tipo de tramite. */
    private Long idTipoTramite;
}
