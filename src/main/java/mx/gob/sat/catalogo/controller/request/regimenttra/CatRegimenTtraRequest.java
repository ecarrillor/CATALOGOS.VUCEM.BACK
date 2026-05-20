package mx.gob.sat.catalogo.controller.request.regimenttra;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * <b>Class:</b> CatRegimenTtraRequest.java <br>
 * <b>Description:</b>
 * <p>DTO de entrada para el catalogo de regimenes ttra.</p>
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
public class CatRegimenTtraRequest {

    /** Fecha de inicio de vigencia. */
    @NotNull
    private LocalDate fecIniVigencia;

    /** Fecha de fin de vigencia. */
    private LocalDate fecFinVigencia;

    /** Indicador de activo. */
    @NotNull
    private Boolean blnActivo;

    /** Clave del regimen. */
    private String cveRegimen;

    /** Identificador del tipo de tramite. */
    private Long idTipoTramite;
}
