package mx.gob.sat.catalogo.controller.response.servicioimmex;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

/**
 * <b>Class:</b> CatServicioImmexResponse.java <br>
 * <b>Description:</b>
 * <p>DTO de salida para el catalogo de servicios IMMEX.</p>
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
public class CatServicioImmexResponse {

    /** Identificador del servicio IMMEX. */
    private Short idServicioImmex;

    /** Nombre del servicio IMMEX. */
    private String nombre;

    /** Fecha de inicio de vigencia. */
    private LocalDate fecIniVigencia;

    /** Identificador de tipo de servicio IMMEX. */
    private String ideTipoServicioImmex;

    /** Fecha de fin de vigencia. */
    private LocalDate fecFinVigencia;

    /** Indicador de activo. */
    private Boolean blnActivo;
}
