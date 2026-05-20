package mx.gob.sat.catalogo.controller.response.normaloficial;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

/**
 * <b>Class:</b> CatNormalOficialResponse.java <br>
 * <b>Description:</b>
 * <p>DTO de salida para el catalogo de normas oficiales.</p>
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
public class CatNormalOficialResponse {

    /** Identificador de la norma oficial. */
    private Integer idNormalOficial;

    /** Clave de la norma. */
    private String claveNorma;

    /** Fecha de inicio de vigencia. */
    private LocalDate fecIniVigencia;

    /** Fecha de fin de vigencia. */
    private LocalDate fecFinVigencia;

    /** Indicador de activo. */
    private Boolean blnActivo;

    /** Descripcion de la norma. */
    private String descNorma;

    /** Fecha de publicacion. */
    private LocalDate fecPublicacion;

    /** Fecha de entrada en vigor. */
    private LocalDate fecEntradaVigor;

    /** Identificador de clasificacion de norma. */
    private String ideClasifNorma;

    /** Indicador de lote estructurado. */
    private Boolean blnLoteEstructurado;

    /** Nombre del pais asociado. */
    private String nombrePais;

    /** Clave de la norma oficial relacionada. */
    private Integer idNormaOficialR;
}
