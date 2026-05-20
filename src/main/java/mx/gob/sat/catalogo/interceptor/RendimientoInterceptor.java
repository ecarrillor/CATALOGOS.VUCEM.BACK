package mx.gob.sat.catalogo.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * <b>Class:</b> RendimientoInterceptor.java <br>
 * <b>Description:</b>
 * <p>
 * Interceptor que mide el tiempo de ejecución de cada solicitud HTTP y registra aquellas que exceden el umbral de 
 * lentitud.
 * </p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 06 de septiembre del 2025
 * @version 1.0
 * @category Interceptor
 */
@Slf4j
@Component
@ConditionalOnProperty(name = "app.interceptor.rendimiento", havingValue = "true", matchIfMissing = true)
public class RendimientoInterceptor implements HandlerInterceptor {

    private static final String START_TIME = "startTime";
    private static final long SLOW_REQUEST_THRESHOLD = 500; //Milisegundos

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean preHandle(final @NonNull HttpServletRequest request, final @NonNull HttpServletResponse response,
                             final @NonNull Object handler) {
        request.setAttribute(START_TIME, System.currentTimeMillis());
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void afterCompletion(final @NonNull HttpServletRequest request, final @NonNull HttpServletResponse response,
                                final @NonNull Object handler, final @Nullable Exception ex) {
        Long startTime = (Long) request.getAttribute(START_TIME);
        if (startTime != null) {
            long duration = System.currentTimeMillis() - startTime;
            String uri = request.getRequestURI();
            String method = request.getMethod();

            if (duration > SLOW_REQUEST_THRESHOLD) {
                log.warn("🐌 SLOW REQUEST: {} {} tardo {}ms", method, uri, duration);
            } else {
                log.info("⚡ {} {} completado en {}ms", method, uri, duration);
            }
        }
    }
}
