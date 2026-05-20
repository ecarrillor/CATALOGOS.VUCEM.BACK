package mx.gob.sat.catalogo.controller.request.catalogoh;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

/**
 * <b>Class:</b> CatCatalogoHRequest.java <br>
 * <b>Description:</b>
 * <p>DTO de entrada para el catalogo de catalogos H.</p>
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
public class CatCatalogoHRequest {

    /** Clave del catalogo H. */
    @Size(max = 6)
    private String cveCatalogoH;

    /** Nombre del catalogo. */
    @NotBlank
    @Size(max = 300)
    private String nomCatalogo;

    /** Fecha de inicio de vigencia. */
    @NotNull
    private Instant fecIniVigencia;

    /** Fecha de fin de vigencia. */
    private Instant fecFinVigencia;

    /** Indicador de activo. */
    @NotNull
    private Boolean blnActivo;
}
