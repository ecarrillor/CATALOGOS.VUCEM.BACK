package mx.gob.sat.catalogo.controller.request.criterioorigen;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

/**
 * <b>Class:</b> CatCriterioOrigenRequest.java <br>
 * <b>Description:</b>
 * <p>DTO de entrada para el catalogo de criterios de origen.</p>
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
public class CatCriterioOrigenRequest {

    /** Clave del criterio de origen. */
    @Size(max = 20)
    private String cveCriterioOrigen;

    /** Nombre del criterio de origen. */
    @Size(max = 50)
    private String nombre;

    /** Fecha de inicio de vigencia. */
    @NotNull
    private Instant fecIniVigencia;

    /** Fecha de fin de vigencia. */
    private Instant fecFinVigencia;

    /** Indicador de activo. */
    @NotNull
    private Boolean blnActivo;
}
