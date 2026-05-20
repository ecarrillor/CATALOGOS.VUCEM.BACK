package mx.gob.sat.catalogo.controller.request.normaloficial;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * <b>Class:</b> CatNormalOficialRequest.java <br>
 * <b>Description:</b>
 * <p>DTO de entrada para el catalogo de normas oficiales.</p>
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
public class CatNormalOficialRequest {

    /** Clave de la norma. */
    @NotNull
    @Size(max = 30)
    private String claveNorma;

    /** Fecha de inicio de vigencia. */
    @NotNull
    private LocalDate fecIniVigencia;

    /** Fecha de fin de vigencia. */
    private LocalDate fecFinVigencia;

    /** Indicador de activo. */
    @NotNull
    private Boolean blnActivo;

    /** Descripcion de la norma. */
    @Size(max = 300)
    private String descNorma;

    /** Fecha de publicacion. */
    private LocalDate fecPublicacion;

    /** Fecha de entrada en vigor. */
    private LocalDate fecEntradaVigor;

    /** Identificador de clasificacion de norma. */
    @Size(max = 20)
    private String ideClasifNorma;

    /** Indicador de lote estructurado. */
    private Boolean blnLoteEstructurado;

    /** Clave del pais asociado. */
    private String cvePais;

    /** Identificador de la norma oficial relacionada (auto-referencia). */
    private Integer idNormaOficialR;
}
