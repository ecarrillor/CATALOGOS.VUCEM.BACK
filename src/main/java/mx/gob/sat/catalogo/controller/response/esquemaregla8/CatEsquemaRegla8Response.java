package mx.gob.sat.catalogo.controller.response.esquemaregla8;

import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

/**
 * <b>Class:</b> CatEsquemaRegla8Response.java <br>
 * <b>Description:</b>
 * <p>DTO de salida para el catalogo de esquemas de regla 8.</p>
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
public class CatEsquemaRegla8Response {

    /** Identificador del esquema de regla 8. */
    private Short idEsquemaRegla8;

    /** Nombre del esquema de regla 8. */
    private String nombre;

    /** Descripcion del requisito. */
    private String descRequisito;

    /** Fecha de inicio de vigencia. */
    private Instant fecIniVigencia;

    /** Fecha de fin de vigencia. */
    private Instant fecFinVigencia;

    /** Indicador de activo. */
    private Boolean blnActivo;
}
