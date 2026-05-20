package mx.gob.sat.catalogo.controller;

import lombok.extern.slf4j.Slf4j;
import mx.gob.sat.basems.controller.BaseExcepcionController;
import mx.gob.sat.basems.controller.response.BaseErrorResponse;
import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.basems.utils.consts.GeneralConst;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Objects;

/**
 * <b>Class:</b> ExcepcionController.java <br>
 * <b>Description:</b>
 * <p> Controlador para el manejo de excepciones que se necesitan
 * retornar con un codigo http diferente al 200.
 * </p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 * 
 * @created 17 de febrero del 2025
 * @version 1.0
 * @category Controlador
 */
@Slf4j
@RestControllerAdvice
public class ExcepcionController extends BaseExcepcionController {

    private static final String LOG_SALIDA_EXCEPCION = "Respuesta: {} --> {}";

    /**
     * Control de exception {@code HttpRequestMethodNotSupportedException} cuando un cliente intenta usar un
     * método HTTP que no está soportado por el endpoint.
     *
     * <p>
     *  Respuesta de http 405
     * </p>
     * Codigo de error 05 - El método no es compatible con esta solicitud
     *
     * @param ex - Representa la excepcion
     * @return Retorna el json con la información
     */
    @SuppressWarnings("null")
    @ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(org.springframework.web.HttpRequestMethodNotSupportedException.class)
    public BaseResponse<String> processUnmergeException(final HttpRequestMethodNotSupportedException ex) {
        final StringBuilder builder = new StringBuilder();
        builder.append("El método ");
        builder.append(ex.getMethod());
        builder.append(" no es compatible con esta solicitud. Los métodos admitidos son: ");
        if (Objects.nonNull(ex.getSupportedHttpMethods())) {
            for (HttpMethod metodo:ex.getSupportedHttpMethods()) {
                builder.append(metodo).append(" ");
            }
        }
        BaseResponse<String> response = new BaseResponse<>();
        response.setCodigo("05");
        response.setMensaje(GeneralConst.MENSAJE_OPERACION_FALLIDA);
        response.setError("Método HTTP no soportado.");
        response.setCausa(builder.toString());
        log.warn(LOG_SALIDA_EXCEPCION, ex.getClass().getSimpleName(), response);
        return response;
    }

    /**
     * Control de exception {@code NoHandlerFoundException} cuando no se encuentra un recurso estático.
     *
     * <p>
     *  Respuesta de http 404
     * </p>
     * Codigo de error 06 - Recurso no encontrado
     *
     * @param ex - Representa la excepcion
     * @return Retorna el json con la información del error
     */
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoHandlerFoundException.class)
    public BaseErrorResponse handleNoResourceFoundException(final NoHandlerFoundException ex) {
        log.warn("Recurso no encontrado: {}", ex.getMessage());
        BaseErrorResponse response = new BaseErrorResponse();
        response.setCodigo("06");
        response.setMensaje(GeneralConst.MENSAJE_OPERACION_FALLIDA);
        response.setError("Recurso no encontrado");
        response.setCausa("El recurso solicitado no existe en el servidor.");
        log.warn(LOG_SALIDA_EXCEPCION, ex.getClass().getSimpleName(), response);
        return response;
    }
}