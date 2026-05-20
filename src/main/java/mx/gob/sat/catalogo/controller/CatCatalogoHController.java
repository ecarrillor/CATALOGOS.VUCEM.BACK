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
import mx.gob.sat.catalogo.controller.request.catalogoh.CatCatalogoHRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.catalogoh.CatCatalogoHResponse;
import mx.gob.sat.catalogo.service.api.catalogoh.CatCatalogoHAPIService;
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
 * <b>Class:</b> CatCatalogoHController.java <br>
 * <b>Description:</b>
 * <p>Controlador REST para el catalogo Catalogo H.
 * Expone endpoints bajo la ruta base {@code api/catalogo-admin/catalogo-h}.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Controlador
 */
@Tag(name = "Cat. Catalogo H",
        description = "Servicios de consulta y mantenimiento del catalogo Catalogo H")
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class CatCatalogoHController extends ApiBaseController {

    private final CatCatalogoHAPIService catCatalogoHAPIService;

    @Operation(operationId = "listar-catalogo-h", summary = "Lista los registros Catalogo H paginados",
        description = "Retorna una pagina de registros Catalogo H con soporte de busqueda y ordenamiento.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = PaginaResponse.class)))
    })
    @GetMapping(value = UrlConst.CATALOGO_H_LISTAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<PaginaResponse<CatCatalogoHResponse>> listar(
            @RequestParam(defaultValue = "0") final int pagina,
            @RequestParam(defaultValue = "20") final int tamano,
            @RequestParam(required = false) final String busqueda,
            @RequestParam(required = false) final String sortBy,
            @RequestParam(required = false) final String sortDir) {
        return catCatalogoHAPIService.listar(pagina, tamano, busqueda, sortBy, sortDir);
    }

    @Operation(operationId = "crear-catalogo-h", summary = "Crea un nuevo registro Catalogo H")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatCatalogoHResponse.class)))
    })
    @PostMapping(value = UrlConst.CATALOGO_H_CREAR,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatCatalogoHResponse> crear(@Valid @RequestBody final CatCatalogoHRequest request) {
        return catCatalogoHAPIService.crear(request);
    }

    @Operation(operationId = "buscar-catalogo-h", summary = "Busca un registro Catalogo H por clave")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatCatalogoHResponse.class)))
    })
    @GetMapping(value = UrlConst.CATALOGO_H_BUSCAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatCatalogoHResponse> findById(
            @Parameter(description = "Clave Catalogo H") @PathVariable final String cveCatalogoH) {
        return catCatalogoHAPIService.findById(cveCatalogoH);
    }

    @Operation(operationId = "actualizar-catalogo-h", summary = "Actualiza un registro Catalogo H")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatCatalogoHResponse.class)))
    })
    @PutMapping(value = UrlConst.CATALOGO_H_ACTUALIZAR,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatCatalogoHResponse> actualizar(
            @Parameter(description = "Clave Catalogo H") @PathVariable final String cveCatalogoH,
            @RequestBody final CatCatalogoHRequest request) {
        return catCatalogoHAPIService.actualizar(cveCatalogoH, request);
    }
}
