package mx.gob.sat.catalogo.config;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * <b>Class:</b> SwaggerProperties.java <br>
 * <b>Description:</b>
 * <p>
 * Propiedades de configuración para Swagger.
 * </p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 02 de julio del 2025
 * @version 1.0
 * @category Configuracion
 */
@Component
@Getter
@Setter
public class SwaggerProperties {

    @Value("${app.swagger.servers}")
    private String swaggerServersString;

    /** Representa la información de los ambientes. */
    private List<ServerConfig> servers;

    /**
     * Inicializa la lista de servidores desde la configuración.
     */
    @PostConstruct
    public void init() {
        servers = new ArrayList<>();
        String[] entries = swaggerServersString.split(",");
        for (String entry : entries) {
            String[] parts = entry.trim().split("\\|");
            if (parts.length == 2) {
                ServerConfig server = new ServerConfig();
                server.setUrl(parts[0].trim());
                server.setDescription(parts[1].trim());
                servers.add(server);
            }
        }
    }
}