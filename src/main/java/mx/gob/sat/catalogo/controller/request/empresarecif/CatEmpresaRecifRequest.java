package mx.gob.sat.catalogo.controller.request.empresarecif;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * <b>Class:</b> CatEmpresaRecifRequest.java <br>
 * <b>Description:</b>
 * <p>DTO de entrada para el catalogo de empresas RECIF.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Request DTO
 */
@Getter
@Setter
public class CatEmpresaRecifRequest {

    /** Clave RECIF. */
    @Size(max = 21)
    private String recif;

    /** RFC de la empresa. */
    @Size(max = 13)
    private String rfc;

    /** Razon social de la empresa. */
    @Size(max = 255)
    private String razonSocial;

    /** Indicador de activo. */
    @NotNull
    private Boolean blnActivo;

    /** Fecha de inicio de vigencia. */
    @NotNull
    private LocalDate fecIniVigencia;

    /** Fecha de fin de vigencia. */
    private LocalDate fecFinVigencia;

    /** Clave de unidad administrativa. */
    private String cveUnidadAdministrativa;
}
