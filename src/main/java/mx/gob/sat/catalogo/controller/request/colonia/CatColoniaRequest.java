package mx.gob.sat.catalogo.controller.request.colonia;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * <b>Class:</b> CatColoniaRequest.java <br>
 * <b>Description:</b>
 * <p>DTO de entrada para el catalogo de colonias.</p>
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
public class CatColoniaRequest {

    /** Clave de la colonia. */
    @Size(max = 12)
    private String cveColonia;

    /** Nombre de la colonia. */
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

    /** Codigo de colonia SAT. */
    @Size(max = 10)
    private String satColonyCd;

    /** Indicador de activo. */
    @NotNull
    private Boolean blnActivo;

    /** Clave de la delegacion o municipio. */
    @Size(max = 6)
    private String cveDelegMun;

    /** Clave de la localidad. */
    @Size(max = 12)
    private String cveLocalidad;
}
