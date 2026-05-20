package mx.gob.sat.catalogo.controller.response.vidasilvestre;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

/**
 * <b>Class:</b> CatVidaSilvestreResponse.java <br>
 * <b>Description:</b>
 * <p>DTO de salida para el catalogo de vida silvestre.</p>
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
public class CatVidaSilvestreResponse {

    /** Identificador de vida silvestre. */
    private Integer idVidaSilvestre;

    /** Identificador de tipo de vida silvestre. */
    private String ideTipoVidaSilvestre;

    /** Descripcion del nombre comun. */
    private String descNombreComun;

    /** Descripcion del nombre cientifico. */
    private String descNombreCientifico;

    /** Identificador de clasificacion taxonomica. */
    private String ideClasifTaxonomica;

    /** Fecha de inicio de vigencia. */
    private LocalDate fecIniVigencia;

    /** Fecha de fin de vigencia. */
    private LocalDate fecFinVigencia;

    /** Indicador de activo. */
    private Boolean blnActivo;

    /** Funcion zootecnica. */
    private String funcionZootecnica;

    /** Identificador del genero asociado. */
    private Integer idGenero;

    /** Nombre del genero asociado. */
    private String nombreGenero;

    /** Identificador de la especie asociada. */
    private Integer idEspecie;

    /** Descripcion de la especie asociada. */
    private String descEspecie;
}
