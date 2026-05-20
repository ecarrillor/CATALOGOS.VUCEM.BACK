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
import mx.gob.sat.catalogo.controller.request.fundamentottra.CatFundamentoTtraRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.fundamentottra.CatFundamentoTtraResponse;
import mx.gob.sat.catalogo.service.api.fundamentottra.CatFundamentoTtraAPIService;
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
 * <b>Class:</b> CatFundamentoTtraController.java <br>
 * <b>Description:</b>
 * <p>Controlador REST para el catalogo de fundamentos ttra.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Controlador
 */
@Tag(name = "Cat. Fundamento Ttra", description = "...")
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class CatFundamentoTtraController extends ApiBaseController {

    private final CatFundamentoTtraAPIService catFundamentoTtraAPIService;

    @Operation(operationId = "listar-fundamento-ttra", summary = "Lista los registros Fundamento Ttra paginados", description = "...")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = PaginaResponse.class)))
    })
    @GetMapping(value = UrlConst.FUNDAMENTO_TTRA_LISTAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<PaginaResponse<CatFundamentoTtraResponse>> listar(
            @RequestParam(defaultValue = "0") final int pagina,
            @RequestParam(defaultValue = "20") final int tamano,
            @RequestParam(required = false) final String busqueda,
            @RequestParam(required = false) final String sortBy,
            @RequestParam(required = false) final String sortDir) {
        return catFundamentoTtraAPIService.listar(pagina, tamano, busqueda, sortBy, sortDir);
    }

    @Operation(operationId = "crear-fundamento-ttra", summary = "Crea un nuevo registro Fundamento Ttra")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatFundamentoTtraResponse.class)))
    })
    @PostMapping(value = UrlConst.FUNDAMENTO_TTRA_CREAR, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatFundamentoTtraResponse> crear(@Valid @RequestBody final CatFundamentoTtraRequest request) {
        return catFundamentoTtraAPIService.crear(request);
    }

    @Operation(operationId = "buscar-fundamento-ttra", summary = "Busca un registro Fundamento Ttra por id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatFundamentoTtraResponse.class)))
    })
    @GetMapping(value = UrlConst.FUNDAMENTO_TTRA_BUSCAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatFundamentoTtraResponse> findById(
            @Parameter(description = "Id Fundamento Ttra") @PathVariable final Short idFundamentoTtra) {
        return catFundamentoTtraAPIService.findById(idFundamentoTtra);
    }

    @Operation(operationId = "actualizar-fundamento-ttra", summary = "Actualiza un registro Fundamento Ttra")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatFundamentoTtraResponse.class)))
    })
    @PutMapping(value = UrlConst.FUNDAMENTO_TTRA_ACTUALIZAR, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatFundamentoTtraResponse> actualizar(
            @Parameter(description = "Id Fundamento Ttra") @PathVariable final Short idFundamentoTtra,
            @RequestBody final CatFundamentoTtraRequest request) {
        return catFundamentoTtraAPIService.actualizar(idFundamentoTtra, request);
    }
}
