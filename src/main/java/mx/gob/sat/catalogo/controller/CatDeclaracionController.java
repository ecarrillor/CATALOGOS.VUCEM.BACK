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
import mx.gob.sat.catalogo.controller.request.declaracion.CatDeclaracionRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.declaracion.CatDeclaracionResponse;
import mx.gob.sat.catalogo.service.api.declaracion.CatDeclaracionAPIService;
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

@Tag(name = "Cat. Declaracion", description = "...")
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class CatDeclaracionController extends ApiBaseController {

    private final CatDeclaracionAPIService catDeclaracionAPIService;

    @Operation(operationId = "listar-declaracion", summary = "Lista los registros Declaracion paginados", description = "...")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = PaginaResponse.class)))
    })
    @GetMapping(value = UrlConst.DECLARACION_LISTAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<PaginaResponse<CatDeclaracionResponse>> listar(
            @RequestParam(defaultValue = "0") final int pagina,
            @RequestParam(defaultValue = "20") final int tamano,
            @RequestParam(required = false) final String busqueda,
            @RequestParam(required = false) final String sortBy,
            @RequestParam(required = false) final String sortDir) {
        return catDeclaracionAPIService.listar(pagina, tamano, busqueda, sortBy, sortDir);
    }

    @Operation(operationId = "crear-declaracion", summary = "Crea un nuevo registro Declaracion")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatDeclaracionResponse.class)))
    })
    @PostMapping(value = UrlConst.DECLARACION_CREAR, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatDeclaracionResponse> crear(@Valid @RequestBody final CatDeclaracionRequest request) {
        return catDeclaracionAPIService.crear(request);
    }

    @Operation(operationId = "buscar-declaracion", summary = "Busca un registro Declaracion por clave")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatDeclaracionResponse.class)))
    })
    @GetMapping(value = UrlConst.DECLARACION_BUSCAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatDeclaracionResponse> findById(
            @Parameter(description = "Clave Declaracion") @PathVariable final String cveDeclaracion) {
        return catDeclaracionAPIService.findById(cveDeclaracion);
    }

    @Operation(operationId = "actualizar-declaracion", summary = "Actualiza un registro Declaracion")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatDeclaracionResponse.class)))
    })
    @PutMapping(value = UrlConst.DECLARACION_ACTUALIZAR, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatDeclaracionResponse> actualizar(
            @Parameter(description = "Clave Declaracion") @PathVariable final String cveDeclaracion,
            @RequestBody final CatDeclaracionRequest request) {
        return catDeclaracionAPIService.actualizar(cveDeclaracion, request);
    }
}
