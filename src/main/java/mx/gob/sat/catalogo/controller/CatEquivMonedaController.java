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
import mx.gob.sat.catalogo.controller.request.equivmoneda.CatEquivMonedaRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.equivmoneda.CatEquivMonedaResponse;
import mx.gob.sat.catalogo.service.api.equivmoneda.CatEquivMonedaAPIService;
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
 * <b>Class:</b> CatEquivMonedaController.java <br>
 * <b>Description:</b>
 * <p>Controlador REST para el catalogo de equivalencia de moneda.
 * Expone endpoints bajo la ruta base {@code api/catalogo-admin/equiv-moneda}.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Controlador
 */
@Tag(name = "Cat. Equiv Moneda",
        description = "Servicios de consulta y mantenimiento del catalogo de equivalencia de moneda")
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class CatEquivMonedaController extends ApiBaseController {

    /** Servicio de negocio para el catalogo de equivalencia de moneda. */
    private final CatEquivMonedaAPIService catEquivMonedaAPIService;

    @Operation(operationId = "listar-equiv-moneda", summary = "Lista los registros de equivalencia de moneda paginados",
        description = "Retorna una pagina de registros de equivalencia de moneda con soporte de busqueda y ordenamiento.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = PaginaResponse.class)))
    })
    @GetMapping(value = UrlConst.EQUIV_MONEDA_LISTAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<PaginaResponse<CatEquivMonedaResponse>> listar(
            @RequestParam(defaultValue = "0") final int pagina,
            @RequestParam(defaultValue = "20") final int tamano,
            @RequestParam(required = false) final String busqueda,
            @RequestParam(required = false) final String sortBy,
            @RequestParam(required = false) final String sortDir) {
        return catEquivMonedaAPIService.listar(pagina, tamano, busqueda, sortBy, sortDir);
    }

    @Operation(operationId = "crear-equiv-moneda", summary = "Crea un nuevo registro de equivalencia de moneda")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatEquivMonedaResponse.class)))
    })
    @PostMapping(value = UrlConst.EQUIV_MONEDA_CREAR,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatEquivMonedaResponse> crear(@Valid @RequestBody final CatEquivMonedaRequest request) {
        return catEquivMonedaAPIService.crear(request);
    }

    @Operation(operationId = "buscar-equiv-moneda", summary = "Busca un registro de equivalencia de moneda por identificador")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatEquivMonedaResponse.class)))
    })
    @GetMapping(value = UrlConst.EQUIV_MONEDA_BUSCAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatEquivMonedaResponse> findById(
            @Parameter(description = "Identificador de equivalencia de moneda") @PathVariable final Integer idEquivMoneda) {
        return catEquivMonedaAPIService.findById(idEquivMoneda);
    }

    @Operation(operationId = "actualizar-equiv-moneda", summary = "Actualiza un registro de equivalencia de moneda")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatEquivMonedaResponse.class)))
    })
    @PutMapping(value = UrlConst.EQUIV_MONEDA_ACTUALIZAR,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatEquivMonedaResponse> actualizar(
            @Parameter(description = "Identificador de equivalencia de moneda") @PathVariable final Integer idEquivMoneda,
            @RequestBody final CatEquivMonedaRequest request) {
        return catEquivMonedaAPIService.actualizar(idEquivMoneda, request);
    }
}
