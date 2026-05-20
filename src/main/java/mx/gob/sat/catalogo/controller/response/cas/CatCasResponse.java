package mx.gob.sat.catalogo.controller.response.cas;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

/**
 * <b>Class:</b> CatCasResponse.java <br>
 * <b>Description:</b>
 * <p>DTO de salida para el catalogo CAS.</p>
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
public class CatCasResponse {

    /** Identificador del CAS. */
    private Short idCas;

    /** Descripcion del CAS. */
    private String descCas;

    /** Fecha de inicio de vigencia. */
    private LocalDate fecIniVigencia;

    /** Fecha de fin de vigencia. */
    private LocalDate fecFinVigencia;

    /** Indicador de activo. */
    private Boolean blActivo;
}
