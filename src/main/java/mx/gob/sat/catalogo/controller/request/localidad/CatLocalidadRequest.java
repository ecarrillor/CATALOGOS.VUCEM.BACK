package mx.gob.sat.catalogo.controller.request.localidad;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * <b>Class:</b> CatLocalidadRequest.java <br>
 * <b>Description:</b>
 * <p>DTO de entrada para el catalogo de localidades.</p>
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
public class CatLocalidadRequest {

    /** Clave de la localidad. */
    @Size(max = 12)
    private String cveLocalidad;

    /** Nombre de la localidad. */
    @Size(max = 120)
    private String nombre;

    /** Fecha de captura. */
    private LocalDate fecCaptura;

    /** Fecha de inicio de vigencia. */
    @NotNull
    private LocalDate fecIniVigencia;

    /** Fecha de fin de vigencia. */
    private LocalDate fecFinVigencia;

    /** Codigo postal. */
    @Size(max = 12)
    private String cp;

    /** Codigo de localidad SAT. */
    @Size(max = 10)
    private String satTownCode;

    /** Indicador de activo. */
    @NotNull
    private Boolean blnActivo;

    /** Clave de la delegacion o municipio. */
    @Size(max = 6)
    private String cveDelegMun;
}
