package mx.gob.sat.catalogo.controller.request.patenteaduanal;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * <b>Class:</b> CatPatenteAduanalRequest.java <br>
 * <b>Description:</b>
 * <p>DTO de entrada para el catalogo de patentes aduanales.</p>
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
public class CatPatenteAduanalRequest {

    /** Clave de la patente aduanal. */
    @Size(max = 4)
    private String cvePatenteAduanal;

    /** RFC del agente aduanal. */
    @Size(max = 13)
    private String rfc;

    /** Fecha de fin de vigencia. */
    private LocalDate fecFinVigencia;

    /** Fecha de inicio de vigencia. */
    @NotNull
    private LocalDate fecIniVigencia;

    /** Indicador de activo. */
    @NotNull
    private Boolean blnActivo;

    /** Identificador de estado de patente autorizada. */
    @Size(max = 20)
    private String ideEstPatenteAut;
}
