package mx.gob.sat.catalogo.controller.request.dependencia;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * <b>Class:</b> CatDependenciaRequest.java <br>
 * <b>Description:</b>
 * <p>DTO de entrada para el catalogo de dependencias.</p>
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
public class CatDependenciaRequest {

    /** Identificador de la dependencia. */
    private Long cveDependencia;

    /** Nombre de la dependencia. */
    @Size(max = 120)
    private String nombreDependencia;

    /** Acronimo de la dependencia. */
    @Size(max = 20)
    private String acronimo;

    /** Clave del calendario asociado. */
    private String cveCalendario;

    /** Indicador de tramites VU. */
    private Boolean tramiteVU;

    /** Fecha de inicio de vigencia. */
    @NotNull
    private LocalDate fecIniVigencia;

    /** Fecha de fin de vigencia. */
    private LocalDate fecFinVigencia;

    /** Indicador de activo. */
    @NotNull
    private Boolean blnActivo;
}
