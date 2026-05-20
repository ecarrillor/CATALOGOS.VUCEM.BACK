package mx.gob.sat.catalogo.controller.request.tipoempresarecif;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * <b>Class:</b> CatTipoEmpresaRecifRequest.java <br>
 * <b>Description:</b>
 * <p>DTO de entrada para el catalogo de tipos de empresa RECIF.</p>
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
public class CatTipoEmpresaRecifRequest {

    /** Clave del tipo de empresa RECIF. */
    @Size(max = 2)
    private String cveTipoEmpresaRecif;

    /** Descripcion del tipo de empresa RECIF. */
    @Size(max = 50)
    private String descripcion;

    /** Clave del tipo de empresa RECIF relacionado. */
    @Size(max = 2)
    private String cveTipoEmpresaRecifR;

    /** Fecha de inicio de vigencia. */
    @NotNull
    private LocalDate fecIniVigencia;

    /** Fecha de fin de vigencia. */
    private LocalDate fecFinVigencia;

    /** Indicador de activo. */
    @NotNull
    private Boolean blnActivo;
}
