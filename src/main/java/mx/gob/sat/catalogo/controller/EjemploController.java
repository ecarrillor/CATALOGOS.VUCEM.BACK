package mx.gob.sat.catalogo.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.http.MediaType;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.basems.utils.consts.GeneralConst;
import mx.gob.sat.catalogo.service.api.EjemploAPIService;
import mx.gob.sat.catalogo.util.consts.UrlConst;

/**
 * <b>Class:</b> EjemploController.java <br>
 * <b>Description:</b>
 * <p>Controlador de ejemplo que demuestra la implementación de endpoints REST.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 20 de junio del 2025
 * @version 1.0
 * @category Controlador
 */
@Tag(name = "Ejemplos",
        description = "Servicios de ejemplo para demostración")
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class EjemploController extends ApiBaseController {

    /****** VARIABLES GLOBALES ********/

    /****** SERVICIOS API ********/
    /** Permite consumir los servicios de ejemplo. */
    private final EjemploAPIService ejemploAPIService;

    /****** SERVICIOS EXTERNOS ********/

    /**
     * Servicio de ejemplo que consulta datos de muestra.
     * 
     * @return Retorna una lista de objetos de tipo {@code BaseResponse<List<HashMap<String,String>>>}
     * con información de ejemplo.
     */
    @Operation(
        operationId = "consulta-ejemplos", 
        summary = "Consulta datos de ejemplo",
        description = "Servicio que permite consultar datos de **ejemplo** para demostración.", 
        responses = {
            @ApiResponse(
                responseCode = "200", 
                description = GeneralConst.MENSAJE_OPERACION_EXITOSA, 
                content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE, 
                    schema = @Schema(
                        implementation = List.class)))
        }
    )
    @GetMapping(value = UrlConst.EJEMPLO_CONSULTA_DATOS, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<List<HashMap<String, String>>> getDatosEjemplo() {
        return ejemploAPIService.getDatosEjemplo();
    }
}
