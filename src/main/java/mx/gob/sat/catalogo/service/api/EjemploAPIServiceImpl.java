package mx.gob.sat.catalogo.service.api;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import mx.gob.sat.basems.controller.response.BaseResponse;

/**
 * <b>Class:</b> EjemploAPIServiceImpl.java <br>
 * <b>Description:</b>
 * <p>
 * Clase que implementa los servicios de la interfaz {@code EjemploAPIService}.
 * </p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 30 de mayo del 2025
 * @version 1.0
 * @category Implementación del servicio API
 */
@Service
@RequiredArgsConstructor
class EjemploAPIServiceImpl implements EjemploAPIService {

    /****** VARIABLES GLOBALES ******/

    /****** SERVICIOS INTERNOS ******/

    /****** SERVICIOS EXTERNOS ******/
    
    public BaseResponse<List<HashMap<String, String>>> getDatosEjemplo() {
        throw new UnsupportedOperationException("Unimplemented method 'getDatosEjemplo'");
    }
}