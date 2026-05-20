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
import mx.gob.sat.catalogo.controller.request.fraccionttradescrprod.CatFraccionTtraDescProdRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.fraccionttradescrprod.CatFraccionTtraDescProdResponse;
import mx.gob.sat.catalogo.service.api.fraccionttradescrprod.CatFraccionTtraDescProdAPIService;
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

@Tag(name = "Cat. Fraccion Ttra Desc Prod", description = "...")
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class CatFraccionTtraDescProdController extends ApiBaseController {

    private final CatFraccionTtraDescProdAPIService catFraccionTtraDescProdAPIService;

    @Operation(operationId = "listar-fraccion-ttra-desc-prod", summary = "Lista los registros Fraccion Ttra Desc Prod paginados")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = PaginaResponse.class)))
    })
    @GetMapping(value = UrlConst.FRACCION_TTRA_DESC_PROD_LISTAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<PaginaResponse<CatFraccionTtraDescProdResponse>> listar(
            @RequestParam(defaultValue = "0") final int pagina,
            @RequestParam(defaultValue = "20") final int tamano,
            @RequestParam(required = false) final String busqueda,
            @RequestParam(required = false) final String sortBy,
            @RequestParam(required = false) final String sortDir) {
        return catFraccionTtraDescProdAPIService.listar(pagina, tamano, busqueda, sortBy, sortDir);
    }

    @Operation(operationId = "crear-fraccion-ttra-desc-prod", summary = "Crea un nuevo registro Fraccion Ttra Desc Prod")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatFraccionTtraDescProdResponse.class)))
    })
    @PostMapping(value = UrlConst.FRACCION_TTRA_DESC_PROD_CREAR, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatFraccionTtraDescProdResponse> crear(@Valid @RequestBody final CatFraccionTtraDescProdRequest request) {
        return catFraccionTtraDescProdAPIService.crear(request);
    }

    @Operation(operationId = "buscar-fraccion-ttra-desc-prod", summary = "Busca un registro Fraccion Ttra Desc Prod por id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatFraccionTtraDescProdResponse.class)))
    })
    @GetMapping(value = UrlConst.FRACCION_TTRA_DESC_PROD_BUSCAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatFraccionTtraDescProdResponse> findById(
            @Parameter(description = "Id Fraccion Ttra Desc Prod") @PathVariable final Long idFraccionTtraDescProd) {
        return catFraccionTtraDescProdAPIService.findById(idFraccionTtraDescProd);
    }

    @Operation(operationId = "actualizar-fraccion-ttra-desc-prod", summary = "Actualiza un registro Fraccion Ttra Desc Prod")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatFraccionTtraDescProdResponse.class)))
    })
    @PutMapping(value = UrlConst.FRACCION_TTRA_DESC_PROD_ACTUALIZAR, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatFraccionTtraDescProdResponse> actualizar(
            @Parameter(description = "Id Fraccion Ttra Desc Prod") @PathVariable final Long idFraccionTtraDescProd,
            @RequestBody final CatFraccionTtraDescProdRequest request) {
        return catFraccionTtraDescProdAPIService.actualizar(idFraccionTtraDescProd, request);
    }
}
