package mx.gob.sat.catalogo.controller.response.tipoproductottra;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

/**
 * <b>Class:</b> CatTipoProductoTtraResponse.java <br>
 * <b>Description:</b>
 * <p>DTO de salida para el catalogo de tipos de producto ttra.</p>
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
public class CatTipoProductoTtraResponse {

    /** Identificador del tipo de producto ttra. */
    private Short idTipoProductoTtra;

    /** Descripcion del tipo de producto. */
    private String descTipoProducto;

    /** Fecha de inicio de vigencia. */
    private LocalDate fecIniVigencia;

    /** Fecha de fin de vigencia. */
    private LocalDate fecFinVigencia;

    /** Indicador de activo. */
    private Boolean blnActivo;

    /** Identificador del tipo de certificado de mercancias. */
    private String ideTipoCertificadoMerc;

    /** Identificador del tipo de tramite. */
    private Long idTipoTramite;
}
