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
import mx.gob.sat.catalogo.controller.request.fraccionttra.CatFraccionTtraRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.fraccionttra.CatFraccionTtraResponse;
import mx.gob.sat.catalogo.service.api.fraccionttra.CatFraccionTtraAPIService;
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

@Tag(name = "Cat. Fraccion Ttra", description = "...")
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class CatFraccionTtraController extends ApiBaseController {

    private final CatFraccionTtraAPIService catFraccionTtraAPIService;

    @Operation(operationId = "listar-fraccion-ttra", summary = "Lista los registros Fraccion Ttra paginados")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = PaginaResponse.class)))
    })
    @GetMapping(value = UrlConst.FRACCION_TTRA_LISTAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<PaginaResponse<CatFraccionTtraResponse>> listar(
            @RequestParam(defaultValue = "0") final int pagina,
            @RequestParam(defaultValue = "20") final int tamano,
            @RequestParam(required = false) final String busqueda,
            @RequestParam(required = false) final String sortBy,
            @RequestParam(required = false) final String sortDir) {
        return catFraccionTtraAPIService.listar(pagina, tamano, busqueda, sortBy, sortDir);
    }

    @Operation(operationId = "crear-fraccion-ttra", summary = "Crea un nuevo registro Fraccion Ttra")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatFraccionTtraResponse.class)))
    })
    @PostMapping(value = UrlConst.FRACCION_TTRA_CREAR, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatFraccionTtraResponse> crear(@Valid @RequestBody final CatFraccionTtraRequest request) {
        return catFraccionTtraAPIService.crear(request);
    }

    @Operation(operationId = "buscar-fraccion-ttra", summary = "Busca un registro Fraccion Ttra por id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatFraccionTtraResponse.class)))
    })
    @GetMapping(value = UrlConst.FRACCION_TTRA_BUSCAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatFraccionTtraResponse> findById(
            @Parameter(description = "Id Fraccion Gob") @PathVariable final Long idFraccionGob) {
        return catFraccionTtraAPIService.findById(idFraccionGob);
    }

    @Operation(operationId = "actualizar-fraccion-ttra", summary = "Actualiza un registro Fraccion Ttra")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatFraccionTtraResponse.class)))
    })
    @PutMapping(value = UrlConst.FRACCION_TTRA_ACTUALIZAR, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatFraccionTtraResponse> actualizar(
            @Parameter(description = "Id Fraccion Gob") @PathVariable final Long idFraccionGob,
            @RequestBody final CatFraccionTtraRequest request) {
        return catFraccionTtraAPIService.actualizar(idFraccionGob, request);
    }
}
