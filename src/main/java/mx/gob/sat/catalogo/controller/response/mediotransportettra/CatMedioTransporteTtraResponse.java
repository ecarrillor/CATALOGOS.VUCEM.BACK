package mx.gob.sat.catalogo.controller.response.mediotransportettra;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

/**
 * <b>Class:</b> CatMedioTransporteTtraResponse.java <br>
 * <b>Description:</b>
 * <p>DTO de salida para el catalogo de medios de transporte ttra.</p>
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
public class CatMedioTransporteTtraResponse {

    /** Identificador del medio de transporte ttra. */
    private Integer idMedioTransporteTtra;

    /** Identificador del medio de transporte en gobierno. */
    private String ideMedioTransporteGob;

    /** Fecha de inicio de vigencia. */
    private LocalDate fecIniVigencia;

    /** Fecha de fin de vigencia. */
    private LocalDate fecFinVigencia;

    /** Indicador de activo. */
    private Boolean blnActivo;

    /** Identificador del tipo de tramite. */
    private Long idTipoTramite;
}
