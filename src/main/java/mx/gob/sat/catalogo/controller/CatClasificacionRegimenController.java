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
import mx.gob.sat.catalogo.controller.request.clasificacionregimen.CatClasificacionRegimenRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.clasificacionregimen.CatClasificacionRegimenResponse;
import mx.gob.sat.catalogo.service.api.clasificacionregimen.CatClasificacionRegimenAPIService;
import mx.gob.sat.catalogo.util.consts.UrlConst;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador para el catalogo de clasificacion de regimen.
 *
 * @author Javier Chávez Barrios
 * @since 18 de mayo del 2026
 */
@Tag(name = "Cat. Clasificacion Regimen", description = "Operaciones sobre el catalogo de clasificacion de regimen")
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class CatClasificacionRegimenController extends ApiBaseController {

    private final CatClasificacionRegimenAPIService catClasificacionRegimenAPIService;

    @Operation(operationId = "listar-clasificacion-regimen", summary = "Lista los registros Clasificacion Regimen paginados", description = "Retorna una pagina de registros del catalogo de clasificacion de regimen")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
        content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = PaginaResponse.class))) })
    @GetMapping(value = UrlConst.CLASIFICACION_REGIMEN_LISTAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<PaginaResponse<CatClasificacionRegimenResponse>> listar(
            @RequestParam(defaultValue = "0") final int pagina,
            @RequestParam(defaultValue = "20") final int tamano,
            @RequestParam(required = false) final String busqueda,
            @RequestParam(required = false) final String sortBy,
            @RequestParam(required = false) final String sortDir) {
        return catClasificacionRegimenAPIService.listar(pagina, tamano, busqueda, sortBy, sortDir);
    }

    @Operation(operationId = "crear-clasificacion-regimen", summary = "Crea un nuevo registro Clasificacion Regimen")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
        content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CatClasificacionRegimenResponse.class))) })
    @PostMapping(value = UrlConst.CLASIFICACION_REGIMEN_CREAR, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatClasificacionRegimenResponse> crear(@Valid @RequestBody final CatClasificacionRegimenRequest request) {
        return catClasificacionRegimenAPIService.crear(request);
    }

    @Operation(operationId = "buscar-clasificacion-regimen", summary = "Busca un registro Clasificacion Regimen por id")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
        content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CatClasificacionRegimenResponse.class))) })
    @GetMapping(value = UrlConst.CLASIFICACION_REGIMEN_BUSCAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatClasificacionRegimenResponse> findById(
            @Parameter(description = "Clave Clasificacion Regimen") @PathVariable final String cveClasificacionRegimen,
            @Parameter(description = "Clave Regimen") @PathVariable final String cveRegimen) {
        return catClasificacionRegimenAPIService.findById(cveClasificacionRegimen, cveRegimen);
    }

    @Operation(operationId = "actualizar-clasificacion-regimen", summary = "Actualiza un registro Clasificacion Regimen")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
        content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CatClasificacionRegimenResponse.class))) })
    @PutMapping(value = UrlConst.CLASIFICACION_REGIMEN_ACTUALIZAR, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatClasificacionRegimenResponse> actualizar(
            @Parameter(description = "Clave Clasificacion Regimen") @PathVariable final String cveClasificacionRegimen,
            @Parameter(description = "Clave Regimen") @PathVariable final String cveRegimen,
            @RequestBody final CatClasificacionRegimenRequest request) {
        return catClasificacionRegimenAPIService.actualizar(cveClasificacionRegimen, cveRegimen, request);
    }
}
