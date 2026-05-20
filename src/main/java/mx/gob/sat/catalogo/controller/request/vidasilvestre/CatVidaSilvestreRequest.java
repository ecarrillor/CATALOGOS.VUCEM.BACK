package mx.gob.sat.catalogo.controller.request.vidasilvestre;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * <b>Class:</b> CatVidaSilvestreRequest.java <br>
 * <b>Description:</b>
 * <p>DTO de entrada para el catalogo de vida silvestre.</p>
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
public class CatVidaSilvestreRequest {

    /** Identificador de tipo de vida silvestre. */
    @Size(max = 20)
    private String ideTipoVidaSilvestre;

    /** Descripcion del nombre comun. */
    @Size(max = 256)
    private String descNombreComun;

    /** Descripcion del nombre cientifico. */
    @Size(max = 256)
    private String descNombreCientifico;

    /** Identificador de clasificacion taxonomica. */
    @Size(max = 20)
    private String ideClasifTaxonomica;

    /** Fecha de inicio de vigencia. */
    @NotNull
    private LocalDate fecIniVigencia;

    /** Fecha de fin de vigencia. */
    private LocalDate fecFinVigencia;

    /** Indicador de activo. */
    @NotNull
    private Boolean blnActivo;

    /** Funcion zootecnica. */
    @Size(max = 100)
    private String funcionZootecnica;

    /** Identificador del genero asociado. */
    private Integer idGenero;

    /** Identificador de la especie asociada. */
    private Integer idEspecie;
}
