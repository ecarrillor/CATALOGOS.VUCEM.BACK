package mx.gob.sat.catalogo.controller.request.sectorprosec;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * <b>Class:</b> CatSectorProsecRequest.java <br>
 * <b>Description:</b>
 * <p>DTO de entrada para el catalogo de sectores PROSEC.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Request DTO
 */
@Getter
@Setter
public class CatSectorProsecRequest {

    /** Clave del sector PROSEC. */
    @Size(max = 6)
    private String cveSectorProsec;

    /** Nombre del sector PROSEC. */
    @Size(max = 120)
    private String nombre;

    /** Indicador de productor indirecto. */
    private Boolean blnProductorIndirecto;

    /** Indicador de ampliacion de mercancias. */
    private Boolean blnAmpliacionMercancias;

    /** Fecha de inicio de vigencia. */
    @NotNull
    private LocalDate fecIniVigencia;

    /** Fecha de fin de vigencia. */
    private LocalDate fecFinVigencia;

    /** Indicador de activo. */
    @NotNull
    private Boolean blnActivo;
}
