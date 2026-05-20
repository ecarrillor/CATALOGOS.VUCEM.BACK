package mx.gob.sat.catalogo.controller.response.suplencia;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

/**
 * <b>Class:</b> CatSuplenciaResponse.java <br>
 * <b>Description:</b>
 * <p>DTO de salida para el catalogo de suplencias.</p>
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
public class CatSuplenciaResponse {

    /** Identificador de la suplencia. */
    private Short idSuplencia;

    /** Texto de la suplencia. */
    private String texto;

    /** Fecha de inicio de vigencia. */
    private LocalDate fecIniVigencia;

    /** Fecha de fin de vigencia. */
    private LocalDate fecFinVigencia;

    /** Indicador de activo. */
    private Boolean blnActivo;

    /** Nombre de la dependencia asociada. */
    private String nombreDependencia;
}
