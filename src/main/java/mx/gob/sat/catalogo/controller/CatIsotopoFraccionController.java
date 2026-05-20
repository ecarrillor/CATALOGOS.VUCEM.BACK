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
import mx.gob.sat.catalogo.controller.request.isotopofraccion.CatIsotopoFraccionRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.isotopofraccion.CatIsotopoFraccionResponse;
import mx.gob.sat.catalogo.service.api.isotopofraccion.CatIsotopoFraccionAPIService;
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

@Tag(name = "Cat. Isotopo Fraccion", description = "...")
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class CatIsotopoFraccionController extends ApiBaseController {

    private final CatIsotopoFraccionAPIService catIsotopoFraccionAPIService;

    @Operation(operationId = "listar-isotopo-fraccion", summary = "Lista los registros Isotopo Fraccion paginados")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = PaginaResponse.class)))
    })
    @GetMapping(value = UrlConst.ISOTOPO_FRACCION_LISTAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<PaginaResponse<CatIsotopoFraccionResponse>> listar(
            @RequestParam(defaultValue = "0") final int pagina,
            @RequestParam(defaultValue = "20") final int tamano,
            @RequestParam(required = false) final String busqueda,
            @RequestParam(required = false) final String sortBy,
            @RequestParam(required = false) final String sortDir) {
        return catIsotopoFraccionAPIService.listar(pagina, tamano, busqueda, sortBy, sortDir);
    }

    @Operation(operationId = "crear-isotopo-fraccion", summary = "Crea un nuevo registro Isotopo Fraccion")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatIsotopoFraccionResponse.class)))
    })
    @PostMapping(value = UrlConst.ISOTOPO_FRACCION_CREAR, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatIsotopoFraccionResponse> crear(@Valid @RequestBody final CatIsotopoFraccionRequest request) {
        return catIsotopoFraccionAPIService.crear(request);
    }

    @Operation(operationId = "buscar-isotopo-fraccion", summary = "Busca un registro Isotopo Fraccion por id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatIsotopoFraccionResponse.class)))
    })
    @GetMapping(value = UrlConst.ISOTOPO_FRACCION_BUSCAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatIsotopoFraccionResponse> findById(
            @Parameter(description = "Id Isotopo") @PathVariable final Short idIsotopo) {
        return catIsotopoFraccionAPIService.findById(idIsotopo);
    }

    @Operation(operationId = "actualizar-isotopo-fraccion", summary = "Actualiza un registro Isotopo Fraccion")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatIsotopoFraccionResponse.class)))
    })
    @PutMapping(value = UrlConst.ISOTOPO_FRACCION_ACTUALIZAR, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatIsotopoFraccionResponse> actualizar(
            @Parameter(description = "Id Isotopo") @PathVariable final Short idIsotopo,
            @RequestBody final CatIsotopoFraccionRequest request) {
        return catIsotopoFraccionAPIService.actualizar(idIsotopo, request);
    }
}
