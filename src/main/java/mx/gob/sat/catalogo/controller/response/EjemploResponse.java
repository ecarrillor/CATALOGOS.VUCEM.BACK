package mx.gob.sat.catalogo.controller.response;

import java.io.Serial;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import mx.gob.sat.basems.controller.global.BaseSerializable;

/**
 * <b>Class:</b> EjemploResponse.java <br>
 * <b>Description:</b>
 * <p>
 * Modelo de salida representa las propiedades del proceso de ejemplo
 * </p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 17 de febrero del 2025
 * @version 1.0
 * @category Modelo de salida
 */
@Getter
@Builder
public class EjemploResponse extends BaseSerializable {
    
    @Serial
    private static final long serialVersionUID = -913195612033181924L;

    /** Representa el nombre del productor. */
    @Schema(name = "nombre", description = "Representa el nombre del productor.")
    @JsonProperty("nombre")
    private String nombre;

    /** Representa el domicilio del productor. */
    @Schema(name = "descripcion_ubicacion", description = "Representa el domicilio del productor.")
    @JsonProperty("descripcion_ubicacion")
    private String domicilio;

    /** Representa la razón social del productor. */
    @Schema(name = "razon_social", description = "Representa la razón social del productor.")
    @JsonProperty("razon_social")
    private String razonSocial;

    /** Representa el tipo de persona del productor. */
    @Schema(name = "tipo_persona", description = "Representa el tipo de persona del productor.")
    @JsonProperty("tipo_persona")
    private boolean tipoPersona;

    /** Representa el apellido paterno del productor. */
    @Schema(name = "apellido_paterno", description = "Representa el apellido paterno del productor.")
    @JsonProperty("apellido_paterno")
    private String apellidoPaterno;

    /** Representa el apellido materno del productor. */
    @Schema(name = "apellido_materno", description = "Representa el apellido materno del productor.")
    @JsonProperty("apellido_materno")
    private String apellidoMaterno;

    /** Representa el RFC del productor. */
    @Schema(name = "rfc", description = "Representa el RFC del productor.")
    @JsonProperty("rfc")
    private String rfc;

    /** Representa el país del productor. */
    @Schema(name = "pais", description = "Representa el país del productor.")
    @JsonProperty("pais")
    private String pais;

}
