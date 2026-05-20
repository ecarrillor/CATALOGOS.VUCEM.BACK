package mx.gob.sat.catalogo.config;

import feign.RequestInterceptor;
import io.micrometer.tracing.TraceContext;
import io.micrometer.tracing.Tracer;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * <b>Class:</b> TracingConfig.java <br>
 * <b>Description:</b>
 * <p> Configuración unificada de trazabilidad distribuida para peticiones entrantes y salientes. </p>
 * <p> Maneja tanto la recepción de headers OpenTelemetry en peticiones HTTP entrantes como
 * el envío de headers de trazabilidad en llamadas Feign salientes. </p>
 *
 * <p><b>Funcionalidades:</b></p>
 * <ul>
 *   <li><b>Peticiones entrantes:</b> Extrae traceId del header 'traceparent' y lo propaga al MDC</li>
 *   <li><b>Peticiones salientes:</b> Añade header 'traceparent' a todas las llamadas Feign</li>
 * </ul>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 07 de octubre del 2025
 * @version 1.0
 * @category Configuración
 */
@Configuration
@RequiredArgsConstructor
public class TracingConfig {

    /** Tracer de Micrometer para obtener contexto de trazabilidad. */
    private final Tracer tracer;

    /** Longitud maxima para el trace. */
    private static final int LONGITUD_MAX = 55;
    /** Representa la posición inicial del trace. */
    private static final int TRACE_POSICION_INICIAL = 3;
    /** Representa la posición final del trace. */
    private static final int TRACE_POSICION_FINAL = 35;

    /**
     * Configura un filtro para recibir y procesar headers de trazabilidad en peticiones entrantes.
     *
     * <p>Este filtro extrae el traceId del header 'traceparent' estándar de OpenTelemetry
     * y lo propaga al MDC (Mapped Diagnostic Context) para correlación de logs.</p>
     *
     * <p><b>Formato del header traceparent:</b> 00-traceId-spanId-01</p>
     *
     * @return OncePerRequestFilter configurado para trazabilidad entrante
     */
    @Bean
    OncePerRequestFilter tracingFilter() {
        return new OncePerRequestFilter() {
            @Override
            protected void doFilterInternal(final @NonNull HttpServletRequest request,
                                            final @NonNull HttpServletResponse response,
                                            final @NonNull FilterChain filterChain)
                    throws ServletException, IOException {
                String traceparent = request.getHeader("traceparent");
                if (traceparent != null && traceparent.length() >= LONGITUD_MAX) {
                    // Formato: 00-traceId-spanId-01
                    String traceId = traceparent.substring(TRACE_POSICION_INICIAL, TRACE_POSICION_FINAL);
                    // Propagar traceId en MDC para logs
                    org.slf4j.MDC.put("traceId", traceId);
                }

                try {
                    filterChain.doFilter(request, response);
                } finally {
                    org.slf4j.MDC.clear();
                }
            }
        };
    }

    /**
     * Configura un interceptor para propagar headers de trazabilidad en peticiones salientes Feign.
     *
     * <p>Este interceptor automáticamente añade el header 'traceparent' estándar de OpenTelemetry
     * a todas las peticiones HTTP realizadas por clientes Feign, permitiendo
     * el seguimiento de requests a través de múltiples microservicios.</p>
     *
     * <p><b>Beneficios:</b></p>
     * <ul>
     *   <li>Trazabilidad end-to-end entre microservicios</li>
     *   <li>Debugging simplificado en arquitecturas distribuidas</li>
     *   <li>Monitoreo de performance cross-service</li>
     *   <li>Correlación automática de logs</li>
     * </ul>
     *
     * @return RequestInterceptor configurado para trazabilidad saliente
     */
    @Bean
    RequestInterceptor tracingRequestInterceptor() {
        return requestTemplate -> {
            TraceContext traceContext = tracer.currentTraceContext().context();
            if (traceContext != null) {
                // Header estándar OpenTelemetry
                requestTemplate.header("traceparent",
                        String.format("00-%s-%s-01", traceContext.traceId(), traceContext.spanId()));
            }
        };
    }
}