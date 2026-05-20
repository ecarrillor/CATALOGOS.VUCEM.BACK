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
import mx.gob.sat.catalogo.controller.request.descripcionprod.CatDescripcionProdRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.descripcionprod.CatDescripcionProdResponse;
import mx.gob.sat.catalogo.service.api.descripcionprod.CatDescripcionProdAPIService;
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

@Tag(name = "Cat. Descripcion Prod", description = "...")
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class CatDescripcionProdController extends ApiBaseController {

    private final CatDescripcionProdAPIService catDescripcionProdAPIService;

    @Operation(operationId = "listar-descripcion-prod", summary = "Lista los registros Descripcion Prod paginados", description = "...")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = PaginaResponse.class)))
    })
    @GetMapping(value = UrlConst.DESCRIPCION_PROD_LISTAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<PaginaResponse<CatDescripcionProdResponse>> listar(
            @RequestParam(defaultValue = "0") final int pagina,
            @RequestParam(defaultValue = "20") final int tamano,
            @RequestParam(required = false) final String busqueda,
            @RequestParam(required = false) final String sortBy,
            @RequestParam(required = false) final String sortDir) {
        return catDescripcionProdAPIService.listar(pagina, tamano, busqueda, sortBy, sortDir);
    }

    @Operation(operationId = "crear-descripcion-prod", summary = "Crea un nuevo registro Descripcion Prod")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatDescripcionProdResponse.class)))
    })
    @PostMapping(value = UrlConst.DESCRIPCION_PROD_CREAR, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatDescripcionProdResponse> crear(@Valid @RequestBody final CatDescripcionProdRequest request) {
        return catDescripcionProdAPIService.crear(request);
    }

    @Operation(operationId = "buscar-descripcion-prod", summary = "Busca un registro Descripcion Prod por id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatDescripcionProdResponse.class)))
    })
    @GetMapping(value = UrlConst.DESCRIPCION_PROD_BUSCAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatDescripcionProdResponse> findById(
            @Parameter(description = "Id Descripcion Prod") @PathVariable final Integer idDescripcionProd) {
        return catDescripcionProdAPIService.findById(idDescripcionProd);
    }

    @Operation(operationId = "actualizar-descripcion-prod", summary = "Actualiza un registro Descripcion Prod")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatDescripcionProdResponse.class)))
    })
    @PutMapping(value = UrlConst.DESCRIPCION_PROD_ACTUALIZAR, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatDescripcionProdResponse> actualizar(
            @Parameter(description = "Id Descripcion Prod") @PathVariable final Integer idDescripcionProd,
            @RequestBody final CatDescripcionProdRequest request) {
        return catDescripcionProdAPIService.actualizar(idDescripcionProd, request);
    }
}
