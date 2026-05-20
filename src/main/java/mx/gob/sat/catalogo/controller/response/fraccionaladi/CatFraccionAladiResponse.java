package mx.gob.sat.catalogo.controller.response.fraccionaladi;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

/**
 * <b>Class:</b> CatFraccionAladiResponse.java <br>
 * <b>Description:</b>
 * <p>DTO de salida para el catalogo de fracciones ALADI.</p>
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
public class CatFraccionAladiResponse {

    /** Identificador de la fraccion ALADI. */
    private Long idFraccionAladi;

    /** Tipo de fraccion ALADI. */
    private String ideTipoFraccionAladi;

    /** Clave de la fraccion. */
    private String cveFraccion;

    /** Descripcion de la fraccion ALADI. */
    private String descripcion;

    /** Fecha de captura. */
    private LocalDate fecCaptura;

    /** Fecha de fin de vigencia. */
    private LocalDate fecFinVigencia;

    /** Fecha de inicio de vigencia. */
    private LocalDate fecIniVigencia;

    /** Indicador de activo. */
    private Boolean blnActivo;
}
