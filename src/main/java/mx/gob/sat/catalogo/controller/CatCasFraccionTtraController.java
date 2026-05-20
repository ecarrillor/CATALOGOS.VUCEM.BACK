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
import mx.gob.sat.catalogo.controller.request.casfraccionttra.CatCasFraccionTtraRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.casfraccionttra.CatCasFraccionTtraResponse;
import mx.gob.sat.catalogo.service.api.casfraccionttra.CatCasFraccionTtraAPIService;
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

@Tag(name = "Cat. Cas Fraccion Ttra", description = "...")
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class CatCasFraccionTtraController extends ApiBaseController {

    private final CatCasFraccionTtraAPIService catCasFraccionTtraAPIService;

    @Operation(operationId = "listar-cas-fraccion-ttra", summary = "Lista los registros Cas Fraccion Ttra paginados")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = PaginaResponse.class)))
    })
    @GetMapping(value = UrlConst.CAS_FRACCION_TTRA_LISTAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<PaginaResponse<CatCasFraccionTtraResponse>> listar(
            @RequestParam(defaultValue = "0") final int pagina,
            @RequestParam(defaultValue = "20") final int tamano,
            @RequestParam(required = false) final String busqueda,
            @RequestParam(required = false) final String sortBy,
            @RequestParam(required = false) final String sortDir) {
        return catCasFraccionTtraAPIService.listar(pagina, tamano, busqueda, sortBy, sortDir);
    }

    @Operation(operationId = "crear-cas-fraccion-ttra", summary = "Crea un nuevo registro Cas Fraccion Ttra")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatCasFraccionTtraResponse.class)))
    })
    @PostMapping(value = UrlConst.CAS_FRACCION_TTRA_CREAR, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatCasFraccionTtraResponse> crear(@Valid @RequestBody final CatCasFraccionTtraRequest request) {
        return catCasFraccionTtraAPIService.crear(request);
    }

    @Operation(operationId = "buscar-cas-fraccion-ttra", summary = "Busca un registro Cas Fraccion Ttra por id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatCasFraccionTtraResponse.class)))
    })
    @GetMapping(value = UrlConst.CAS_FRACCION_TTRA_BUSCAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatCasFraccionTtraResponse> findById(
            @Parameter(description = "Id Cas Fraccion Ttra") @PathVariable final Short idCasFraccionTtra) {
        return catCasFraccionTtraAPIService.findById(idCasFraccionTtra);
    }

    @Operation(operationId = "actualizar-cas-fraccion-ttra", summary = "Actualiza un registro Cas Fraccion Ttra")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatCasFraccionTtraResponse.class)))
    })
    @PutMapping(value = UrlConst.CAS_FRACCION_TTRA_ACTUALIZAR, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatCasFraccionTtraResponse> actualizar(
            @Parameter(description = "Id Cas Fraccion Ttra") @PathVariable final Short idCasFraccionTtra,
            @RequestBody final CatCasFraccionTtraRequest request) {
        return catCasFraccionTtraAPIService.actualizar(idCasFraccionTtra, request);
    }
}
