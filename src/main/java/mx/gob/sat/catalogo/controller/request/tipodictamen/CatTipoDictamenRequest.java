package mx.gob.sat.catalogo.controller.request.tipodictamen;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

/**
 * <b>Class:</b> CatTipoDictamenRequest.java <br>
 * <b>Description:</b>
 * <p>DTO de entrada para el catalogo de tipos de dictamen.</p>
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
public class CatTipoDictamenRequest {

    /** Nombre del tipo de dictamen. */
    @Size(max = 120)
    private String nombre;

    /** Orden del tipo de dictamen. */
    private Short orden;

    /** Fecha de inicio de vigencia. */
    @NotNull
    private Instant fecIniVigencia;

    /** Fecha de fin de vigencia. */
    private Instant fecFinVigencia;

    /** Indicador de activo. */
    @NotNull
    private Boolean blnActivo;
}
