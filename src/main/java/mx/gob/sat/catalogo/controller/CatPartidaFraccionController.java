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
import mx.gob.sat.catalogo.controller.request.partidafraccion.CatPartidaFraccionRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.partidafraccion.CatPartidaFraccionResponse;
import mx.gob.sat.catalogo.service.api.partidafraccion.CatPartidaFraccionAPIService;
import mx.gob.sat.catalogo.util.consts.UrlConst;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador para el catalogo de partida fraccion.
 *
 * @author Javier Chávez Barrios
 * @since 18 de mayo del 2026
 */
@Tag(name = "Cat. Partida Fraccion", description = "Operaciones sobre el catalogo de partida fraccion")
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class CatPartidaFraccionController extends ApiBaseController {

    private final CatPartidaFraccionAPIService catPartidaFraccionAPIService;

    @Operation(operationId = "listar-partida-fraccion", summary = "Lista los registros Partida Fraccion paginados", description = "Retorna una pagina de registros del catalogo de partida fraccion")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
        content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = PaginaResponse.class))) })
    @GetMapping(value = UrlConst.PARTIDA_FRACCION_LISTAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<PaginaResponse<CatPartidaFraccionResponse>> listar(
            @RequestParam(defaultValue = "0") final int pagina,
            @RequestParam(defaultValue = "20") final int tamano,
            @RequestParam(required = false) final String busqueda,
            @RequestParam(required = false) final String sortBy,
            @RequestParam(required = false) final String sortDir) {
        return catPartidaFraccionAPIService.listar(pagina, tamano, busqueda, sortBy, sortDir);
    }

    @Operation(operationId = "crear-partida-fraccion", summary = "Crea un nuevo registro Partida Fraccion")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
        content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CatPartidaFraccionResponse.class))) })
    @PostMapping(value = UrlConst.PARTIDA_FRACCION_CREAR, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatPartidaFraccionResponse> crear(@Valid @RequestBody final CatPartidaFraccionRequest request) {
        return catPartidaFraccionAPIService.crear(request);
    }

    @Operation(operationId = "buscar-partida-fraccion", summary = "Busca un registro Partida Fraccion por id")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
        content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CatPartidaFraccionResponse.class))) })
    @GetMapping(value = UrlConst.PARTIDA_FRACCION_BUSCAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatPartidaFraccionResponse> findById(
            @Parameter(description = "Clave Capitulo Fraccion") @PathVariable final String cveCapituloFraccion,
            @Parameter(description = "Clave Partida Fraccion") @PathVariable final String cvePartidaFraccion) {
        return catPartidaFraccionAPIService.findById(cveCapituloFraccion, cvePartidaFraccion);
    }

    @Operation(operationId = "actualizar-partida-fraccion", summary = "Actualiza un registro Partida Fraccion")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
        content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CatPartidaFraccionResponse.class))) })
    @PutMapping(value = UrlConst.PARTIDA_FRACCION_ACTUALIZAR, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatPartidaFraccionResponse> actualizar(
            @Parameter(description = "Clave Capitulo Fraccion") @PathVariable final String cveCapituloFraccion,
            @Parameter(description = "Clave Partida Fraccion") @PathVariable final String cvePartidaFraccion,
            @RequestBody final CatPartidaFraccionRequest request) {
        return catPartidaFraccionAPIService.actualizar(cveCapituloFraccion, cvePartidaFraccion, request);
    }
}
