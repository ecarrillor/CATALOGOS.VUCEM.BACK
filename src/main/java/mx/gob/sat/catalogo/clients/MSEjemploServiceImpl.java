package mx.gob.sat.catalogo.clients;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import mx.gob.sat.basems.clients.MicroserviceClient;
import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.catalogo.controller.request.EjemploRequest;
/**
 * <b>Class:</b>MSEjemploServiceImpl.java <br>
 * <b>Description:</b>
 * <p>
 * Clase que implementa los servicios de la intefaz {@code MSEjemploService}.
 * </p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 01 de julio del 2025
 * @version 1.0
 * @category Servicio de Implementación
 */
@Slf4j
@Service
class MSEjemploServiceImpl extends MicroserviceClient implements MSEjemploService {

    /****** VARIABLES GLOBALES. ********/
    private static final String NOMBRE_APLICACION = "ms-ejemplo";

    /** Representa el cliente para consumo del microservicio ms-ejemplo. */
    private final EjemploClient ejemploClient;

    MSEjemploServiceImpl(final EjemploClient ejemploClient) {
        super(NOMBRE_APLICACION);
        this.ejemploClient = ejemploClient;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public BaseResponse<String> firmarGenerarRequerimiento(final String numFolioTramite,
            final EjemploRequest request) {
        return ejecutar(
            "firmarGenerarRequerimiento",
            () -> ejemploClient.firmarGenerarRequerimiento(numFolioTramite, request)
        );
    }
}
