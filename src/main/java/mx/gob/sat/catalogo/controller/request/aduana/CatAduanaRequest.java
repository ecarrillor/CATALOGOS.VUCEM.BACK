package mx.gob.sat.catalogo.controller.request.aduana;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

/**
 * <b>Class:</b> CatAduanaRequest.java <br>
 * <b>Description:</b>
 * <p>DTO de entrada para operaciones de creacion y actualizacion del catalogo de aduanas.
 * Los campos son opcionales para permitir actualizaciones parciales (PATCH semantics en PUT).</p>
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
public class CatAduanaRequest {

    /** Clave de la aduana (requerida en creacion, max 3 caracteres). */
    @Size(max = 3, message = "La clave de aduana no puede exceder 3 caracteres")
    private String cveAduana;

    /** Nombre de la aduana (requerido, max 120 caracteres). */
    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 120, message = "El nombre no puede exceder 120 caracteres")
    private String nombre;

    /** Fecha de captura del registro. */
    private Instant fecCaptura;

    /** Fecha de inicio de vigencia (requerida). */
    @NotNull(message = "La fecha de inicio de vigencia es obligatoria")
    private Instant fecIniVigencia;

    /** Fecha de fin de vigencia. */
    private Instant fecFinVigencia;

    /** Indica si el registro esta activo (requerido). */
    @NotNull(message = "El indicador de activo es obligatorio")
    private Boolean blnActivo;

    /** Correo electronico de contacto (max 30 caracteres). */
    @Size(max = 30, message = "El correo electronico no puede exceder 30 caracteres")
    private String correoElectronico;

    /** Clave del tipo de aduana (requerida). */
    @NotBlank(message = "La clave del tipo de aduana es obligatoria")
    private String cveTipoAduana;

    /** Clave de la entidad federativa (requerida). */
    @NotBlank(message = "La clave de la entidad es obligatoria")
    private String cveEntidad;
}
