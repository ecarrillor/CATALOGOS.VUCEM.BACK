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
import mx.gob.sat.catalogo.controller.request.unidadadminaduana.CatUnidadAdminAduanaRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.unidadadminaduana.CatUnidadAdminAduanaResponse;
import mx.gob.sat.catalogo.service.api.unidadadminaduana.CatUnidadAdminAduanaAPIService;
import mx.gob.sat.catalogo.util.consts.UrlConst;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <b>Class:</b> CatUnidadAdminAduanaController.java <br>
 * <b>Description:</b>
 * <p>Controlador REST para el catalogo de unidades administrativas de aduana.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Controller
 */
@Tag(name = "Cat. Unidad Admin Aduana", description = "Operaciones sobre el catalogo de unidades administrativas de aduana")
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class CatUnidadAdminAduanaController extends ApiBaseController {

    private final CatUnidadAdminAduanaAPIService catUnidadAdminAduanaAPIService;

    @Operation(operationId = "listar-unidad-admin-aduana", summary = "Lista los registros Unidad Admin Aduana paginados", description = "Retorna una pagina de registros del catalogo de unidades administrativas de aduana")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
        content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = PaginaResponse.class))) })
    @GetMapping(value = UrlConst.UNIDAD_ADMIN_ADUANA_LISTAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<PaginaResponse<CatUnidadAdminAduanaResponse>> listar(
            @RequestParam(defaultValue = "0") final int pagina,
            @RequestParam(defaultValue = "20") final int tamano,
            @RequestParam(required = false) final String busqueda,
            @RequestParam(required = false) final String sortBy,
            @RequestParam(required = false) final String sortDir) {
        return catUnidadAdminAduanaAPIService.listar(pagina, tamano, busqueda, sortBy, sortDir);
    }

    @Operation(operationId = "crear-unidad-admin-aduana", summary = "Crea un nuevo registro Unidad Admin Aduana")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
        content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CatUnidadAdminAduanaResponse.class))) })
    @PostMapping(value = UrlConst.UNIDAD_ADMIN_ADUANA_CREAR, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatUnidadAdminAduanaResponse> crear(@Valid @RequestBody final CatUnidadAdminAduanaRequest request) {
        return catUnidadAdminAduanaAPIService.crear(request);
    }

    @Operation(operationId = "buscar-unidad-admin-aduana", summary = "Busca un registro Unidad Admin Aduana por id")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
        content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CatUnidadAdminAduanaResponse.class))) })
    @GetMapping(value = UrlConst.UNIDAD_ADMIN_ADUANA_BUSCAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatUnidadAdminAduanaResponse> findById(
            @Parameter(description = "Clave Unidad Administrativa") @PathVariable final String cveUnidadAdministrativa,
            @Parameter(description = "Clave Aduana") @PathVariable final String cveAduana) {
        return catUnidadAdminAduanaAPIService.findById(cveUnidadAdministrativa, cveAduana);
    }

    @Operation(operationId = "actualizar-unidad-admin-aduana", summary = "Actualiza un registro Unidad Admin Aduana")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
        content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CatUnidadAdminAduanaResponse.class))) })
    @PutMapping(value = UrlConst.UNIDAD_ADMIN_ADUANA_ACTUALIZAR, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatUnidadAdminAduanaResponse> actualizar(
            @Parameter(description = "Clave Unidad Administrativa") @PathVariable final String cveUnidadAdministrativa,
            @Parameter(description = "Clave Aduana") @PathVariable final String cveAduana,
            @RequestBody final CatUnidadAdminAduanaRequest request) {
        return catUnidadAdminAduanaAPIService.actualizar(cveUnidadAdministrativa, cveAduana, request);
    }
}
