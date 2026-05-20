package mx.gob.sat.catalogo.controller.request.regimen;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * <b>Class:</b> CatRegimenRequest.java <br>
 * <b>Description:</b>
 * <p>DTO de entrada para el catalogo de regimenes.</p>
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
public class CatRegimenRequest {

    /** Clave del regimen. */
    @Size(max = 2)
    private String cveRegimen;

    /** Nombre del regimen. */
    @Size(max = 120)
    private String nombre;

    /** Fecha de inicio de vigencia. */
    @NotNull
    private LocalDate fecIniVigencia;

    /** Fecha de fin de vigencia. */
    private LocalDate fecFinVigencia;

    /** Indicador de activo. */
    @NotNull
    private Boolean blnActivo;

    /** Clave de enumeracion. */
    @Size(max = 20)
    private String cveEnumeracion;
}
