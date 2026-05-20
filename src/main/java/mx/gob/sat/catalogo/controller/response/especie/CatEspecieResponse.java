package mx.gob.sat.catalogo.controller.response.especie;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

/**
 * <b>Class:</b> CatEspecieResponse.java <br>
 * <b>Description:</b>
 * <p>DTO de salida para el catalogo de especies.</p>
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
public class CatEspecieResponse {

    /** Identificador de la especie. */
    private Integer idEspecie;

    /** Descripcion de la especie. */
    private String descEspecie;

    /** Fecha de inicio de vigencia. */
    private LocalDate fecIniVigencia;

    /** Fecha de fin de vigencia. */
    private LocalDate fecFinVigencia;

    /** Indicador de activo. */
    private Boolean blnActivo;
}
