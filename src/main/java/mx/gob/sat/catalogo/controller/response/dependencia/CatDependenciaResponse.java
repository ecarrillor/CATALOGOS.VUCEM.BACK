package mx.gob.sat.catalogo.controller.response.dependencia;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

/**
 * <b>Class:</b> CatDependenciaResponse.java <br>
 * <b>Description:</b>
 * <p>DTO de salida para el catalogo de dependencias.</p>
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
public class CatDependenciaResponse {

    /** Identificador de la dependencia. */
    private Long cveDependencia;

    /** Nombre de la dependencia. */
    private String nombreDependencia;

    /** Acronimo de la dependencia. */
    private String acronimo;

    /** Nombre del calendario asociado. */
    private String nombreCalendario;

    /** Clave del calendario asociado. */
    private String idCalendario;

    /** Indicador de tramites VU. */
    private Boolean tramiteVU;

    /** Fecha de inicio de vigencia. */
    private LocalDate fecIniVigencia;

    /** Fecha de fin de vigencia. */
    private LocalDate fecFinVigencia;

    /** Indicador de activo. */
    private Boolean blnActivo;
}
