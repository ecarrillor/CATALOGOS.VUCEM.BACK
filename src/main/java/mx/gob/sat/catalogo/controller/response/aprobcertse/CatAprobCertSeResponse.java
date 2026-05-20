package mx.gob.sat.catalogo.controller.response.aprobcertse;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

/**
 * <b>Class:</b> CatAprobCertSeResponse.java <br>
 * <b>Description:</b>
 * <p>DTO de salida para el catalogo de aprobaciones de certificado SE.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Response DTO
 */
@Getter
@Builder
public class CatAprobCertSeResponse {

    /** Identificador de la aprobacion certificado SE. */
    private Short idAprobCertSe;

    /** Identificador de tipo de aprobacion certificado SE. */
    private String ideTipoAprobCertSe;

    /** Numero de aprobacion certificado. */
    private String numAprobCert;

    /** Fecha de emision. */
    private LocalDate fecEmision;

    /** Fecha de inicio de vigencia. */
    private LocalDate fecIniVigencia;

    /** Fecha de fin de vigencia. */
    private LocalDate fecFinVigencia;

    /** Indicador de activo. */
    private Boolean blnActivo;

    /** Clave de unidad administrativa. */
    private String cveUnidadAdministrativa;
}
