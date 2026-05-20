package mx.gob.sat.catalogo.controller.request.fundamentottra;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * <b>Class:</b> CatFundamentoTtraRequest.java <br>
 * <b>Description:</b>
 * <p>DTO de entrada para el catalogo de fundamentos ttra.</p>
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
public class CatFundamentoTtraRequest {

    /** Tipo de fundamento ttra. */
    @Size(max = 20)
    private String ideTipoFundamentoTtra;

    /** Descripcion del fundamento. */
    @Size(max = 512)
    private String descFundamento;

    /** Descripcion del contenido del fundamento. */
    @Size(max = 2000)
    private String descContenidoFundamento;

    /** Fecha de inicio de vigencia. */
    @NotNull
    private LocalDate fecIniVigencia;

    /** Fecha de fin de vigencia. */
    private LocalDate fecFinVigencia;

    /** Indicador de activo. */
    @NotNull
    private Boolean blnActivo;

    /** Identificador del tipo de tramite. */
    private Long idTipoTramite;
}
