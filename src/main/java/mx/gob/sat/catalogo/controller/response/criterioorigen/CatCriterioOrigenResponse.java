package mx.gob.sat.catalogo.controller.response.criterioorigen;

import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

/**
 * <b>Class:</b> CatCriterioOrigenResponse.java <br>
 * <b>Description:</b>
 * <p>DTO de salida para el catalogo de criterios de origen.</p>
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
public class CatCriterioOrigenResponse {

    /** Clave del criterio de origen. */
    private String cveCriterioOrigen;

    /** Nombre del criterio de origen. */
    private String nombre;

    /** Fecha de inicio de vigencia. */
    private Instant fecIniVigencia;

    /** Fecha de fin de vigencia. */
    private Instant fecFinVigencia;

    /** Indicador de activo. */
    private Boolean blnActivo;
}
