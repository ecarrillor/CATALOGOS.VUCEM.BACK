package mx.gob.sat.catalogo.controller.response.tipoempresarecif;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

/**
 * <b>Class:</b> CatTipoEmpresaRecifResponse.java <br>
 * <b>Description:</b>
 * <p>DTO de salida para el catalogo de tipos de empresa RECIF.</p>
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
public class CatTipoEmpresaRecifResponse {

    /** Clave del tipo de empresa RECIF. */
    private String cveTipoEmpresaRecif;

    /** Descripcion del tipo de empresa RECIF. */
    private String descripcion;

    /** Clave del tipo de empresa RECIF relacionado. */
    private String cveTipoEmpresaRecifR;

    /** Fecha de inicio de vigencia. */
    private LocalDate fecIniVigencia;

    /** Fecha de fin de vigencia. */
    private LocalDate fecFinVigencia;

    /** Indicador de activo. */
    private Boolean blnActivo;
}
