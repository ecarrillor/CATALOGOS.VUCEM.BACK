package mx.gob.sat.catalogo.controller.request.unidadadministrativa;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * <b>Class:</b> CatUnidadAdministrativaRequest.java <br>
 * <b>Description:</b>
 * <p>DTO de entrada para el catalogo de unidades administrativas.</p>
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
public class CatUnidadAdministrativaRequest {

    /** Clave de la unidad administrativa. */
    @NotNull
    @Size(max = 10)
    private String cveUnidadAdministrativa;

    /** Identificador de tipo de unidad administrativa. */
    @Size(max = 20)
    private String ideTipoUnidadAdministrativa;

    /** Nivel de la unidad administrativa. */
    private Short nivel;

    /** Acronimo de la unidad administrativa. */
    @Size(max = 20)
    private String acronimo;

    /** Nombre de la unidad administrativa. */
    @Size(max = 120)
    private String nombre;

    /** Descripcion de la unidad administrativa. */
    @Size(max = 120)
    private String descripcion;

    /** Indicador de fronteriza. */
    private Boolean blnFronteriza;

    /** Fecha de inicio de vigencia. */
    @NotNull
    private LocalDate fecIniVigencia;

    /** Fecha de fin de vigencia. */
    private LocalDate fecFinVigencia;

    /** Indicador de activo. */
    @NotNull
    private Boolean blnActivo;

    /** Clave de la unidad administrativa relacionada (auto-referencia). */
    private String cveUnidadAdministrativaR;

    /** Clave de la entidad federativa asociada. */
    private String cveEntidad;

    /** Identificador de la dependencia asociada. */
    @NotNull
    private Short idDependencia;
}
