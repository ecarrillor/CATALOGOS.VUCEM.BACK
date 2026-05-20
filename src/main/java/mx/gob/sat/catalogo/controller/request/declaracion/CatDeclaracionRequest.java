package mx.gob.sat.catalogo.controller.request.declaracion;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

/**
 * <b>Class:</b> CatDeclaracionRequest.java <br>
 * <b>Description:</b>
 * <p>DTO de entrada para el catalogo de declaraciones.</p>
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
public class CatDeclaracionRequest {

    /** Clave de la declaracion. */
    @Size(max = 20)
    private String cveDeclaracion;

    /** Descripcion de la declaracion. */
    @Size(max = 2000)
    private String descDeclaracion;

    /** Fecha de captura. */
    private Instant fecCaptura;

    /** Fecha de fin de vigencia. */
    private Instant fecFinVigencia;

    /** Fecha de inicio de vigencia. */
    @NotNull
    private Instant fecIniVigencia;

    /** Indicador de activo. */
    @NotNull
    private Boolean blnActivo;

    /** Clave de referencia. */
    @Size(max = 20)
    private String cveReferencia;
}
