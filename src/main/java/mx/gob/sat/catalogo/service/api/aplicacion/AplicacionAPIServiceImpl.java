package mx.gob.sat.catalogo.service.api.aplicacion;

import lombok.RequiredArgsConstructor;
import mx.gob.sat.basems.controller.response.AplicacionResponse;
import mx.gob.sat.basems.service.api.aplicacion.AplicacionAPIService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * <b>Class:</b> AplicacionAPIServiceImpl.java <br>
 * <b>Description:</b>
 * <p>Implementacion del servicio de informacion de la aplicacion.
 * Lee nombre y version directamente de las propiedades del proyecto.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Implementacion del servicio API
 */
@Service
@RequiredArgsConstructor
class AplicacionAPIServiceImpl implements AplicacionAPIService {

    /** Nombre del microservicio leido desde las propiedades del proyecto. */
    @Value("${spring.application.name:catalogo-admin}")
    private String nombre;

    /** Version del microservicio leida desde las propiedades del proyecto. */
    @Value("${info.app.version:0.0.2}")
    private String version;

    /**
     * Retorna la informacion general del microservicio.
     *
     * @return Objeto con nombre, version y descripcion del servicio.
     */
    @Override
    public AplicacionResponse getInformacionMS() {
        return AplicacionResponse.builder()
                .nombre(nombre)
                .version(version)
                .descripcion("Microservicio de administracion del catalogo VUCEM")
                .build();
    }
}
