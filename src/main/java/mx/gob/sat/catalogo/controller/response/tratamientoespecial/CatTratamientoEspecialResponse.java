package mx.gob.sat.catalogo.controller.response.tratamientoespecial;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

/**
 * <b>Class:</b> CatTratamientoEspecialResponse.java <br>
 * <b>Description:</b>
 * <p>DTO de salida para el catalogo de tratamientos especiales.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Response DTO
 */
@Getter
@Builder
public class CatTratamientoEspecialResponse {

    /** Identificador del tratamiento especial. */
    private Short idTratamientoEspecial;

    /** Nombre del tratamiento especial. */
    private String nombre;

    /** Fecha de inicio de vigencia. */
    private LocalDate fecIniVigencia;

    /** Fecha de fin de vigencia. */
    private LocalDate fecFinVigencia;

    /** Indicador de activo. */
    private Boolean blnActivo;

    /** Identificador de clasificacion de tratamiento. */
    private String ideClasifTratamiento;
}
