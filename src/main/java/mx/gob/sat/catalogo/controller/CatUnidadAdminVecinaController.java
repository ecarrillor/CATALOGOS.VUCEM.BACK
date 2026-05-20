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
import mx.gob.sat.catalogo.controller.request.unidadadminvecina.CatUnidadAdminVecinaRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.unidadadminvecina.CatUnidadAdminVecinaResponse;
import mx.gob.sat.catalogo.service.api.unidadadminvecina.CatUnidadAdminVecinaAPIService;
import mx.gob.sat.catalogo.util.consts.UrlConst;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <b>Class:</b> CatUnidadAdminVecinaController.java <br>
 * <b>Description:</b>
 * <p>Controlador REST para el catalogo de unidades administrativas vecinas.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Controller
 */
@Tag(name = "Cat. Unidad Admin Vecina", description = "Operaciones sobre el catalogo de unidades administrativas vecinas")
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class CatUnidadAdminVecinaController extends ApiBaseController {

    private final CatUnidadAdminVecinaAPIService catUnidadAdminVecinaAPIService;

    @Operation(operationId = "listar-unidad-admin-vecina", summary = "Lista los registros Unidad Admin Vecina paginados", description = "Retorna una pagina de registros del catalogo de unidades administrativas vecinas")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
        content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = PaginaResponse.class))) })
    @GetMapping(value = UrlConst.UNIDAD_ADMIN_VECINA_LISTAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<PaginaResponse<CatUnidadAdminVecinaResponse>> listar(
            @RequestParam(defaultValue = "0") final int pagina,
            @RequestParam(defaultValue = "20") final int tamano,
            @RequestParam(required = false) final String busqueda,
            @RequestParam(required = false) final String sortBy,
            @RequestParam(required = false) final String sortDir) {
        return catUnidadAdminVecinaAPIService.listar(pagina, tamano, busqueda, sortBy, sortDir);
    }

    @Operation(operationId = "crear-unidad-admin-vecina", summary = "Crea un nuevo registro Unidad Admin Vecina")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
        content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CatUnidadAdminVecinaResponse.class))) })
    @PostMapping(value = UrlConst.UNIDAD_ADMIN_VECINA_CREAR, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatUnidadAdminVecinaResponse> crear(@Valid @RequestBody final CatUnidadAdminVecinaRequest request) {
        return catUnidadAdminVecinaAPIService.crear(request);
    }

    @Operation(operationId = "buscar-unidad-admin-vecina", summary = "Busca un registro Unidad Admin Vecina por id")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
        content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CatUnidadAdminVecinaResponse.class))) })
    @GetMapping(value = UrlConst.UNIDAD_ADMIN_VECINA_BUSCAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatUnidadAdminVecinaResponse> findById(
            @Parameter(description = "Clave Unidad Administrativa") @PathVariable final String cveUnidadAdministrativa,
            @Parameter(description = "Clave Entidad") @PathVariable final String cveEntidad) {
        return catUnidadAdminVecinaAPIService.findById(cveUnidadAdministrativa, cveEntidad);
    }

    @Operation(operationId = "actualizar-unidad-admin-vecina", summary = "Actualiza un registro Unidad Admin Vecina")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
        content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CatUnidadAdminVecinaResponse.class))) })
    @PutMapping(value = UrlConst.UNIDAD_ADMIN_VECINA_ACTUALIZAR, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatUnidadAdminVecinaResponse> actualizar(
            @Parameter(description = "Clave Unidad Administrativa") @PathVariable final String cveUnidadAdministrativa,
            @Parameter(description = "Clave Entidad") @PathVariable final String cveEntidad,
            @RequestBody final CatUnidadAdminVecinaRequest request) {
        return catUnidadAdminVecinaAPIService.actualizar(cveUnidadAdministrativa, cveEntidad, request);
    }
}
