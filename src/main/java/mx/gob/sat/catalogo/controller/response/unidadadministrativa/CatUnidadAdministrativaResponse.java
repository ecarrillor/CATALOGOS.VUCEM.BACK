package mx.gob.sat.catalogo.controller.response.unidadadministrativa;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

/**
 * <b>Class:</b> CatUnidadAdministrativaResponse.java <br>
 * <b>Description:</b>
 * <p>DTO de salida para el catalogo de unidades administrativas.</p>
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
public class CatUnidadAdministrativaResponse {

    /** Clave de la unidad administrativa. */
    private String cveUnidadAdministrativa;

    /** Identificador de tipo de unidad administrativa. */
    private String ideTipoUnidadAdministrativa;

    /** Nivel de la unidad administrativa. */
    private Short nivel;

    /** Acronimo de la unidad administrativa. */
    private String acronimo;

    /** Nombre de la unidad administrativa. */
    private String nombre;

    /** Descripcion de la unidad administrativa. */
    private String descripcion;

    /** Indicador de fronteriza. */
    private Boolean blnFronteriza;

    /** Fecha de inicio de vigencia. */
    private LocalDate fecIniVigencia;

    /** Fecha de fin de vigencia. */
    private LocalDate fecFinVigencia;

    /** Indicador de activo. */
    private Boolean blnActivo;

    /** Clave de la unidad administrativa relacionada. */
    private String cveUnidadAdministrativaR;

    /** Clave de la entidad federativa asociada. */
    private String cveEntidad;

    /** Nombre de la entidad federativa asociada. */
    private String nombreEntidad;

    /** Nombre de la dependencia asociada. */
    private String nombreDependencia;
}
