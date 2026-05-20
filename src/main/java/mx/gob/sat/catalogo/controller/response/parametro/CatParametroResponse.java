package mx.gob.sat.catalogo.controller.response.parametro;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

/**
 * <b>Class:</b> CatParametroResponse.java <br>
 * <b>Description:</b>
 * <p>DTO de salida para el catalogo de parametros del sistema.</p>
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
public class CatParametroResponse {

    /** Clave del parametro. */
    private String cveParametro;

    /** Descripcion del parametro. */
    private String descripcion;

    /** Valor del parametro. */
    private String valor;

    /** Identificador de la dependencia asociada. */
    private Long idDependencia;

    /** Nombre de la dependencia asociada. */
    private String dependencia;

    /** Fecha de inicio de vigencia. */
    private LocalDate fecIniVigencia;

    /** Fecha de fin de vigencia. */
    private LocalDate fecFinVigencia;

    /** Indicador de activo. */
    private Boolean blnActivo;
}
