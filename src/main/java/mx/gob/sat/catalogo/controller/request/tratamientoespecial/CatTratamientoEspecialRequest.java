package mx.gob.sat.catalogo.controller.request.tratamientoespecial;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * <b>Class:</b> CatTratamientoEspecialRequest.java <br>
 * <b>Description:</b>
 * <p>DTO de entrada para el catalogo de tratamientos especiales.</p>
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
public class CatTratamientoEspecialRequest {

    /** Nombre del tratamiento especial. */
    @Size(max = 120)
    private String nombre;

    /** Fecha de inicio de vigencia. */
    @NotNull
    private LocalDate fecIniVigencia;

    /** Fecha de fin de vigencia. */
    private LocalDate fecFinVigencia;

    /** Indicador de activo. */
    @NotNull
    private Boolean blnActivo;

    /** Identificador de clasificacion de tratamiento. */
    @Size(max = 20)
    private String ideClasifTratamiento;
}
