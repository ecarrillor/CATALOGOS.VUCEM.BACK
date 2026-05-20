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
import mx.gob.sat.catalogo.controller.request.restriccionttra.CatRestriccionTtraRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.restriccionttra.CatRestriccionTtraResponse;
import mx.gob.sat.catalogo.service.api.restriccionttra.CatRestriccionTtraAPIService;
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

@Tag(name = "Cat. Restriccion Ttra", description = "...")
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class CatRestriccionTtraController extends ApiBaseController {

    private final CatRestriccionTtraAPIService catRestriccionTtraAPIService;

    @Operation(operationId = "listar-restriccion-ttra", summary = "Lista los registros Restriccion Ttra paginados", description = "...")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = PaginaResponse.class)))
    })
    @GetMapping(value = UrlConst.RESTRICCION_TTRA_LISTAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<PaginaResponse<CatRestriccionTtraResponse>> listar(
            @RequestParam(defaultValue = "0") final int pagina,
            @RequestParam(defaultValue = "20") final int tamano,
            @RequestParam(required = false) final String busqueda,
            @RequestParam(required = false) final String sortBy,
            @RequestParam(required = false) final String sortDir) {
        return catRestriccionTtraAPIService.listar(pagina, tamano, busqueda, sortBy, sortDir);
    }

    @Operation(operationId = "crear-restriccion-ttra", summary = "Crea un nuevo registro Restriccion Ttra")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatRestriccionTtraResponse.class)))
    })
    @PostMapping(value = UrlConst.RESTRICCION_TTRA_CREAR, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatRestriccionTtraResponse> crear(@Valid @RequestBody final CatRestriccionTtraRequest request) {
        return catRestriccionTtraAPIService.crear(request);
    }

    @Operation(operationId = "buscar-restriccion-ttra", summary = "Busca un registro Restriccion Ttra por id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatRestriccionTtraResponse.class)))
    })
    @GetMapping(value = UrlConst.RESTRICCION_TTRA_BUSCAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatRestriccionTtraResponse> findById(
            @Parameter(description = "Id Restriccion Ttra") @PathVariable final Short idRestriccionTtra) {
        return catRestriccionTtraAPIService.findById(idRestriccionTtra);
    }

    @Operation(operationId = "actualizar-restriccion-ttra", summary = "Actualiza un registro Restriccion Ttra")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatRestriccionTtraResponse.class)))
    })
    @PutMapping(value = UrlConst.RESTRICCION_TTRA_ACTUALIZAR, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatRestriccionTtraResponse> actualizar(
            @Parameter(description = "Id Restriccion Ttra") @PathVariable final Short idRestriccionTtra,
            @RequestBody final CatRestriccionTtraRequest request) {
        return catRestriccionTtraAPIService.actualizar(idRestriccionTtra, request);
    }
}
