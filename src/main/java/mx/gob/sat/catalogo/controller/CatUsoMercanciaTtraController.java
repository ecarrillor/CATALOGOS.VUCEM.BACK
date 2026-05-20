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
import mx.gob.sat.catalogo.controller.request.usomercanciattra.CatUsoMercanciaTtraRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.usomercanciattra.CatUsoMercanciaTtraResponse;
import mx.gob.sat.catalogo.service.api.usomercanciattra.CatUsoMercanciaTtraAPIService;
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
 * <b>Class:</b> CatUsoMercanciaTtraController.java <br>
 * <b>Description:</b>
 * <p>Controlador REST para el catalogo de uso de mercancia ttra.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Controlador
 */
@Tag(name = "Cat. Uso Mercancia Ttra", description = "...")
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class CatUsoMercanciaTtraController extends ApiBaseController {

    private final CatUsoMercanciaTtraAPIService catUsoMercanciaTtraAPIService;

    @Operation(operationId = "listar-uso-mercancia-ttra", summary = "Lista los registros Uso Mercancia Ttra paginados", description = "...")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = PaginaResponse.class)))
    })
    @GetMapping(value = UrlConst.USO_MERCANCIA_TTRA_LISTAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<PaginaResponse<CatUsoMercanciaTtraResponse>> listar(
            @RequestParam(defaultValue = "0") final int pagina,
            @RequestParam(defaultValue = "20") final int tamano,
            @RequestParam(required = false) final String busqueda,
            @RequestParam(required = false) final String sortBy,
            @RequestParam(required = false) final String sortDir) {
        return catUsoMercanciaTtraAPIService.listar(pagina, tamano, busqueda, sortBy, sortDir);
    }

    @Operation(operationId = "crear-uso-mercancia-ttra", summary = "Crea un nuevo registro Uso Mercancia Ttra")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatUsoMercanciaTtraResponse.class)))
    })
    @PostMapping(value = UrlConst.USO_MERCANCIA_TTRA_CREAR, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatUsoMercanciaTtraResponse> crear(@Valid @RequestBody final CatUsoMercanciaTtraRequest request) {
        return catUsoMercanciaTtraAPIService.crear(request);
    }

    @Operation(operationId = "buscar-uso-mercancia-ttra", summary = "Busca un registro Uso Mercancia Ttra por id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatUsoMercanciaTtraResponse.class)))
    })
    @GetMapping(value = UrlConst.USO_MERCANCIA_TTRA_BUSCAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatUsoMercanciaTtraResponse> findById(
            @Parameter(description = "Id Uso Mercancia Ttra") @PathVariable final Short idUsoMercanciaTtra) {
        return catUsoMercanciaTtraAPIService.findById(idUsoMercanciaTtra);
    }

    @Operation(operationId = "actualizar-uso-mercancia-ttra", summary = "Actualiza un registro Uso Mercancia Ttra")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatUsoMercanciaTtraResponse.class)))
    })
    @PutMapping(value = UrlConst.USO_MERCANCIA_TTRA_ACTUALIZAR, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatUsoMercanciaTtraResponse> actualizar(
            @Parameter(description = "Id Uso Mercancia Ttra") @PathVariable final Short idUsoMercanciaTtra,
            @RequestBody final CatUsoMercanciaTtraRequest request) {
        return catUsoMercanciaTtraAPIService.actualizar(idUsoMercanciaTtra, request);
    }
}
