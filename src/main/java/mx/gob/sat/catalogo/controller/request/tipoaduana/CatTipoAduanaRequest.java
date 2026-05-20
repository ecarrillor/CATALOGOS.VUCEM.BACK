package mx.gob.sat.catalogo.controller.request.tipoaduana;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

/**
 * <b>Class:</b> CatTipoAduanaRequest.java <br>
 * <b>Description:</b>
 * <p>DTO de entrada para operaciones de creacion y actualizacion del catalogo de tipos de aduana.</p>
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
public class CatTipoAduanaRequest {

    /** Clave del tipo de aduana (requerida en creacion, max 2 caracteres). */
    @Size(max = 2, message = "La clave no puede exceder 2 caracteres")
    private String cveTipoAduana;

    /** Nombre del tipo de aduana. */
    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 30, message = "El nombre no puede exceder 30 caracteres")
    private String nombre;

    /** Fecha de inicio de vigencia. */
    @NotNull(message = "La fecha de inicio de vigencia es obligatoria")
    private Instant fecIniVigencia;

    /** Fecha de fin de vigencia. */
    private Instant fecFinVigencia;

    /** Indica si el registro esta activo. */
    @NotNull(message = "El indicador de activo es obligatorio")
    private Boolean blnActivo;
}
