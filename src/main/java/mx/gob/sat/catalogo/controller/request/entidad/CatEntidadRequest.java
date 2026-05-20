package mx.gob.sat.catalogo.controller.request.entidad;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

/**
 * <b>Class:</b> CatEntidadRequest.java <br>
 * <b>Description:</b>
 * <p>DTO de entrada para operaciones de creacion y actualizacion del catalogo de entidades federativas.</p>
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
public class CatEntidadRequest {

    /** Clave de la entidad (requerida en creacion, max 6 caracteres). */
    @Size(max = 6, message = "La clave no puede exceder 6 caracteres")
    private String cveEntidad;

    /** Nombre de la entidad. */
    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 120, message = "El nombre no puede exceder 120 caracteres")
    private String nombre;

    /** Codigo de entidad IDC (max 2 caracteres). */
    @Size(max = 2, message = "El codigo entidad IDC no puede exceder 2 caracteres")
    private String codEntidadIdc;

    /** Fecha de inicio de vigencia. */
    @NotNull(message = "La fecha de inicio de vigencia es obligatoria")
    private Instant fecIniVigencia;

    /** Fecha de fin de vigencia. */
    private Instant fecFinVigencia;

    /** Fecha de captura del registro. */
    private Instant fecCaptura;

    /** Indica si el registro esta activo. */
    @NotNull(message = "El indicador de activo es obligatorio")
    private Boolean blnActivo;

    /** Clave del pais al que pertenece la entidad. */
    private String cvePais;
}
