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
import mx.gob.sat.catalogo.controller.request.capitulofraccion.CatCapituloFraccionRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.capitulofraccion.CatCapituloFraccionResponse;
import mx.gob.sat.catalogo.service.api.capitulofraccion.CatCapituloFraccionAPIService;
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

@Tag(name = "Cat. Capitulo Fraccion", description = "...")
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class CatCapituloFraccionController extends ApiBaseController {

    private final CatCapituloFraccionAPIService catCapituloFraccionAPIService;

    @Operation(operationId = "listar-capitulo-fraccion", summary = "Lista los registros Capitulo Fraccion paginados", description = "...")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = PaginaResponse.class)))
    })
    @GetMapping(value = UrlConst.CAPITULO_FRACCION_LISTAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<PaginaResponse<CatCapituloFraccionResponse>> listar(
            @RequestParam(defaultValue = "0") final int pagina,
            @RequestParam(defaultValue = "20") final int tamano,
            @RequestParam(required = false) final String busqueda,
            @RequestParam(required = false) final String sortBy,
            @RequestParam(required = false) final String sortDir) {
        return catCapituloFraccionAPIService.listar(pagina, tamano, busqueda, sortBy, sortDir);
    }

    @Operation(operationId = "crear-capitulo-fraccion", summary = "Crea un nuevo registro Capitulo Fraccion")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatCapituloFraccionResponse.class)))
    })
    @PostMapping(value = UrlConst.CAPITULO_FRACCION_CREAR, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatCapituloFraccionResponse> crear(@Valid @RequestBody final CatCapituloFraccionRequest request) {
        return catCapituloFraccionAPIService.crear(request);
    }

    @Operation(operationId = "buscar-capitulo-fraccion", summary = "Busca un registro Capitulo Fraccion por clave")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatCapituloFraccionResponse.class)))
    })
    @GetMapping(value = UrlConst.CAPITULO_FRACCION_BUSCAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatCapituloFraccionResponse> findById(
            @Parameter(description = "Clave Capitulo Fraccion") @PathVariable final String cveCapituloFraccion) {
        return catCapituloFraccionAPIService.findById(cveCapituloFraccion);
    }

    @Operation(operationId = "actualizar-capitulo-fraccion", summary = "Actualiza un registro Capitulo Fraccion")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatCapituloFraccionResponse.class)))
    })
    @PutMapping(value = UrlConst.CAPITULO_FRACCION_ACTUALIZAR, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatCapituloFraccionResponse> actualizar(
            @Parameter(description = "Clave Capitulo Fraccion") @PathVariable final String cveCapituloFraccion,
            @RequestBody final CatCapituloFraccionRequest request) {
        return catCapituloFraccionAPIService.actualizar(cveCapituloFraccion, request);
    }
}
