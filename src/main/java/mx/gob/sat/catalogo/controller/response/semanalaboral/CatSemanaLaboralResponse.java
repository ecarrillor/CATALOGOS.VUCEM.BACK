package mx.gob.sat.catalogo.controller.response.semanalaboral;

import lombok.Builder;
import lombok.Getter;

/**
 * <b>Class:</b> CatSemanaLaboralResponse.java <br>
 * <b>Description:</b>
 * <p>DTO de salida para el catalogo de semanas laborales.</p>
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
public class CatSemanaLaboralResponse {

    /** Identificador de la semana laboral. */
    private Integer idSemanaLaboral;

    /** Descripcion de la semana laboral. */
    private String descripcion;

    /** Indicador de activo. */
    private Boolean blnActivo;
}
