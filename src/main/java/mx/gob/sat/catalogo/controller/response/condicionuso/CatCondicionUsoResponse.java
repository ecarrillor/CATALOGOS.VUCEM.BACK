package mx.gob.sat.catalogo.controller.response.condicionuso;

import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

/**
 * <b>Class:</b> CatCondicionUsoResponse.java <br>
 * <b>Description:</b>
 * <p>DTO de salida para el catalogo de condiciones de uso.</p>
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
public class CatCondicionUsoResponse {

    /** Identificador de la condicion de uso. */
    private Short idCondicionUso;

    /** Fecha de inicio de vigencia. */
    private Instant fecIniVigencia;

    /** Fecha de fin de vigencia. */
    private Instant fecFinVigencia;

    /** Descripcion de la condicion de uso. */
    private String descripcion;

    /** Indicador de activo. */
    private Boolean blnActivo;

    /** Descripcion HTML de la condicion de uso. */
    private String descripcionHtml;
}
