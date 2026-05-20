package mx.gob.sat.catalogo.controller.response;

import lombok.Builder;
import lombok.Getter;

/**
 * <b>Class:</b> SelectResponse.java <br>
 * <b>Description:</b>
 * <p>DTO generico de respuesta para listas de seleccion (combos/dropdowns).
 * Contiene unicamente la clave y el nombre del elemento.</p>
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
public class SelectResponse {

    /** Clave del elemento. */
    private String cve;

    /** Nombre descriptivo del elemento. */
    private String nombre;
}
