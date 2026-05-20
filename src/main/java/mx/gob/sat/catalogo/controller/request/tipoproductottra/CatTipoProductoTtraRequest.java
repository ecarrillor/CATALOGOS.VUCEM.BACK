package mx.gob.sat.catalogo.controller.request.tipoproductottra;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * <b>Class:</b> CatTipoProductoTtraRequest.java <br>
 * <b>Description:</b>
 * <p>DTO de entrada para el catalogo de tipos de producto ttra.</p>
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
public class CatTipoProductoTtraRequest {

    /** Descripcion del tipo de producto. */
    @Size(max = 250)
    private String descTipoProducto;

    /** Fecha de inicio de vigencia. */
    @NotNull
    private LocalDate fecIniVigencia;

    /** Fecha de fin de vigencia. */
    private LocalDate fecFinVigencia;

    /** Indicador de activo. */
    @NotNull
    private Boolean blnActivo;

    /** Identificador del tipo de certificado de mercancias. */
    @Size(max = 20)
    private String ideTipoCertificadoMerc;

    /** Identificador del tipo de tramite. */
    @NotNull
    private Long idTipoTramite;
}
