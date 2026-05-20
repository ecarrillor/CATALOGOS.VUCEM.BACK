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
import mx.gob.sat.catalogo.controller.request.suplencia.CatSuplenciaRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.suplencia.CatSuplenciaResponse;
import mx.gob.sat.catalogo.service.api.suplencia.CatSuplenciaAPIService;
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
 * <b>Class:</b> CatSuplenciaController.java <br>
 * <b>Description:</b>
 * <p>Controlador REST para el catalogo de suplencia.
 * Expone endpoints bajo la ruta base {@code api/catalogo-admin/suplencia}.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Controlador
 */
@Tag(name = "Cat. Suplencia",
        description = "Servicios de consulta y mantenimiento del catalogo de suplencia")
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class CatSuplenciaController extends ApiBaseController {

    /** Servicio de negocio para el catalogo de suplencia. */
    private final CatSuplenciaAPIService catSuplenciaAPIService;

    @Operation(operationId = "listar-suplencia", summary = "Lista los registros de suplencia paginados",
        description = "Retorna una pagina de registros de suplencia con soporte de busqueda y ordenamiento.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = PaginaResponse.class)))
    })
    @GetMapping(value = UrlConst.SUPLENCIA_LISTAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<PaginaResponse<CatSuplenciaResponse>> listar(
            @RequestParam(defaultValue = "0") final int pagina,
            @RequestParam(defaultValue = "20") final int tamano,
            @RequestParam(required = false) final String busqueda,
            @RequestParam(required = false) final String sortBy,
            @RequestParam(required = false) final String sortDir) {
        return catSuplenciaAPIService.listar(pagina, tamano, busqueda, sortBy, sortDir);
    }

    @Operation(operationId = "crear-suplencia", summary = "Crea un nuevo registro de suplencia")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatSuplenciaResponse.class)))
    })
    @PostMapping(value = UrlConst.SUPLENCIA_CREAR,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatSuplenciaResponse> crear(@Valid @RequestBody final CatSuplenciaRequest request) {
        return catSuplenciaAPIService.crear(request);
    }

    @Operation(operationId = "buscar-suplencia", summary = "Busca un registro de suplencia por identificador")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatSuplenciaResponse.class)))
    })
    @GetMapping(value = UrlConst.SUPLENCIA_BUSCAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatSuplenciaResponse> findById(
            @Parameter(description = "Identificador de suplencia") @PathVariable final Short idSuplencia) {
        return catSuplenciaAPIService.findById(idSuplencia);
    }

    @Operation(operationId = "actualizar-suplencia", summary = "Actualiza un registro de suplencia")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatSuplenciaResponse.class)))
    })
    @PutMapping(value = UrlConst.SUPLENCIA_ACTUALIZAR,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatSuplenciaResponse> actualizar(
            @Parameter(description = "Identificador de suplencia") @PathVariable final Short idSuplencia,
            @RequestBody final CatSuplenciaRequest request) {
        return catSuplenciaAPIService.actualizar(idSuplencia, request);
    }
}
