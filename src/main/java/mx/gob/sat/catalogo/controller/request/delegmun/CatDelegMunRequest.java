package mx.gob.sat.catalogo.controller.request.delegmun;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * <b>Class:</b> CatDelegMunRequest.java <br>
 * <b>Description:</b>
 * <p>DTO de entrada para el catalogo de delegaciones y municipios.</p>
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
public class CatDelegMunRequest {

    /** Clave de la delegacion o municipio. */
    @Size(max = 6)
    private String cveDelegMun;

    /** Nombre de la delegacion o municipio. */
    @Size(max = 120)
    private String nombre;

    /** Fecha de captura. */
    private LocalDate fecCaptura;

    /** Fecha de fin de vigencia. */
    private LocalDate fecFinVigencia;

    /** Municipio SAT. */
    @Size(max = 5)
    private String satMunicipality;

    /** Fecha de inicio de vigencia. */
    @NotNull
    private LocalDate fecIniVigencia;

    /** Indicador de activo. */
    @NotNull
    private Boolean blnActivo;

    /** Clave de la entidad federativa. */
    @Size(max = 3)
    private String cveEntidad;
}
