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
import mx.gob.sat.catalogo.controller.request.scian.CatScianRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.scian.CatScianResponse;
import mx.gob.sat.catalogo.service.api.scian.CatScianAPIService;
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
 * <b>Class:</b> CatScianController.java <br>
 * <b>Description:</b>
 * <p>Controlador REST para el catalogo SCIAN.
 * Expone endpoints bajo la ruta base {@code api/catalogo-admin/scian}.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Controlador
 */
@Tag(name = "Cat. SCIAN",
        description = "Servicios de consulta y mantenimiento del catalogo SCIAN")
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class CatScianController extends ApiBaseController {

    private final CatScianAPIService catScianAPIService;

    @Operation(operationId = "listar-scian", summary = "Lista los registros SCIAN paginados",
        description = "Retorna una pagina de registros SCIAN con soporte de busqueda y ordenamiento.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = PaginaResponse.class)))
    })
    @GetMapping(value = UrlConst.SCIAN_LISTAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<PaginaResponse<CatScianResponse>> listar(
            @RequestParam(defaultValue = "0") final int pagina,
            @RequestParam(defaultValue = "20") final int tamano,
            @RequestParam(required = false) final String busqueda,
            @RequestParam(required = false) final String sortBy,
            @RequestParam(required = false) final String sortDir) {
        return catScianAPIService.listar(pagina, tamano, busqueda, sortBy, sortDir);
    }

    @Operation(operationId = "crear-scian", summary = "Crea un nuevo registro SCIAN")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatScianResponse.class)))
    })
    @PostMapping(value = UrlConst.SCIAN_CREAR,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatScianResponse> crear(@Valid @RequestBody final CatScianRequest request) {
        return catScianAPIService.crear(request);
    }

    @Operation(operationId = "buscar-scian", summary = "Busca un registro SCIAN por clave")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatScianResponse.class)))
    })
    @GetMapping(value = UrlConst.SCIAN_BUSCAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatScianResponse> findById(
            @Parameter(description = "Clave SCIAN") @PathVariable final String cveScian) {
        return catScianAPIService.findById(cveScian);
    }

    @Operation(operationId = "actualizar-scian", summary = "Actualiza un registro SCIAN")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatScianResponse.class)))
    })
    @PutMapping(value = UrlConst.SCIAN_ACTUALIZAR,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatScianResponse> actualizar(
            @Parameter(description = "Clave SCIAN") @PathVariable final String cveScian,
            @RequestBody final CatScianRequest request) {
        return catScianAPIService.actualizar(cveScian, request);
    }
}
