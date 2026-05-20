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
import mx.gob.sat.catalogo.controller.request.colonia.CatColoniaRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.colonia.CatColoniaResponse;
import mx.gob.sat.catalogo.service.api.colonia.CatColoniaAPIService;
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
 * <b>Class:</b> CatColoniaController.java <br>
 * <b>Description:</b>
 * <p>Controlador REST para el catalogo de colonia.
 * Expone endpoints bajo la ruta base {@code api/catalogo-admin/colonia}.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Controlador
 */
@Tag(name = "Cat. Colonia",
        description = "Servicios de consulta y mantenimiento del catalogo de colonia")
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class CatColoniaController extends ApiBaseController {

    /** Servicio de negocio para el catalogo de colonia. */
    private final CatColoniaAPIService catColoniaAPIService;

    @Operation(operationId = "listar-colonia", summary = "Lista los registros de colonia paginados",
        description = "Retorna una pagina de registros de colonia con soporte de busqueda y ordenamiento.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = PaginaResponse.class)))
    })
    @GetMapping(value = UrlConst.COLONIA_LISTAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<PaginaResponse<CatColoniaResponse>> listar(
            @RequestParam(defaultValue = "0") final int pagina,
            @RequestParam(defaultValue = "20") final int tamano,
            @RequestParam(required = false) final String busqueda,
            @RequestParam(required = false) final String sortBy,
            @RequestParam(required = false) final String sortDir) {
        return catColoniaAPIService.listar(pagina, tamano, busqueda, sortBy, sortDir);
    }

    @Operation(operationId = "crear-colonia", summary = "Crea un nuevo registro de colonia")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatColoniaResponse.class)))
    })
    @PostMapping(value = UrlConst.COLONIA_CREAR,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatColoniaResponse> crear(@Valid @RequestBody final CatColoniaRequest request) {
        return catColoniaAPIService.crear(request);
    }

    @Operation(operationId = "buscar-colonia", summary = "Busca un registro de colonia por clave")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatColoniaResponse.class)))
    })
    @GetMapping(value = UrlConst.COLONIA_BUSCAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatColoniaResponse> findById(
            @Parameter(description = "Clave de colonia") @PathVariable final String cveColonia) {
        return catColoniaAPIService.findById(cveColonia);
    }

    @Operation(operationId = "actualizar-colonia", summary = "Actualiza un registro de colonia")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatColoniaResponse.class)))
    })
    @PutMapping(value = UrlConst.COLONIA_ACTUALIZAR,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatColoniaResponse> actualizar(
            @Parameter(description = "Clave de colonia") @PathVariable final String cveColonia,
            @RequestBody final CatColoniaRequest request) {
        return catColoniaAPIService.actualizar(cveColonia, request);
    }
}
