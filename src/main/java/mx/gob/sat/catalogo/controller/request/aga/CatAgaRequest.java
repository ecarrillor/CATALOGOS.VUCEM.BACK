package mx.gob.sat.catalogo.controller.request.aga;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

/**
 * <b>Class:</b> CatAgaRequest.java <br>
 * <b>Description:</b>
 * <p>DTO de entrada para el catalogo de AGA.</p>
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
public class CatAgaRequest {

    /** Clave del parametro AGA. */
    @Size(max = 30)
    private String cveParametro;

    /** Descripcion del parametro. */
    @NotBlank
    @Size(max = 200)
    private String descripcion;

    /** Valor del parametro. */
    @NotBlank
    @Size(max = 4000)
    private String valor;

    /** Fecha de inicio de vigencia. */
    @NotNull
    private Instant fecIniVigencia;

    /** Fecha de fin de vigencia. */
    private Instant fecFinVigencia;

    /** Indicador de activo. */
    @NotNull
    private Boolean blnActivo;
}
