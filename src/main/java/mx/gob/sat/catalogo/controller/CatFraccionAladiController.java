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
import mx.gob.sat.catalogo.controller.request.fraccionaladi.CatFraccionAladiRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.fraccionaladi.CatFraccionAladiResponse;
import mx.gob.sat.catalogo.service.api.fraccionaladi.CatFraccionAladiAPIService;
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
 * <b>Class:</b> CatFraccionAladiController.java <br>
 * <b>Description:</b>
 * <p>Controlador REST para el catalogo de fracciones ALADI.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Controlador
 */
@Tag(name = "Cat. Fraccion ALADI", description = "...")
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class CatFraccionAladiController extends ApiBaseController {

    private final CatFraccionAladiAPIService catFraccionAladiAPIService;

    @Operation(operationId = "listar-fraccion-aladi", summary = "Lista los registros Fraccion ALADI paginados", description = "...")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = PaginaResponse.class)))
    })
    @GetMapping(value = UrlConst.FRACCION_ALADI_LISTAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<PaginaResponse<CatFraccionAladiResponse>> listar(
            @RequestParam(defaultValue = "0") final int pagina,
            @RequestParam(defaultValue = "20") final int tamano,
            @RequestParam(required = false) final String busqueda,
            @RequestParam(required = false) final String sortBy,
            @RequestParam(required = false) final String sortDir) {
        return catFraccionAladiAPIService.listar(pagina, tamano, busqueda, sortBy, sortDir);
    }

    @Operation(operationId = "crear-fraccion-aladi", summary = "Crea un nuevo registro Fraccion ALADI")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatFraccionAladiResponse.class)))
    })
    @PostMapping(value = UrlConst.FRACCION_ALADI_CREAR, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatFraccionAladiResponse> crear(@Valid @RequestBody final CatFraccionAladiRequest request) {
        return catFraccionAladiAPIService.crear(request);
    }

    @Operation(operationId = "buscar-fraccion-aladi", summary = "Busca un registro Fraccion ALADI por id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatFraccionAladiResponse.class)))
    })
    @GetMapping(value = UrlConst.FRACCION_ALADI_BUSCAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatFraccionAladiResponse> findById(
            @Parameter(description = "Id Fraccion ALADI") @PathVariable final Long idFraccionAladi) {
        return catFraccionAladiAPIService.findById(idFraccionAladi);
    }

    @Operation(operationId = "actualizar-fraccion-aladi", summary = "Actualiza un registro Fraccion ALADI")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatFraccionAladiResponse.class)))
    })
    @PutMapping(value = UrlConst.FRACCION_ALADI_ACTUALIZAR, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatFraccionAladiResponse> actualizar(
            @Parameter(description = "Id Fraccion ALADI") @PathVariable final Long idFraccionAladi,
            @RequestBody final CatFraccionAladiRequest request) {
        return catFraccionAladiAPIService.actualizar(idFraccionAladi, request);
    }
}
