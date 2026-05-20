package mx.gob.sat.catalogo.controller.response.sectorprosec;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

/**
 * <b>Class:</b> CatSectorProsecResponse.java <br>
 * <b>Description:</b>
 * <p>DTO de salida para el catalogo de sectores PROSEC.</p>
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
public class CatSectorProsecResponse {

    /** Clave del sector PROSEC. */
    private String cveSectorProsec;

    /** Nombre del sector PROSEC. */
    private String nombre;

    /** Indicador de productor indirecto. */
    private Boolean blnProductorIndirecto;

    /** Indicador de ampliacion de mercancias. */
    private Boolean blnAmpliacionMercancias;

    /** Fecha de inicio de vigencia. */
    private LocalDate fecIniVigencia;

    /** Fecha de fin de vigencia. */
    private LocalDate fecFinVigencia;

    /** Indicador de activo. */
    private Boolean blnActivo;
}
