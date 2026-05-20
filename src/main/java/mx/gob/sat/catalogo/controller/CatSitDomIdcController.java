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
import mx.gob.sat.catalogo.controller.request.sitdomidc.CatSitDomIdcRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.sitdomidc.CatSitDomIdcResponse;
import mx.gob.sat.catalogo.service.api.sitdomidc.CatSitDomIdcAPIService;
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
 * <b>Class:</b> CatSitDomIdcController.java <br>
 * <b>Description:</b>
 * <p>Controlador REST para el catalogo Sit Dom Idc.
 * Expone endpoints bajo la ruta base {@code api/catalogo-admin/sit-dom-idc}.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Controlador
 */
@Tag(name = "Cat. Sit Dom Idc",
        description = "Servicios de consulta y mantenimiento del catalogo Sit Dom Idc")
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class CatSitDomIdcController extends ApiBaseController {

    private final CatSitDomIdcAPIService catSitDomIdcAPIService;

    @Operation(operationId = "listar-sit-dom-idc", summary = "Lista los registros Sit Dom Idc paginados",
        description = "Retorna una pagina de registros Sit Dom Idc con soporte de busqueda y ordenamiento.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = PaginaResponse.class)))
    })
    @GetMapping(value = UrlConst.SIT_DOM_IDC_LISTAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<PaginaResponse<CatSitDomIdcResponse>> listar(
            @RequestParam(defaultValue = "0") final int pagina,
            @RequestParam(defaultValue = "20") final int tamano,
            @RequestParam(required = false) final String busqueda,
            @RequestParam(required = false) final String sortBy,
            @RequestParam(required = false) final String sortDir) {
        return catSitDomIdcAPIService.listar(pagina, tamano, busqueda, sortBy, sortDir);
    }

    @Operation(operationId = "crear-sit-dom-idc", summary = "Crea un nuevo registro Sit Dom Idc")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatSitDomIdcResponse.class)))
    })
    @PostMapping(value = UrlConst.SIT_DOM_IDC_CREAR,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatSitDomIdcResponse> crear(@Valid @RequestBody final CatSitDomIdcRequest request) {
        return catSitDomIdcAPIService.crear(request);
    }

    @Operation(operationId = "buscar-sit-dom-idc", summary = "Busca un registro Sit Dom Idc por id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatSitDomIdcResponse.class)))
    })
    @GetMapping(value = UrlConst.SIT_DOM_IDC_BUSCAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatSitDomIdcResponse> findById(
            @Parameter(description = "Id Sit Dom Idc") @PathVariable final Long idSitDomIdc) {
        return catSitDomIdcAPIService.findById(idSitDomIdc);
    }

    @Operation(operationId = "actualizar-sit-dom-idc", summary = "Actualiza un registro Sit Dom Idc")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatSitDomIdcResponse.class)))
    })
    @PutMapping(value = UrlConst.SIT_DOM_IDC_ACTUALIZAR,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatSitDomIdcResponse> actualizar(
            @Parameter(description = "Id Sit Dom Idc") @PathVariable final Long idSitDomIdc,
            @RequestBody final CatSitDomIdcRequest request) {
        return catSitDomIdcAPIService.actualizar(idSitDomIdc, request);
    }
}
