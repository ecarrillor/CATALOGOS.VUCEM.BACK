package mx.gob.sat.catalogo.controller.response.aduana;

import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

/**
 * <b>Class:</b> CatEntidadResponse.java <br>
 * <b>Description:</b>
 * <p>DTO de salida con la informacion de una entidad federativa.</p>
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
public class CatEntidadResponse {

    /** Clave de la entidad. */
    private String cveEntidad;

    /** Nombre de la entidad. */
    private String nombre;

    /** Fecha de inicio de vigencia. */
    private Instant fecIniVigencia;

    /** Fecha de fin de vigencia. */
    private Instant fecFinVigencia;

    /** Indica si el registro esta activo. */
    private Boolean blnActivo;

    /** Clave del pais al que pertenece la entidad. */
    private String cvePais;
}
