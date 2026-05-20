package mx.gob.sat.catalogo.controller.request.tratadoacuerdo;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * <b>Class:</b> CatTratadoAcuerdoRequest.java <br>
 * <b>Description:</b>
 * <p>DTO de entrada para el catalogo de tratados y acuerdos.</p>
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
public class CatTratadoAcuerdoRequest {

    /** Identificador de tipo de tratado o acuerdo. */
    @Size(max = 20)
    private String ideTipoTratadoAcuerdo;

    /** Clave del tratado o acuerdo. */
    @NotNull
    @Size(max = 20)
    private String cveTratadoAcuerdo;

    /** Nombre del tratado o acuerdo. */
    @Size(max = 250)
    private String nombre;

    /** Indicador PEXIM. */
    private Boolean blnPexim;

    /** Fecha de captura. */
    private LocalDate fecCaptura;

    /** Fecha de fin de vigencia. */
    private LocalDate fecFinVigencia;

    /** Identificador de tipo de cupo SAAI. */
    @Size(max = 20)
    private String ideTipoCupoSaai;

    /** Fecha de inicio de vigencia. */
    @NotNull
    private LocalDate fecIniVigencia;

    /** Indicador de activo. */
    @NotNull
    private Boolean blnActivo;

    /** Indicador de evaluacion individual. */
    private Boolean blnEvaluarIndividual;
}
