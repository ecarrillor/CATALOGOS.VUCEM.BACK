package mx.gob.sat.catalogo.controller.request.condicionuso;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

/**
 * <b>Class:</b> CatCondicionUsoRequest.java <br>
 * <b>Description:</b>
 * <p>DTO de entrada para el catalogo de condiciones de uso.</p>
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
public class CatCondicionUsoRequest {

    /** Fecha de inicio de vigencia. */
    @NotNull
    private Instant fecIniVigencia;

    /** Fecha de fin de vigencia. */
    private Instant fecFinVigencia;

    /** Descripcion de la condicion de uso. */
    @NotNull
    private String descripcion;

    /** Indicador de activo. */
    @NotNull
    private Boolean blnActivo;

    /** Descripcion HTML de la condicion de uso. */
    @NotNull
    private String descripcionHtml;
}
