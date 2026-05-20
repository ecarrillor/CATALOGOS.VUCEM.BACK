package mx.gob.sat.catalogo.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import mx.gob.sat.basems.controller.global.BaseSerializable;

/**
 * <b>Class:</b> EjemploRequest.java <br>
 * <b>Description:</b>
 * <p>
 * Modelo de entrada para el proceso de ejemplo
 * </p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 17 de febrero del 2025
 * @version 1.0
 * @category Modelo de entrada
 */
@Setter
@Getter
public class EjemploRequest extends BaseSerializable {

    private static final long serialVersionUID = 8509739094406785652L;

    /** Representa el tipo de persona. */
    @NotNull(message = "El campo es obligatorio")
    @JsonProperty("tipo_persona")
    private Boolean tipoPersona;

    /** Representa el nombre del productor. */
    @Size(max = 200, message = "El campo no debe exceder los 200 caracteres")
    @Schema(description = "Nombre del productor", example = "Juan")
    @JsonProperty("nombre")
    private String nombre;

    /** Representa el apellido materno. */
    @Schema(description = "Apellido materno", example = "López")
    @JsonProperty("apellido_materno")
    private String apellidoMaterno;

    /** Representa el apellido paterno. */
    @Size(max = 200, message = "El campo no debe exceder los 200 caracteres")
    @Schema(description = "Apellido paterno", example = "Norte")
    @JsonProperty("apellido_paterno")
    private String apellidoPaterno;

    /** Representa la razón social. */
    @Size(max = 250, message = "El campo no debe exceder los 250 caracteres")
    @Schema(description = "Razón social", example = "Aceros Norte")
    @JsonProperty("razon_social")
    private String razonSocial;

    /** Representa la descripción de la ubicación. */
    @NotBlank(message = "El campo es obligatorio")
    @Size(max = 1000, message = "El campo no debe exceder los 1000 caracteres")
    @Schema(description = "Representa la descripción de la ubicación", example = "Calle Acero, No. 123, Col. Centro")
    @JsonProperty("descripcion_ubicacion")
    private String descripcionUbicacion;

    /** Representa el RFC. */
    @NotBlank(message = "El campo es obligatorio")
    @Schema(description = "RFC del productor", example = "AAL0409235E6")
    @JsonProperty("rfc")
    private String rfc;

    /** Representa la clave del país del productor. */
    @NotBlank(message = "El campo es obligatorio")
    @Schema(description = "Clave del país del productor", example = "USA")
    @JsonProperty("pais")
    private String pais;
}