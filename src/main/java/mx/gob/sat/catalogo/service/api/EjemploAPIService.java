package mx.gob.sat.catalogo.service.api;

import java.util.HashMap;
import java.util.List;

import mx.gob.sat.basems.controller.response.BaseResponse;

/**
 * <b>Interface:</b> EjemploAPIService.java <br>
 * <b>Description:</b>
 * <p>Interfaz que define los servicios de ejemplo para demostración.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 20 de junio del 2025
 * @version 1.0
 * @category Servicio API
 */
public interface EjemploAPIService {

    /**
     * Obtiene datos de ejemplo para demostración.
     * 
     * @return Lista de datos de ejemplo
     */
    BaseResponse<List<HashMap<String, String>>> getDatosEjemplo();
}