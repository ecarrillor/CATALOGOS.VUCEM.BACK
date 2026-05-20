package mx.gob.sat.catalogo.controller.request.esquemaregla8;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

/**
 * <b>Class:</b> CatEsquemaRegla8Request.java <br>
 * <b>Description:</b>
 * <p>DTO de entrada para el catalogo de esquemas de regla 8.</p>
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
public class CatEsquemaRegla8Request {

    /** Nombre del esquema de regla 8. */
    @Size(max = 2000)
    private String nombre;

    /** Descripcion del requisito. */
    private String descRequisito;

    /** Fecha de inicio de vigencia. */
    @NotNull
    private Instant fecIniVigencia;

    /** Fecha de fin de vigencia. */
    private Instant fecFinVigencia;

    /** Indicador de activo. */
    @NotNull
    private Boolean blnActivo;
}
