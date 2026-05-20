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
import mx.gob.sat.catalogo.controller.request.fraccionarancelaria.CatFraccionArancelariaRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.fraccionarancelaria.CatFraccionArancelariaResponse;
import mx.gob.sat.catalogo.service.api.fraccionarancelaria.CatFraccionArancelariaAPIService;
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

@Tag(name = "Cat. Fraccion Arancelaria", description = "...")
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class CatFraccionArancelariaController extends ApiBaseController {

    private final CatFraccionArancelariaAPIService catFraccionArancelariaAPIService;

    @Operation(operationId = "listar-fraccion-arancelaria", summary = "Lista los registros Fraccion Arancelaria paginados")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = PaginaResponse.class)))
    })
    @GetMapping(value = UrlConst.FRACCION_ARANCELARIA_LISTAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<PaginaResponse<CatFraccionArancelariaResponse>> listar(
            @RequestParam(defaultValue = "0") final int pagina,
            @RequestParam(defaultValue = "20") final int tamano,
            @RequestParam(required = false) final String busqueda,
            @RequestParam(required = false) final String sortBy,
            @RequestParam(required = false) final String sortDir) {
        return catFraccionArancelariaAPIService.listar(pagina, tamano, busqueda, sortBy, sortDir);
    }

    @Operation(operationId = "crear-fraccion-arancelaria", summary = "Crea un nuevo registro Fraccion Arancelaria")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatFraccionArancelariaResponse.class)))
    })
    @PostMapping(value = UrlConst.FRACCION_ARANCELARIA_CREAR, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatFraccionArancelariaResponse> crear(@Valid @RequestBody final CatFraccionArancelariaRequest request) {
        return catFraccionArancelariaAPIService.crear(request);
    }

    @Operation(operationId = "buscar-fraccion-arancelaria", summary = "Busca un registro Fraccion Arancelaria por clave")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatFraccionArancelariaResponse.class)))
    })
    @GetMapping(value = UrlConst.FRACCION_ARANCELARIA_BUSCAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatFraccionArancelariaResponse> findById(
            @Parameter(description = "Clave Fraccion Arancelaria") @PathVariable final String cveFraccion) {
        return catFraccionArancelariaAPIService.findById(cveFraccion);
    }

    @Operation(operationId = "actualizar-fraccion-arancelaria", summary = "Actualiza un registro Fraccion Arancelaria")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatFraccionArancelariaResponse.class)))
    })
    @PutMapping(value = UrlConst.FRACCION_ARANCELARIA_ACTUALIZAR, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatFraccionArancelariaResponse> actualizar(
            @Parameter(description = "Clave Fraccion Arancelaria") @PathVariable final String cveFraccion,
            @RequestBody final CatFraccionArancelariaRequest request) {
        return catFraccionArancelariaAPIService.actualizar(cveFraccion, request);
    }
}
