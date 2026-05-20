package mx.gob.sat.catalogo.controller.response.genero;

import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

/**
 * <b>Class:</b> CatGeneroResponse.java <br>
 * <b>Description:</b>
 * <p>DTO de salida para el catalogo de generos.</p>
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
public class CatGeneroResponse {

    /** Identificador del genero. */
    private Integer idGenero;

    /** Descripcion del genero. */
    private String descGenero;

    /** Fecha de inicio de vigencia. */
    private Instant fecIniVigencia;

    /** Fecha de fin de vigencia. */
    private Instant fecFinVigencia;

    /** Indicador de activo. */
    private Boolean blnActivo;
}
