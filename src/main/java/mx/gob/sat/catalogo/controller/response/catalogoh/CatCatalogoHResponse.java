package mx.gob.sat.catalogo.controller.response.catalogoh;

import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

/**
 * <b>Class:</b> CatCatalogoHResponse.java <br>
 * <b>Description:</b>
 * <p>DTO de salida para el catalogo de catalogos H.</p>
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
public class CatCatalogoHResponse {

    /** Clave del catalogo H. */
    private String cveCatalogoH;

    /** Nombre del catalogo. */
    private String nomCatalogo;

    /** Fecha de inicio de vigencia. */
    private Instant fecIniVigencia;

    /** Fecha de fin de vigencia. */
    private Instant fecFinVigencia;

    /** Indicador de activo. */
    private Boolean blnActivo;
}
