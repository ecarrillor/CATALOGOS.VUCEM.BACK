package mx.gob.sat.catalogo.controller.request.scian;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * <b>Class:</b> CatScianRequest.java <br>
 * <b>Description:</b>
 * <p>DTO de entrada para el catalogo SCIAN.</p>
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
public class CatScianRequest {

    /** Clave SCIAN. */
    @Size(max = 6)
    private String cveScian;

    /** Descripcion SCIAN. */
    @Size(max = 250)
    private String descScian;

    /** Fecha de inicio de vigencia. */
    @NotNull
    private LocalDate fecIniVigencia;

    /** Fecha de fin de vigencia. */
    private LocalDate fecFinVigencia;

    /** Indicador de activo. */
    @NotNull
    private Boolean blnActivo;

    /** Giro. */
    @Size(max = 6)
    private String giro;
}
