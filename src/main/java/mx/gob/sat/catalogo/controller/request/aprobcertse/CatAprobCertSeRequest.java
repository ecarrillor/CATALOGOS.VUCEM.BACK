package mx.gob.sat.catalogo.controller.request.aprobcertse;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * <b>Class:</b> CatAprobCertSeRequest.java <br>
 * <b>Description:</b>
 * <p>DTO de entrada para el catalogo de aprobaciones de certificado SE.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Request DTO
 */
@Getter
@Setter
public class CatAprobCertSeRequest {

    /** Identificador de tipo de aprobacion certificado SE. */
    @NotNull
    @Size(max = 20)
    private String ideTipoAprobCertSe;

    /** Numero de aprobacion certificado. */
    @NotNull
    @Size(max = 30)
    private String numAprobCert;

    /** Fecha de emision. */
    private LocalDate fecEmision;

    /** Fecha de inicio de vigencia. */
    @NotNull
    private LocalDate fecIniVigencia;

    /** Fecha de fin de vigencia. */
    private LocalDate fecFinVigencia;

    /** Indicador de activo. */
    @NotNull
    private Boolean blnActivo;

    /** Clave de unidad administrativa. */
    private String cveUnidadAdministrativa;
}
