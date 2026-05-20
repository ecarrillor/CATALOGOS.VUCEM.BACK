package mx.gob.sat.catalogo.controller.response.empresarecif;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

/**
 * <b>Class:</b> CatEmpresaRecifResponse.java <br>
 * <b>Description:</b>
 * <p>DTO de salida para el catalogo de empresas RECIF.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Response DTO
 */
@Getter
@Builder
public class CatEmpresaRecifResponse {

    /** Clave RECIF. */
    private String recif;

    /** RFC de la empresa. */
    private String rfc;

    /** Razon social de la empresa. */
    private String razonSocial;

    /** Indicador de activo. */
    private Boolean blnActivo;

    /** Fecha de inicio de vigencia. */
    private LocalDate fecIniVigencia;

    /** Fecha de fin de vigencia. */
    private LocalDate fecFinVigencia;

    /** Clave de unidad administrativa. */
    private String cveUnidadAdministrativa;
}
