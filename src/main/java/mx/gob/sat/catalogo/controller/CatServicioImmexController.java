package mx.gob.sat.catalogo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.basems.utils.consts.GeneralConst;
import mx.gob.sat.catalogo.controller.request.servicioimmex.CatServicioImmexRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.servicioimmex.CatServicioImmexResponse;
import mx.gob.sat.catalogo.service.api.servicioimmex.CatServicioImmexAPIService;
import mx.gob.sat.catalogo.util.consts.UrlConst;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <b>Class:</b> CatServicioImmexController.java <br>
 * <b>Description:</b>
 * <p>Controlador REST para el catalogo Servicio Immex.
 * Expone endpoints bajo la ruta base {@code api/catalogo-admin/servicio-immex}.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Controlador
 */
@Tag(name = "Cat. Servicio Immex",
        description = "Servicios de consulta y mantenimiento del catalogo Servicio Immex")
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class CatServicioImmexController extends ApiBaseController {

    private final CatServicioImmexAPIService catServicioImmexAPIService;

    @Operation(operationId = "listar-servicio-immex", summary = "Lista los registros Servicio Immex paginados",
        description = "Retorna una pagina de registros Servicio Immex con soporte de busqueda y ordenamiento.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = PaginaResponse.class)))
    })
    @GetMapping(value = UrlConst.SERVICIO_IMMEX_LISTAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<PaginaResponse<CatServicioImmexResponse>> listar(
            @RequestParam(defaultValue = "0") final int pagina,
            @RequestParam(defaultValue = "20") final int tamano,
            @RequestParam(required = false) final String busqueda,
            @RequestParam(required = false) final String sortBy,
            @RequestParam(required = false) final String sortDir) {
        return catServicioImmexAPIService.listar(pagina, tamano, busqueda, sortBy, sortDir);
    }

    @Operation(operationId = "crear-servicio-immex", summary = "Crea un nuevo registro Servicio Immex")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatServicioImmexResponse.class)))
    })
    @PostMapping(value = UrlConst.SERVICIO_IMMEX_CREAR,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatServicioImmexResponse> crear(@Valid @RequestBody final CatServicioImmexRequest request) {
        return catServicioImmexAPIService.crear(request);
    }

    @Operation(operationId = "buscar-servicio-immex", summary = "Busca un registro Servicio Immex por id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatServicioImmexResponse.class)))
    })
    @GetMapping(value = UrlConst.SERVICIO_IMMEX_BUSCAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatServicioImmexResponse> findById(
            @Parameter(description = "Id Servicio Immex") @PathVariable final Short idServicioImmex) {
        return catServicioImmexAPIService.findById(idServicioImmex);
    }

    @Operation(operationId = "actualizar-servicio-immex", summary = "Actualiza un registro Servicio Immex")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatServicioImmexResponse.class)))
    })
    @PutMapping(value = UrlConst.SERVICIO_IMMEX_ACTUALIZAR,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatServicioImmexResponse> actualizar(
            @Parameter(description = "Id Servicio Immex") @PathVariable final Short idServicioImmex,
            @RequestBody final CatServicioImmexRequest request) {
        return catServicioImmexAPIService.actualizar(idServicioImmex, request);
    }
}
