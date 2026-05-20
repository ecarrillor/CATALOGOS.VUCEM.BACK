package mx.gob.sat.catalogo.controller.request.fundamentodictamen;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * <b>Class:</b> CatFundamentoDictamenRequest.java <br>
 * <b>Description:</b>
 * <p>DTO de entrada para el catalogo de fundamentos de dictamen.</p>
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
public class CatFundamentoDictamenRequest {

    /** Descripcion del fundamento de dictamen. */
    @Size(max = 2000)
    private String descripcion;

    /** Indicador de sentido del fundamento. */
    private Boolean blnSentidoFundamento;

    /** Fecha de inicio de vigencia. */
    @NotNull
    private LocalDate fecIniVigencia;

    /** Fecha de fin de vigencia. */
    private LocalDate fecFinVigencia;

    /** Indicador de activo. */
    @NotNull
    private Boolean blnActivo;
}
