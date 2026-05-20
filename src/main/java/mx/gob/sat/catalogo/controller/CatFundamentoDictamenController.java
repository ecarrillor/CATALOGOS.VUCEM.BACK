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
import mx.gob.sat.catalogo.controller.request.fundamentodictamen.CatFundamentoDictamenRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.fundamentodictamen.CatFundamentoDictamenResponse;
import mx.gob.sat.catalogo.service.api.fundamentodictamen.CatFundamentoDictamenAPIService;
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
 * <b>Class:</b> CatFundamentoDictamenController.java <br>
 * <b>Description:</b>
 * <p>Controlador REST para el catalogo Fundamento Dictamen.
 * Expone endpoints bajo la ruta base {@code api/catalogo-admin/fundamento-dictamen}.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Controlador
 */
@Tag(name = "Cat. Fundamento Dictamen",
        description = "Servicios de consulta y mantenimiento del catalogo Fundamento Dictamen")
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class CatFundamentoDictamenController extends ApiBaseController {

    private final CatFundamentoDictamenAPIService catFundamentoDictamenAPIService;

    @Operation(operationId = "listar-fundamento-dictamen", summary = "Lista los registros Fundamento Dictamen paginados",
        description = "Retorna una pagina de registros Fundamento Dictamen con soporte de busqueda y ordenamiento.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = PaginaResponse.class)))
    })
    @GetMapping(value = UrlConst.FUNDAMENTO_DICTAMEN_LISTAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<PaginaResponse<CatFundamentoDictamenResponse>> listar(
            @RequestParam(defaultValue = "0") final int pagina,
            @RequestParam(defaultValue = "20") final int tamano,
            @RequestParam(required = false) final String busqueda,
            @RequestParam(required = false) final String sortBy,
            @RequestParam(required = false) final String sortDir) {
        return catFundamentoDictamenAPIService.listar(pagina, tamano, busqueda, sortBy, sortDir);
    }

    @Operation(operationId = "crear-fundamento-dictamen", summary = "Crea un nuevo registro Fundamento Dictamen")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatFundamentoDictamenResponse.class)))
    })
    @PostMapping(value = UrlConst.FUNDAMENTO_DICTAMEN_CREAR,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatFundamentoDictamenResponse> crear(@Valid @RequestBody final CatFundamentoDictamenRequest request) {
        return catFundamentoDictamenAPIService.crear(request);
    }

    @Operation(operationId = "buscar-fundamento-dictamen", summary = "Busca un registro Fundamento Dictamen por id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatFundamentoDictamenResponse.class)))
    })
    @GetMapping(value = UrlConst.FUNDAMENTO_DICTAMEN_BUSCAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatFundamentoDictamenResponse> findById(
            @Parameter(description = "Id Fundamento Dictamen") @PathVariable final Long idFundamentoDictamen) {
        return catFundamentoDictamenAPIService.findById(idFundamentoDictamen);
    }

    @Operation(operationId = "actualizar-fundamento-dictamen", summary = "Actualiza un registro Fundamento Dictamen")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatFundamentoDictamenResponse.class)))
    })
    @PutMapping(value = UrlConst.FUNDAMENTO_DICTAMEN_ACTUALIZAR,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatFundamentoDictamenResponse> actualizar(
            @Parameter(description = "Id Fundamento Dictamen") @PathVariable final Long idFundamentoDictamen,
            @RequestBody final CatFundamentoDictamenRequest request) {
        return catFundamentoDictamenAPIService.actualizar(idFundamentoDictamen, request);
    }
}
