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
import mx.gob.sat.catalogo.controller.request.catalogod.CatCatalogoDRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.catalogod.CatCatalogoDResponse;
import mx.gob.sat.catalogo.service.api.catalogod.CatCatalogoDAPIService;
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

/**
 * <b>Class:</b> CatCatalogoDController.java <br>
 * <b>Description:</b>
 * <p>Controlador REST para el catalogo Catalogo D.
 * Expone endpoints bajo la ruta base {@code api/catalogo-admin/catalogo-d}.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Controlador
 */
@Tag(name = "Cat. Catalogo D",
        description = "Servicios de consulta y mantenimiento del catalogo Catalogo D")
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class CatCatalogoDController extends ApiBaseController {

    /** Servicio de negocio para el catalogo Catalogo D. */
    private final CatCatalogoDAPIService catCatalogoDAPIService;

    @Operation(operationId = "listar-catalogo-d", summary = "Lista los registros Catalogo D paginados",
        description = "Retorna una pagina de registros Catalogo D con soporte de busqueda y ordenamiento.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = PaginaResponse.class)))
    })
    @GetMapping(value = UrlConst.CATALOGO_D_LISTAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<PaginaResponse<CatCatalogoDResponse>> listar(
            @RequestParam(defaultValue = "0") final int pagina,
            @RequestParam(defaultValue = "20") final int tamano,
            @RequestParam(required = false) final String busqueda,
            @RequestParam(required = false) final String sortBy,
            @RequestParam(required = false) final String sortDir) {
        return catCatalogoDAPIService.listar(pagina, tamano, busqueda, sortBy, sortDir);
    }

    @Operation(operationId = "crear-catalogo-d", summary = "Crea un nuevo registro Catalogo D")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatCatalogoDResponse.class)))
    })
    @PostMapping(value = UrlConst.CATALOGO_D_CREAR,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatCatalogoDResponse> crear(@Valid @RequestBody final CatCatalogoDRequest request) {
        return catCatalogoDAPIService.crear(request);
    }

    @Operation(operationId = "buscar-catalogo-d", summary = "Busca un registro Catalogo D por clave")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatCatalogoDResponse.class)))
    })
    @GetMapping(value = UrlConst.CATALOGO_D_BUSCAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatCatalogoDResponse> findById(
            @Parameter(description = "Clave Catalogo D") @PathVariable final String cveCatalogo) {
        return catCatalogoDAPIService.findById(cveCatalogo);
    }

    @Operation(operationId = "actualizar-catalogo-d", summary = "Actualiza un registro Catalogo D")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatCatalogoDResponse.class)))
    })
    @PutMapping(value = UrlConst.CATALOGO_D_ACTUALIZAR,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatCatalogoDResponse> actualizar(
            @Parameter(description = "Clave Catalogo D") @PathVariable final String cveCatalogo,
            @RequestBody final CatCatalogoDRequest request) {
        return catCatalogoDAPIService.actualizar(cveCatalogo, request);
    }
}
