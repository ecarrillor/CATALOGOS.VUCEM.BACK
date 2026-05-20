package mx.gob.sat.catalogo.controller.request.moneda;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * <b>Class:</b> CatMonedaRequest.java <br>
 * <b>Description:</b>
 * <p>DTO de entrada para operaciones de creacion y actualizacion del catalogo de monedas.</p>
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
public class CatMonedaRequest {

    /** Clave de la moneda (requerida en creacion, max 3 caracteres). */
    @Size(max = 3, message = "La clave no puede exceder 3 caracteres")
    private String cveMoneda;

    /** Nombre de la moneda. */
    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 120, message = "El nombre no puede exceder 120 caracteres")
    private String nombre;

    /** Fecha de captura del registro. */
    private LocalDate fecCaptura;

    /** Fecha de inicio de vigencia. */
    @NotNull(message = "La fecha de inicio de vigencia es obligatoria")
    private LocalDate fecIniVigencia;

    /** Fecha de fin de vigencia. */
    private LocalDate fecFinVigencia;

    /** Indica si el registro esta activo. */
    @NotNull(message = "El indicador de activo es obligatorio")
    private Boolean blnActivo;
}
