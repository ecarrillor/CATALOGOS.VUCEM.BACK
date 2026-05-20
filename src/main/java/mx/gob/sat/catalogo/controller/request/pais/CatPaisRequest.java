package mx.gob.sat.catalogo.controller.request.pais;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * <b>Class:</b> CatPaisRequest.java <br>
 * <b>Description:</b>
 * <p>DTO de entrada para operaciones de creacion y actualizacion del catalogo de paises.</p>
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
public class CatPaisRequest {

    /** Clave del pais (requerida en creacion, max 3 caracteres). */
    @Size(max = 3, message = "La clave no puede exceder 3 caracteres")
    private String cvePais;

    /** Nombre del pais. */
    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 120, message = "El nombre no puede exceder 120 caracteres")
    private String nombre;

    /** Clave de la moneda asociada al pais. */
    private String cveMoneda;

    /** Clave del pais en formato WCO (max 2 caracteres). */
    @Size(max = 2, message = "La clave WCO no puede exceder 2 caracteres")
    private String cvePaisWco;

    /** Fecha de inicio de vigencia. */
    @NotNull(message = "La fecha de inicio de vigencia es obligatoria")
    private LocalDate fecIniVigencia;

    /** Fecha de fin de vigencia. */
    private LocalDate fecFinVigencia;

    /** Indica si el registro esta activo. */
    private Boolean blnActivo;

    /** Nombre alterno del pais. */
    @Size(max = 120, message = "El nombre alterno no puede exceder 120 caracteres")
    private String nombreAlterno;
}
