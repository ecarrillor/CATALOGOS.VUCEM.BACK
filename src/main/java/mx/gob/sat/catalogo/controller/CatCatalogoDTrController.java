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
import mx.gob.sat.catalogo.controller.request.catalogodtr.CatCatalogoDTrRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.catalogodtr.CatCatalogoDTrResponse;
import mx.gob.sat.catalogo.service.api.catalogodtr.CatCatalogoDTrAPIService;
import mx.gob.sat.catalogo.util.consts.UrlConst;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Cat. Catalogo D Tr", description = "...")
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class CatCatalogoDTrController extends ApiBaseController {

    private final CatCatalogoDTrAPIService catCatalogoDTrAPIService;

    @Operation(operationId = "listar-catalogo-d-tr", summary = "Lista los registros Catalogo D Tr paginados", description = "...")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
        content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = PaginaResponse.class))) })
    @GetMapping(value = UrlConst.CATALOGO_D_TR_LISTAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<PaginaResponse<CatCatalogoDTrResponse>> listar(
            @RequestParam(defaultValue = "0") final int pagina,
            @RequestParam(defaultValue = "20") final int tamano,
            @RequestParam(required = false) final String busqueda,
            @RequestParam(required = false) final String sortBy,
            @RequestParam(required = false) final String sortDir) {
        return catCatalogoDTrAPIService.listar(pagina, tamano, busqueda, sortBy, sortDir);
    }

    @Operation(operationId = "crear-catalogo-d-tr", summary = "Crea un nuevo registro Catalogo D Tr")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
        content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CatCatalogoDTrResponse.class))) })
    @PostMapping(value = UrlConst.CATALOGO_D_TR_CREAR, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatCatalogoDTrResponse> crear(@Valid @RequestBody final CatCatalogoDTrRequest request) {
        return catCatalogoDTrAPIService.crear(request);
    }

    @Operation(operationId = "buscar-catalogo-d-tr", summary = "Busca un registro Catalogo D Tr por id")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
        content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CatCatalogoDTrResponse.class))) })
    @GetMapping(value = UrlConst.CATALOGO_D_TR_BUSCAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatCatalogoDTrResponse> findById(
            @Parameter(description = "Cve Catalogo") @PathVariable final String cveCatalogo,
            @Parameter(description = "Cve Lenguaje") @PathVariable final String cveLenguaje) {
        return catCatalogoDTrAPIService.findById(cveCatalogo, cveLenguaje);
    }

    @Operation(operationId = "actualizar-catalogo-d-tr", summary = "Actualiza un registro Catalogo D Tr")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
        content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CatCatalogoDTrResponse.class))) })
    @PutMapping(value = UrlConst.CATALOGO_D_TR_ACTUALIZAR, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatCatalogoDTrResponse> actualizar(
            @Parameter(description = "Cve Catalogo") @PathVariable final String cveCatalogo,
            @Parameter(description = "Cve Lenguaje") @PathVariable final String cveLenguaje,
            @RequestBody final CatCatalogoDTrRequest request) {
        return catCatalogoDTrAPIService.actualizar(cveCatalogo, cveLenguaje, request);
    }
}
