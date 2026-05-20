package mx.gob.sat.catalogo.controller.request.usoespecmercanciattra;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * <b>Class:</b> CatUsoEspecMercanciaTtraRequest.java <br>
 * <b>Description:</b>
 * <p>DTO de entrada para el catalogo de uso especial de mercancia ttra.</p>
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
public class CatUsoEspecMercanciaTtraRequest {

    /** Descripcion del uso especial de mercancia. */
    @Size(max = 250)
    private String descUsoEspMercancia;

    /** Fecha de inicio de vigencia. */
    @NotNull
    private LocalDate fecIniVigencia;

    /** Fecha de fin de vigencia. */
    private LocalDate fecFinVigencia;

    /** Indicador de activo. */
    @NotNull
    private Boolean blnActivo;

    /** Indicador de requerimiento de registro sanitario. */
    private Boolean blnReqRegistroSanitario;

    /** Identificador del tipo de tramite. */
    private Long idTipoTramite;
}
