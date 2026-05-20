package mx.gob.sat.catalogo.controller.response.aduanattra;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

/**
 * <b>Class:</b> CatAduanaTtraResponse.java <br>
 * <b>Description:</b>
 * <p>DTO de salida para el catalogo de aduanas ttra.</p>
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
public class CatAduanaTtraResponse {

    /** Identificador de la aduana ttra. */
    private Long idAduanaTtra;

    /** Alias de la aduana. */
    private String aliasAduana;

    /** Fecha de captura del registro. */
    private LocalDate fecCaptura;

    /** Fecha de inicio de vigencia. */
    private LocalDate fecIniVigencia;

    /** Fecha de fin de vigencia. */
    private LocalDate fecFinVigencia;

    /** Indicador de activo. */
    private Boolean blnActivo;

    /** Clave de la aduana. */
    private String cveAduana;

    /** Identificador del tipo de tramite. */
    private Long idTipoTramite;
}
