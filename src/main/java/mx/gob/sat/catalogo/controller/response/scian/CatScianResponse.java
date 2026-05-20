package mx.gob.sat.catalogo.controller.response.scian;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

/**
 * <b>Class:</b> CatScianResponse.java <br>
 * <b>Description:</b>
 * <p>DTO de salida para el catalogo SCIAN.</p>
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
public class CatScianResponse {

    /** Clave SCIAN. */
    private String cveScian;

    /** Descripcion SCIAN. */
    private String descScian;

    /** Fecha de inicio de vigencia. */
    private LocalDate fecIniVigencia;

    /** Fecha de fin de vigencia. */
    private LocalDate fecFinVigencia;

    /** Indicador de activo. */
    private Boolean blnActivo;

    /** Giro. */
    private String giro;
}
