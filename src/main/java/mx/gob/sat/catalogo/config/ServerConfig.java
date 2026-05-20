package mx.gob.sat.catalogo.config;

import lombok.Getter;
import lombok.Setter;

/**
 * <b>Class:</b> ServerConfig.java <br>
 * <b>Description:</b>
 * <p>
 * Configuración de servidor para Swagger.
 * </p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 02 de julio del 2025
 * @version 1.0
 * @category Configuracion
 */
@Getter
@Setter
public class ServerConfig {

    /** Representa la url del servidor. */
    private String url;

    /** Representa la descripción del ambiente. */
    private String description;
}