package mx.gob.sat.catalogo.controller.response.tratadoacuerdo;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

/**
 * <b>Class:</b> CatTratadoAcuerdoResponse.java <br>
 * <b>Description:</b>
 * <p>DTO de salida para el catalogo de tratados y acuerdos.</p>
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
public class CatTratadoAcuerdoResponse {

    /** Identificador del tratado o acuerdo. */
    private Short idTratadoAcuerdo;

    /** Identificador de tipo de tratado o acuerdo. */
    private String ideTipoTratadoAcuerdo;

    /** Clave del tratado o acuerdo. */
    private String cveTratadoAcuerdo;

    /** Nombre del tratado o acuerdo. */
    private String nombre;

    /** Indicador PEXIM. */
    private Boolean blnPexim;

    /** Fecha de captura. */
    private LocalDate fecCaptura;

    /** Fecha de fin de vigencia. */
    private LocalDate fecFinVigencia;

    /** Identificador de tipo de cupo SAAI. */
    private String ideTipoCupoSaai;

    /** Fecha de inicio de vigencia. */
    private LocalDate fecIniVigencia;

    /** Indicador de activo. */
    private Boolean blnActivo;

    /** Indicador de evaluacion individual. */
    private Boolean blnEvaluarIndividual;
}
