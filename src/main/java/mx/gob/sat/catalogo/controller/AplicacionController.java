package mx.gob.sat.catalogo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import mx.gob.sat.basems.controller.response.AplicacionResponse;
import mx.gob.sat.basems.service.api.aplicacion.AplicacionAPIService;
import mx.gob.sat.catalogo.util.consts.UrlConst;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <b>Class:</b> AplicacionController.java <br>
 * <b>Description:</b>
 * <p> Controlador que expone los endpoint relacionados con la aplicación. </p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 * 
 * @created 17 de febrero del 2025
 * @version 1.0
 * @category Controlador
 */
@Tag(name = "Aplicación", description = "Servicios relacionados a la aplicación")
@Slf4j
@RestController
@RequiredArgsConstructor
public class AplicacionController extends ApiBaseController {

    /****** SERVICIOS API ********/
    /** Representa el acceso a los servicios de ejemplo {@code AplicacionAPIService}. */
    private final AplicacionAPIService aplicacionAPIService;

    /**
     * Servicio que permite consultar la información de la aplicación.
     *
     * @return Retorna información relevante de la aplicación.
     */
    @Operation(
        operationId = "getVersionApp",
        summary = "Consulta la información de la aplicación.",
        description = "Servicio que permite consultar la información de la aplicación."
    )
    @ApiResponses(
        value = {
            @ApiResponse(
                responseCode = "200",
                content = @Content(schema = @Schema(implementation = AplicacionResponse.class))
                )
        }
    )
    @GetMapping(value = UrlConst.APLICACION_VERSION, produces = MediaType.APPLICATION_JSON_VALUE)
    public AplicacionResponse getVersionApp() {
        return aplicacionAPIService.getInformacionMS();
    }
}
