package mx.gob.sat.catalogo.controller.response.lenguaje;

import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

/**
 * <b>Class:</b> CatLenguajeResponse.java <br>
 * <b>Description:</b>
 * <p>DTO de salida para el catalogo de lenguajes.</p>
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
public class CatLenguajeResponse {

    /** Clave del lenguaje. */
    private String cveLenguaje;

    /** Nombre del lenguaje. */
    private String nombre;

    /** Fecha de inicio de vigencia. */
    private Instant fecIniVigencia;

    /** Fecha de fin de vigencia. */
    private Instant fecFinVigencia;

    /** Indicador de activo. */
    private Boolean blnActivo;
}
