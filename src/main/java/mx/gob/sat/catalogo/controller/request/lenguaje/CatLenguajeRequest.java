package mx.gob.sat.catalogo.controller.request.lenguaje;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

/**
 * <b>Class:</b> CatLenguajeRequest.java <br>
 * <b>Description:</b>
 * <p>DTO de entrada para el catalogo de lenguajes.</p>
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
public class CatLenguajeRequest {

    /** Clave del lenguaje. */
    @Size(max = 2)
    private String cveLenguaje;

    /** Nombre del lenguaje. */
    @NotBlank
    @Size(max = 128)
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
