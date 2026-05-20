package mx.gob.sat.catalogo.controller.request.semanalaboral;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * <b>Class:</b> CatSemanaLaboralRequest.java <br>
 * <b>Description:</b>
 * <p>DTO de entrada para el catalogo de semanas laborales.</p>
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
public class CatSemanaLaboralRequest {

    /** Descripcion de la semana laboral. */
    @NotBlank
    @Size(max = 100)
    private String descripcion;

    /** Indicador de activo. */
    private Boolean blnActivo;
}
