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
import mx.gob.sat.catalogo.controller.request.subpartidafraccion.CatSubpartidaFraccionRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.subpartidafraccion.CatSubpartidaFraccionResponse;
import mx.gob.sat.catalogo.service.api.subpartidafraccion.CatSubpartidaFraccionAPIService;
import mx.gob.sat.catalogo.util.consts.UrlConst;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador para el catalogo de subpartida fraccion.
 *
 * @author Javier Chávez Barrios
 * @since 18 de mayo del 2026
 */
@Tag(name = "Cat. Subpartida Fraccion", description = "Operaciones sobre el catalogo de subpartida fraccion")
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class CatSubpartidaFraccionController extends ApiBaseController {

    private final CatSubpartidaFraccionAPIService catSubpartidaFraccionAPIService;

    @Operation(operationId = "listar-subpartida-fraccion", summary = "Lista los registros Subpartida Fraccion paginados", description = "Retorna una pagina de registros del catalogo de subpartida fraccion")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
        content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = PaginaResponse.class))) })
    @GetMapping(value = UrlConst.SUBPARTIDA_FRACCION_LISTAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<PaginaResponse<CatSubpartidaFraccionResponse>> listar(
            @RequestParam(defaultValue = "0") final int pagina,
            @RequestParam(defaultValue = "20") final int tamano,
            @RequestParam(required = false) final String busqueda,
            @RequestParam(required = false) final String sortBy,
            @RequestParam(required = false) final String sortDir) {
        return catSubpartidaFraccionAPIService.listar(pagina, tamano, busqueda, sortBy, sortDir);
    }

    @Operation(operationId = "crear-subpartida-fraccion", summary = "Crea un nuevo registro Subpartida Fraccion")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
        content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CatSubpartidaFraccionResponse.class))) })
    @PostMapping(value = UrlConst.SUBPARTIDA_FRACCION_CREAR, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatSubpartidaFraccionResponse> crear(@Valid @RequestBody final CatSubpartidaFraccionRequest request) {
        return catSubpartidaFraccionAPIService.crear(request);
    }

    @Operation(operationId = "buscar-subpartida-fraccion", summary = "Busca un registro Subpartida Fraccion por id")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
        content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CatSubpartidaFraccionResponse.class))) })
    @GetMapping(value = UrlConst.SUBPARTIDA_FRACCION_BUSCAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatSubpartidaFraccionResponse> findById(
            @Parameter(description = "Clave Subpartida Fraccion") @PathVariable final String cveSubpartidaFraccion,
            @Parameter(description = "Clave Capitulo Fraccion") @PathVariable final String cveCapituloFraccion,
            @Parameter(description = "Clave Partida Fraccion") @PathVariable final String cvePartidaFraccion) {
        return catSubpartidaFraccionAPIService.findById(cveSubpartidaFraccion, cveCapituloFraccion, cvePartidaFraccion);
    }

    @Operation(operationId = "actualizar-subpartida-fraccion", summary = "Actualiza un registro Subpartida Fraccion")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
        content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CatSubpartidaFraccionResponse.class))) })
    @PutMapping(value = UrlConst.SUBPARTIDA_FRACCION_ACTUALIZAR, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatSubpartidaFraccionResponse> actualizar(
            @Parameter(description = "Clave Subpartida Fraccion") @PathVariable final String cveSubpartidaFraccion,
            @Parameter(description = "Clave Capitulo Fraccion") @PathVariable final String cveCapituloFraccion,
            @Parameter(description = "Clave Partida Fraccion") @PathVariable final String cvePartidaFraccion,
            @RequestBody final CatSubpartidaFraccionRequest request) {
        return catSubpartidaFraccionAPIService.actualizar(cveSubpartidaFraccion, cveCapituloFraccion, cvePartidaFraccion, request);
    }
}
