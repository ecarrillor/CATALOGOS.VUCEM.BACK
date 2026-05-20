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
import mx.gob.sat.catalogo.controller.request.restricdescprod.CatRestricDescProdRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.restricdescprod.CatRestricDescProdResponse;
import mx.gob.sat.catalogo.service.api.restricdescprod.CatRestricDescProdAPIService;
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

@Tag(name = "Cat. Restric Desc Prod", description = "...")
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class CatRestricDescProdController extends ApiBaseController {

    private final CatRestricDescProdAPIService catRestricDescProdAPIService;

    @Operation(operationId = "listar-restric-desc-prod", summary = "Lista los registros Restric Desc Prod paginados")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = PaginaResponse.class)))
    })
    @GetMapping(value = UrlConst.RESTRIC_DESC_PROD_LISTAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<PaginaResponse<CatRestricDescProdResponse>> listar(
            @RequestParam(defaultValue = "0") final int pagina,
            @RequestParam(defaultValue = "20") final int tamano,
            @RequestParam(required = false) final String busqueda,
            @RequestParam(required = false) final String sortBy,
            @RequestParam(required = false) final String sortDir) {
        return catRestricDescProdAPIService.listar(pagina, tamano, busqueda, sortBy, sortDir);
    }

    @Operation(operationId = "crear-restric-desc-prod", summary = "Crea un nuevo registro Restric Desc Prod")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatRestricDescProdResponse.class)))
    })
    @PostMapping(value = UrlConst.RESTRIC_DESC_PROD_CREAR, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatRestricDescProdResponse> crear(@Valid @RequestBody final CatRestricDescProdRequest request) {
        return catRestricDescProdAPIService.crear(request);
    }

    @Operation(operationId = "buscar-restric-desc-prod", summary = "Busca un registro Restric Desc Prod por id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatRestricDescProdResponse.class)))
    })
    @GetMapping(value = UrlConst.RESTRIC_DESC_PROD_BUSCAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatRestricDescProdResponse> findById(
            @Parameter(description = "Id Restric Desc Prod") @PathVariable final Long idRestricDescProd) {
        return catRestricDescProdAPIService.findById(idRestricDescProd);
    }

    @Operation(operationId = "actualizar-restric-desc-prod", summary = "Actualiza un registro Restric Desc Prod")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatRestricDescProdResponse.class)))
    })
    @PutMapping(value = UrlConst.RESTRIC_DESC_PROD_ACTUALIZAR, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatRestricDescProdResponse> actualizar(
            @Parameter(description = "Id Restric Desc Prod") @PathVariable final Long idRestricDescProd,
            @RequestBody final CatRestricDescProdRequest request) {
        return catRestricDescProdAPIService.actualizar(idRestricDescProd, request);
    }
}
