package mx.gob.sat.catalogo.config;

import java.util.Objects;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import lombok.RequiredArgsConstructor;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

/**
 * <b>Class:</b> OpenApiConfig.java <br>
 * <b>Description:</b>
 * <p> Configuración de documentación de swagger en el proyecto.</p>
 *
 * <p>Revisión de documentación en la siguiente liga
 *
 *  http://<ip>:<puerto>/swagger-ui/index.html
 * </p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 17 de febrero del 2025
 * @version 1.0
 * @category Configuracion
 */
@Configuration
@SecurityScheme(
    name = "bearerAuth",
    type = SecuritySchemeType.HTTP,
    scheme = "bearer",
    bearerFormat = "JWT"
)
@RequiredArgsConstructor
public class OpenApiConfig {

    /** Representa el nombre de la aplicación. */
    @Value("${spring.application.name:Aplicación API}")
    private String nombreProyecto;

    /** Representa la versión del API. */
    @Value("${spring.application.version:0.0.1}")
    private String versionAPI;

    /** Representa las propiedades de swagger. */
    private final SwaggerProperties swaggerProperties;
    
    @Bean
    OpenAPI customOpenAPI() {
        OpenAPI openAPI = new OpenAPI()
            .info(new Info()
                .title(nombreProyecto)
                .description("""
                    Documentación donde se define la funcionalidad de los procesos para la
                    **administración de catalogos**, por medio de servicios API REST. 
                    ---
                    *Dependencia:* **Todas las dependencias**
                 """)
                .version(versionAPI)
                .termsOfService("https://www.ventanillaunica.gob.mx/vucem/index.html")
                .contact(new Contact()
                    .name("Equipo de Desarrollo")
                    .email("soporte@ultrasist.com")
                    .url("https://www.ultrasist.mx/ultrasite/")
                )
            );
        if (Objects.nonNull(swaggerProperties.getServers())) {
            swaggerProperties.getServers().forEach(serverConfig -> 
                openAPI.addServersItem(new Server()
                    .url(serverConfig.getUrl())
                    .description(serverConfig.getDescription()))
            );
        }
        return openAPI;
    }
    
    @Bean
    GroupedOpenApi configuracion() {
        return GroupedOpenApi.builder()
                .group("api")
                .pathsToMatch("/api/**")
                .addOpenApiCustomizer(openApi -> openApi.getPaths()
                    .forEach((key, pathItem) -> pathItem.readOperations()
                        .forEach(operation -> {
                            operation.getResponses().remove("400");
                            operation.getResponses().remove("405");
                            operation.getResponses().remove("500");
                        })))
                .build();
    }
}