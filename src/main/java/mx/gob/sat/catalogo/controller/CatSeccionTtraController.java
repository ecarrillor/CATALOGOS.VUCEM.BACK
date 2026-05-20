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
import mx.gob.sat.catalogo.controller.request.seccionttra.CatSeccionTtraRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.seccionttra.CatSeccionTtraResponse;
import mx.gob.sat.catalogo.service.api.seccionttra.CatSeccionTtraAPIService;
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
 * <b>Class:</b> CatSeccionTtraController.java <br>
 * <b>Description:</b>
 * <p>Controlador REST para el catalogo de secciones ttra.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Controlador
 */
@Tag(name = "Cat. Seccion Ttra", description = "...")
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class CatSeccionTtraController extends ApiBaseController {

    private final CatSeccionTtraAPIService catSeccionTtraAPIService;

    @Operation(operationId = "listar-seccion-ttra", summary = "Lista los registros Seccion Ttra paginados", description = "...")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = PaginaResponse.class)))
    })
    @GetMapping(value = UrlConst.SECCION_TTRA_LISTAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<PaginaResponse<CatSeccionTtraResponse>> listar(
            @RequestParam(defaultValue = "0") final int pagina,
            @RequestParam(defaultValue = "20") final int tamano,
            @RequestParam(required = false) final String busqueda,
            @RequestParam(required = false) final String sortBy,
            @RequestParam(required = false) final String sortDir) {
        return catSeccionTtraAPIService.listar(pagina, tamano, busqueda, sortBy, sortDir);
    }

    @Operation(operationId = "crear-seccion-ttra", summary = "Crea un nuevo registro Seccion Ttra")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatSeccionTtraResponse.class)))
    })
    @PostMapping(value = UrlConst.SECCION_TTRA_CREAR, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatSeccionTtraResponse> crear(@Valid @RequestBody final CatSeccionTtraRequest request) {
        return catSeccionTtraAPIService.crear(request);
    }

    @Operation(operationId = "buscar-seccion-ttra", summary = "Busca un registro Seccion Ttra por id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatSeccionTtraResponse.class)))
    })
    @GetMapping(value = UrlConst.SECCION_TTRA_BUSCAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatSeccionTtraResponse> findById(
            @Parameter(description = "Id Seccion Ttra") @PathVariable final Integer idSeccionTtra) {
        return catSeccionTtraAPIService.findById(idSeccionTtra);
    }

    @Operation(operationId = "actualizar-seccion-ttra", summary = "Actualiza un registro Seccion Ttra")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatSeccionTtraResponse.class)))
    })
    @PutMapping(value = UrlConst.SECCION_TTRA_ACTUALIZAR, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatSeccionTtraResponse> actualizar(
            @Parameter(description = "Id Seccion Ttra") @PathVariable final Integer idSeccionTtra,
            @RequestBody final CatSeccionTtraRequest request) {
        return catSeccionTtraAPIService.actualizar(idSeccionTtra, request);
    }
}
