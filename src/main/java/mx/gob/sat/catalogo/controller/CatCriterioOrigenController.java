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
import mx.gob.sat.catalogo.controller.request.criterioorigen.CatCriterioOrigenRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.criterioorigen.CatCriterioOrigenResponse;
import mx.gob.sat.catalogo.service.api.criterioorigen.CatCriterioOrigenAPIService;
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

@Tag(name = "Cat. Criterio Origen", description = "...")
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class CatCriterioOrigenController extends ApiBaseController {

    private final CatCriterioOrigenAPIService catCriterioOrigenAPIService;

    @Operation(operationId = "listar-criterio-origen", summary = "Lista los registros Criterio Origen paginados", description = "...")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = PaginaResponse.class)))
    })
    @GetMapping(value = UrlConst.CRITERIO_ORIGEN_LISTAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<PaginaResponse<CatCriterioOrigenResponse>> listar(
            @RequestParam(defaultValue = "0") final int pagina,
            @RequestParam(defaultValue = "20") final int tamano,
            @RequestParam(required = false) final String busqueda,
            @RequestParam(required = false) final String sortBy,
            @RequestParam(required = false) final String sortDir) {
        return catCriterioOrigenAPIService.listar(pagina, tamano, busqueda, sortBy, sortDir);
    }

    @Operation(operationId = "crear-criterio-origen", summary = "Crea un nuevo registro Criterio Origen")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatCriterioOrigenResponse.class)))
    })
    @PostMapping(value = UrlConst.CRITERIO_ORIGEN_CREAR, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatCriterioOrigenResponse> crear(@Valid @RequestBody final CatCriterioOrigenRequest request) {
        return catCriterioOrigenAPIService.crear(request);
    }

    @Operation(operationId = "buscar-criterio-origen", summary = "Busca un registro Criterio Origen por clave")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatCriterioOrigenResponse.class)))
    })
    @GetMapping(value = UrlConst.CRITERIO_ORIGEN_BUSCAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatCriterioOrigenResponse> findById(
            @Parameter(description = "Clave Criterio Origen") @PathVariable final String cveCriterioOrigen) {
        return catCriterioOrigenAPIService.findById(cveCriterioOrigen);
    }

    @Operation(operationId = "actualizar-criterio-origen", summary = "Actualiza un registro Criterio Origen")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatCriterioOrigenResponse.class)))
    })
    @PutMapping(value = UrlConst.CRITERIO_ORIGEN_ACTUALIZAR, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatCriterioOrigenResponse> actualizar(
            @Parameter(description = "Clave Criterio Origen") @PathVariable final String cveCriterioOrigen,
            @RequestBody final CatCriterioOrigenRequest request) {
        return catCriterioOrigenAPIService.actualizar(cveCriterioOrigen, request);
    }
}
