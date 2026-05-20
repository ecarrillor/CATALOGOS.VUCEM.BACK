package mx.gob.sat.catalogo.controller.request.aduanattra;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * <b>Class:</b> CatAduanaTtraRequest.java <br>
 * <b>Description:</b>
 * <p>DTO de entrada para el catalogo de aduanas ttra.</p>
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
public class CatAduanaTtraRequest {

    /** Identificador de la aduana ttra. */
    @NotNull
    private Long idAduanaTtra;

    /** Alias de la aduana. */
    @NotNull
    @Size(max = 100)
    private String aliasAduana;

    /** Fecha de captura del registro. */
    private LocalDate fecCaptura;

    /** Fecha de inicio de vigencia. */
    @NotNull
    private LocalDate fecIniVigencia;

    /** Fecha de fin de vigencia. */
    private LocalDate fecFinVigencia;

    /** Indicador de activo. */
    @NotNull
    private Boolean blnActivo;

    /** Clave de la aduana. */
    @NotNull
    private String cveAduana;

    /** Identificador del tipo de tramite. */
    @NotNull
    private Long idTipoTramite;
}
