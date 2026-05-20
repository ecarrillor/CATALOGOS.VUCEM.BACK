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
import mx.gob.sat.catalogo.controller.request.subdivisionfraccion.CatSubdivisionFraccionRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.subdivisionfraccion.CatSubdivisionFraccionResponse;
import mx.gob.sat.catalogo.service.api.subdivisionfraccion.CatSubdivisionFraccionAPIService;
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

@Tag(name = "Cat. Subdivision Fraccion", description = "...")
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class CatSubdivisionFraccionController extends ApiBaseController {

    private final CatSubdivisionFraccionAPIService catSubdivisionFraccionAPIService;

    @Operation(operationId = "listar-subdivision-fraccion", summary = "Lista los registros Subdivision Fraccion paginados")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = PaginaResponse.class)))
    })
    @GetMapping(value = UrlConst.SUBDIVISION_FRACCION_LISTAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<PaginaResponse<CatSubdivisionFraccionResponse>> listar(
            @RequestParam(defaultValue = "0") final int pagina,
            @RequestParam(defaultValue = "20") final int tamano,
            @RequestParam(required = false) final String busqueda,
            @RequestParam(required = false) final String sortBy,
            @RequestParam(required = false) final String sortDir) {
        return catSubdivisionFraccionAPIService.listar(pagina, tamano, busqueda, sortBy, sortDir);
    }

    @Operation(operationId = "crear-subdivision-fraccion", summary = "Crea un nuevo registro Subdivision Fraccion")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatSubdivisionFraccionResponse.class)))
    })
    @PostMapping(value = UrlConst.SUBDIVISION_FRACCION_CREAR, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatSubdivisionFraccionResponse> crear(@Valid @RequestBody final CatSubdivisionFraccionRequest request) {
        return catSubdivisionFraccionAPIService.crear(request);
    }

    @Operation(operationId = "buscar-subdivision-fraccion", summary = "Busca un registro Subdivision Fraccion por clave")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatSubdivisionFraccionResponse.class)))
    })
    @GetMapping(value = UrlConst.SUBDIVISION_FRACCION_BUSCAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatSubdivisionFraccionResponse> findById(
            @Parameter(description = "Clave Subdivision") @PathVariable final String cveSubdivision) {
        return catSubdivisionFraccionAPIService.findById(cveSubdivision);
    }

    @Operation(operationId = "actualizar-subdivision-fraccion", summary = "Actualiza un registro Subdivision Fraccion")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatSubdivisionFraccionResponse.class)))
    })
    @PutMapping(value = UrlConst.SUBDIVISION_FRACCION_ACTUALIZAR, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatSubdivisionFraccionResponse> actualizar(
            @Parameter(description = "Clave Subdivision") @PathVariable final String cveSubdivision,
            @RequestBody final CatSubdivisionFraccionRequest request) {
        return catSubdivisionFraccionAPIService.actualizar(cveSubdivision, request);
    }
}
