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
import mx.gob.sat.catalogo.controller.request.criteriodictaminacion.CatCriterioDictaminacionRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.criteriodictaminacion.CatCriterioDictaminacionResponse;
import mx.gob.sat.catalogo.service.api.criteriodictaminacion.CatCriterioDictaminacionAPIService;
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

@Tag(name = "Cat. Criterio Dictaminacion", description = "...")
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class CatCriterioDictaminacionController extends ApiBaseController {

    private final CatCriterioDictaminacionAPIService catCriterioDictaminacionAPIService;

    @Operation(operationId = "listar-criterio-dictaminacion", summary = "Lista los registros Criterio Dictaminacion paginados", description = "...")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = PaginaResponse.class)))
    })
    @GetMapping(value = UrlConst.CRITERIO_DICTAMINACION_LISTAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<PaginaResponse<CatCriterioDictaminacionResponse>> listar(
            @RequestParam(defaultValue = "0") final int pagina,
            @RequestParam(defaultValue = "20") final int tamano,
            @RequestParam(required = false) final String busqueda,
            @RequestParam(required = false) final String sortBy,
            @RequestParam(required = false) final String sortDir) {
        return catCriterioDictaminacionAPIService.listar(pagina, tamano, busqueda, sortBy, sortDir);
    }

    @Operation(operationId = "crear-criterio-dictaminacion", summary = "Crea un nuevo registro Criterio Dictaminacion")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatCriterioDictaminacionResponse.class)))
    })
    @PostMapping(value = UrlConst.CRITERIO_DICTAMINACION_CREAR, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatCriterioDictaminacionResponse> crear(@Valid @RequestBody final CatCriterioDictaminacionRequest request) {
        return catCriterioDictaminacionAPIService.crear(request);
    }

    @Operation(operationId = "buscar-criterio-dictaminacion", summary = "Busca un registro Criterio Dictaminacion por id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatCriterioDictaminacionResponse.class)))
    })
    @GetMapping(value = UrlConst.CRITERIO_DICTAMINACION_BUSCAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatCriterioDictaminacionResponse> findById(
            @Parameter(description = "Id Criterio Dictaminacion") @PathVariable final Short idCriterioDictaminacion) {
        return catCriterioDictaminacionAPIService.findById(idCriterioDictaminacion);
    }

    @Operation(operationId = "actualizar-criterio-dictaminacion", summary = "Actualiza un registro Criterio Dictaminacion")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatCriterioDictaminacionResponse.class)))
    })
    @PutMapping(value = UrlConst.CRITERIO_DICTAMINACION_ACTUALIZAR, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatCriterioDictaminacionResponse> actualizar(
            @Parameter(description = "Id Criterio Dictaminacion") @PathVariable final Short idCriterioDictaminacion,
            @RequestBody final CatCriterioDictaminacionRequest request) {
        return catCriterioDictaminacionAPIService.actualizar(idCriterioDictaminacion, request);
    }
}
