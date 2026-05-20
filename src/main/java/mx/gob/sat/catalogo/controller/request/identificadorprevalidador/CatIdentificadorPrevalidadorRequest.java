package mx.gob.sat.catalogo.controller.request.identificadorprevalidador;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

/**
 * <b>Class:</b> CatIdentificadorPrevalidadorRequest.java <br>
 * <b>Description:</b>
 * <p>DTO de entrada para el catalogo de identificadores de prevalidador.</p>
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
public class CatIdentificadorPrevalidadorRequest {

    /** Caracter de identificacion. */
    @NotNull
    @Size(max = 1)
    private String caracterIdentificacion;

    /** Fecha de inicio de vigencia. */
    @NotNull
    private Instant fecIniVigencia;

    /** Fecha de fin de vigencia. */
    private Instant fecFinVigencia;

    /** Indicador de utilizado. */
    @NotNull
    private Boolean blnUtilizado;

    /** Indicador de activo. */
    @NotNull
    private Boolean blnActivo;
}
