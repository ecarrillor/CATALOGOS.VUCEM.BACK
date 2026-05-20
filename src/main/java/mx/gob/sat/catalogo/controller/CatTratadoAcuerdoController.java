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
import mx.gob.sat.catalogo.controller.request.tratadoacuerdo.CatTratadoAcuerdoRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.tratadoacuerdo.CatTratadoAcuerdoResponse;
import mx.gob.sat.catalogo.service.api.tratadoacuerdo.CatTratadoAcuerdoAPIService;
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
 * <b>Class:</b> CatTratadoAcuerdoController.java <br>
 * <b>Description:</b>
 * <p>Controlador REST para el catalogo Tratado Acuerdo.
 * Expone endpoints bajo la ruta base {@code api/catalogo-admin/tratado-acuerdo}.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Controlador
 */
@Tag(name = "Cat. Tratado Acuerdo",
        description = "Servicios de consulta y mantenimiento del catalogo Tratado Acuerdo")
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class CatTratadoAcuerdoController extends ApiBaseController {

    private final CatTratadoAcuerdoAPIService catTratadoAcuerdoAPIService;

    @Operation(operationId = "listar-tratado-acuerdo", summary = "Lista los registros Tratado Acuerdo paginados",
        description = "Retorna una pagina de registros Tratado Acuerdo con soporte de busqueda y ordenamiento.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = PaginaResponse.class)))
    })
    @GetMapping(value = UrlConst.TRATADO_ACUERDO_LISTAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<PaginaResponse<CatTratadoAcuerdoResponse>> listar(
            @RequestParam(defaultValue = "0") final int pagina,
            @RequestParam(defaultValue = "20") final int tamano,
            @RequestParam(required = false) final String busqueda,
            @RequestParam(required = false) final String sortBy,
            @RequestParam(required = false) final String sortDir) {
        return catTratadoAcuerdoAPIService.listar(pagina, tamano, busqueda, sortBy, sortDir);
    }

    @Operation(operationId = "crear-tratado-acuerdo", summary = "Crea un nuevo registro Tratado Acuerdo")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatTratadoAcuerdoResponse.class)))
    })
    @PostMapping(value = UrlConst.TRATADO_ACUERDO_CREAR,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatTratadoAcuerdoResponse> crear(@Valid @RequestBody final CatTratadoAcuerdoRequest request) {
        return catTratadoAcuerdoAPIService.crear(request);
    }

    @Operation(operationId = "buscar-tratado-acuerdo", summary = "Busca un registro Tratado Acuerdo por id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatTratadoAcuerdoResponse.class)))
    })
    @GetMapping(value = UrlConst.TRATADO_ACUERDO_BUSCAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatTratadoAcuerdoResponse> findById(
            @Parameter(description = "Id Tratado Acuerdo") @PathVariable final Short idTratadoAcuerdo) {
        return catTratadoAcuerdoAPIService.findById(idTratadoAcuerdo);
    }

    @Operation(operationId = "actualizar-tratado-acuerdo", summary = "Actualiza un registro Tratado Acuerdo")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatTratadoAcuerdoResponse.class)))
    })
    @PutMapping(value = UrlConst.TRATADO_ACUERDO_ACTUALIZAR,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatTratadoAcuerdoResponse> actualizar(
            @Parameter(description = "Id Tratado Acuerdo") @PathVariable final Short idTratadoAcuerdo,
            @RequestBody final CatTratadoAcuerdoRequest request) {
        return catTratadoAcuerdoAPIService.actualizar(idTratadoAcuerdo, request);
    }
}
