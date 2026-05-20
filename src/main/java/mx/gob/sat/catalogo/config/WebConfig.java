package mx.gob.sat.catalogo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.RequiredArgsConstructor;
import mx.gob.sat.catalogo.interceptor.RendimientoInterceptor;

/**
 * <b>Class:</b> WebConfig.java <br>
 * <b>Description:</b>
 * <p>
 * Registra los interceptores de la aplicación.
 * </p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 06 de septiembre del 2025
 * @version 1.0
 * @category Configuracion
 */
@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final RendimientoInterceptor rendimientoInterceptor;

    /**
     * Registra los interceptores de la aplicación.
     *
     * @param registry - Represeta el objeto donde se registra el interceptor
     */
    @Override
    public void addInterceptors(@NonNull final InterceptorRegistry registry) {
        registry.addInterceptor(rendimientoInterceptor)
                .addPathPatterns("/api/**")
                .excludePathPatterns("/api/*/actuator/**");
    }
}