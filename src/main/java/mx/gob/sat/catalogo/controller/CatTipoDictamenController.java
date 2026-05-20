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
import mx.gob.sat.catalogo.controller.request.tipodictamen.CatTipoDictamenRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.tipodictamen.CatTipoDictamenResponse;
import mx.gob.sat.catalogo.service.api.tipodictamen.CatTipoDictamenAPIService;
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
 * <b>Class:</b> CatTipoDictamenController.java <br>
 * <b>Description:</b>
 * <p>Controlador REST para el catalogo Tipo Dictamen.
 * Expone endpoints bajo la ruta base {@code api/catalogo-admin/tipo-dictamen}.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Controlador
 */
@Tag(name = "Cat. Tipo Dictamen",
        description = "Servicios de consulta y mantenimiento del catalogo Tipo Dictamen")
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class CatTipoDictamenController extends ApiBaseController {

    private final CatTipoDictamenAPIService catTipoDictamenAPIService;

    @Operation(operationId = "listar-tipo-dictamen", summary = "Lista los registros Tipo Dictamen paginados",
        description = "Retorna una pagina de registros Tipo Dictamen con soporte de busqueda y ordenamiento.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = PaginaResponse.class)))
    })
    @GetMapping(value = UrlConst.TIPO_DICTAMEN_LISTAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<PaginaResponse<CatTipoDictamenResponse>> listar(
            @RequestParam(defaultValue = "0") final int pagina,
            @RequestParam(defaultValue = "20") final int tamano,
            @RequestParam(required = false) final String busqueda,
            @RequestParam(required = false) final String sortBy,
            @RequestParam(required = false) final String sortDir) {
        return catTipoDictamenAPIService.listar(pagina, tamano, busqueda, sortBy, sortDir);
    }

    @Operation(operationId = "crear-tipo-dictamen", summary = "Crea un nuevo registro Tipo Dictamen")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatTipoDictamenResponse.class)))
    })
    @PostMapping(value = UrlConst.TIPO_DICTAMEN_CREAR,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatTipoDictamenResponse> crear(@Valid @RequestBody final CatTipoDictamenRequest request) {
        return catTipoDictamenAPIService.crear(request);
    }

    @Operation(operationId = "buscar-tipo-dictamen", summary = "Busca un registro Tipo Dictamen por id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatTipoDictamenResponse.class)))
    })
    @GetMapping(value = UrlConst.TIPO_DICTAMEN_BUSCAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatTipoDictamenResponse> findById(
            @Parameter(description = "Id Tipo Dictamen") @PathVariable final Long idTipoDictamen) {
        return catTipoDictamenAPIService.findById(idTipoDictamen);
    }

    @Operation(operationId = "actualizar-tipo-dictamen", summary = "Actualiza un registro Tipo Dictamen")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatTipoDictamenResponse.class)))
    })
    @PutMapping(value = UrlConst.TIPO_DICTAMEN_ACTUALIZAR,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatTipoDictamenResponse> actualizar(
            @Parameter(description = "Id Tipo Dictamen") @PathVariable final Long idTipoDictamen,
            @RequestBody final CatTipoDictamenRequest request) {
        return catTipoDictamenAPIService.actualizar(idTipoDictamen, request);
    }
}
