package mx.gob.sat.catalogo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * <b>Class:</b> CorsConfig.java <br>
 * <b>Description:</b>
 * <p> Configuración CORS dinámica basada en propiedades del archivo yml. </p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 06 de septiembre del 2025
 * @version 1.0
 * @category Configuración
 */
@Configuration
@ConfigurationProperties(prefix = "app.cors")
@Getter
@Setter
public class CorsConfig {

    /** Tiempo de cache para respuestas preflight CORS (1 hora en segundos). */
    private static final long CORS_MAX_AGE_SECONDS = 3600L;
    /** Comodín para permitir todos los valores. */
    private static final String COMODIN = "*";
    /** Representa la lista de origenes permitidos. */
    private List<String> allowedOrigins;
    /** Representa los encabezados permitidos. */
    private String allowedHeaders;
    /** Representa los metodos permitidos. */
    private List<String> allowedMethods;
    /** Representa si es necesario encabezados de seguridad. */
    private boolean allowCredentials;

    /**
     * Configura los cors de la aplicación.
     *
     * @return Retorna la configuración
     */
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        /** Configurar origins dinámicamente. */
        if (allowedOrigins != null) {
            allowedOrigins.forEach(origin -> {
                if (origin.contains("localhost:*")) {
                    configuration.addAllowedOriginPattern("http://localhost:*");
                    configuration.addAllowedOriginPattern("https://localhost:*");
                } else {
                    configuration.addAllowedOrigin(origin);
                }
            });
        }

        /** Configurar headers permitidos. */
        if (allowedHeaders != null && !COMODIN.equals(allowedHeaders)) {
            configuration.setAllowedHeaders(List.of(allowedHeaders.split(",")));
        } else {
            configuration.addAllowedHeader(COMODIN);
        }

        /** Configurar métodos permitidos. */
        if (allowedMethods != null && !allowedMethods.isEmpty()) {
            configuration.setAllowedMethods(allowedMethods);
        } else {
            configuration.addAllowedMethod(COMODIN);
        }

        configuration.setAllowCredentials(allowCredentials);

        /**
         * MaxAge: Optimiza el rendimiento de peticiones CORS cacheando respuestas preflight.
         *
         * Cuando Angular envía peticiones con headers personalizados (authorization: Bearer token),
         * el navegador primero envía una petición OPTIONS para verificar permisos CORS.
         *
         * Ventajas de MaxAge (3600 segundos = 1 hora):
         * - Reduce latencia: Menos peticiones de red al servidor
         * - Mejora UX: Respuestas más rápidas en el frontend
         * - Reduce carga: Menos tráfico OPTIONS repetitivo
         *
         * Sin MaxAge: OPTIONS + POST en cada petición
         * Con MaxAge: OPTIONS solo la primera vez, luego solo POST por 1 hora
         */
        configuration.setMaxAge(CORS_MAX_AGE_SECONDS);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    /**
     * Filtro CORS para asegurar que las peticiones preflight se manejen correctamente.
     *
     * @return Filtro CORS configurado
     */
    @Bean
    CorsFilter corsFilter() {
        return new CorsFilter(corsConfigurationSource());
    }
}