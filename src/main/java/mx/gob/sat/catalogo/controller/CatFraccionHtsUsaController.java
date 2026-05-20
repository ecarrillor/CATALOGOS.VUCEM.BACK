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
import mx.gob.sat.catalogo.controller.request.fraccionhtsusa.CatFraccionHtsUsaRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.fraccionhtsusa.CatFraccionHtsUsaResponse;
import mx.gob.sat.catalogo.service.api.fraccionhtsusa.CatFraccionHtsUsaAPIService;
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
 * <b>Class:</b> CatFraccionHtsUsaController.java <br>
 * <b>Description:</b>
 * <p>Controlador REST para el catalogo de fracciones HTS USA.
 * Expone endpoints bajo la ruta base {@code api/catalogo-admin/fraccion-hts-usa}.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Controlador
 */
@Tag(name = "Cat. Fraccion HTS USA",
        description = "Servicios de consulta y mantenimiento del catalogo de fracciones HTS USA")
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class CatFraccionHtsUsaController extends ApiBaseController {

    /** Servicio de negocio para el catalogo de fracciones HTS USA. */
    private final CatFraccionHtsUsaAPIService catFraccionHtsUsaAPIService;

    @Operation(operationId = "listar-fraccion-hts-usa", summary = "Lista los registros de fraccion HTS USA paginados",
            description = "Retorna una pagina de registros de fraccion HTS USA con soporte de busqueda y ordenamiento.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = PaginaResponse.class)))
    })
    @GetMapping(value = UrlConst.FRACCION_HTS_USA_LISTAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<PaginaResponse<CatFraccionHtsUsaResponse>> listar(
            @RequestParam(defaultValue = "0") final int pagina,
            @RequestParam(defaultValue = "20") final int tamano,
            @RequestParam(required = false) final String busqueda,
            @RequestParam(required = false) final String sortBy,
            @RequestParam(required = false) final String sortDir) {
        return catFraccionHtsUsaAPIService.listar(pagina, tamano, busqueda, sortBy, sortDir);
    }

    @Operation(operationId = "crear-fraccion-hts-usa", summary = "Crea un nuevo registro de fraccion HTS USA")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatFraccionHtsUsaResponse.class)))
    })
    @PostMapping(value = UrlConst.FRACCION_HTS_USA_CREAR,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatFraccionHtsUsaResponse> crear(@Valid @RequestBody final CatFraccionHtsUsaRequest request) {
        return catFraccionHtsUsaAPIService.crear(request);
    }

    @Operation(operationId = "buscar-fraccion-hts-usa", summary = "Busca un registro de fraccion HTS USA por identificador")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatFraccionHtsUsaResponse.class)))
    })
    @GetMapping(value = UrlConst.FRACCION_HTS_USA_BUSCAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatFraccionHtsUsaResponse> findById(
            @Parameter(description = "Identificador de fraccion HTS USA") @PathVariable final Long idFraccionHtsUsa) {
        return catFraccionHtsUsaAPIService.findById(idFraccionHtsUsa);
    }

    @Operation(operationId = "actualizar-fraccion-hts-usa", summary = "Actualiza un registro de fraccion HTS USA")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatFraccionHtsUsaResponse.class)))
    })
    @PutMapping(value = UrlConst.FRACCION_HTS_USA_ACTUALIZAR,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatFraccionHtsUsaResponse> actualizar(
            @Parameter(description = "Identificador de fraccion HTS USA") @PathVariable final Long idFraccionHtsUsa,
            @RequestBody final CatFraccionHtsUsaRequest request) {
        return catFraccionHtsUsaAPIService.actualizar(idFraccionHtsUsa, request);
    }
}
