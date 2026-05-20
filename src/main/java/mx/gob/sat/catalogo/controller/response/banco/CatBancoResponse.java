package mx.gob.sat.catalogo.controller.response.banco;

import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

/**
 * <b>Class:</b> CatBancoResponse.java <br>
 * <b>Description:</b>
 * <p>DTO de salida para el catalogo de bancos.</p>
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
public class CatBancoResponse {

    /** Clave del banco. */
    private String cveBanco;

    /** Nombre del banco. */
    private String nombre;

    /** Fecha de captura. */
    private Instant fecCaptura;

    /** Fecha de inicio de vigencia. */
    private Instant fecIniVigencia;

    /** Fecha de fin de vigencia. */
    private Instant fecFinVigencia;

    /** Indicador de activo. */
    private Boolean blnActivo;

    /** Indicador E5. */
    private Boolean blnE5;
}
