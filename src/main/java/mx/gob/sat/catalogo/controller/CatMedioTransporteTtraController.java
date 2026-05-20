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
import mx.gob.sat.catalogo.controller.request.mediotransportettra.CatMedioTransporteTtraRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.mediotransportettra.CatMedioTransporteTtraResponse;
import mx.gob.sat.catalogo.service.api.mediotransportettra.CatMedioTransporteTtraAPIService;
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
 * <b>Class:</b> CatMedioTransporteTtraController.java <br>
 * <b>Description:</b>
 * <p>Controlador REST para el catalogo de medios de transporte ttra.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Controlador
 */
@Tag(name = "Cat. Medio Transporte Ttra", description = "...")
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class CatMedioTransporteTtraController extends ApiBaseController {

    private final CatMedioTransporteTtraAPIService catMedioTransporteTtraAPIService;

    @Operation(operationId = "listar-medio-transporte-ttra", summary = "Lista los registros Medio Transporte Ttra paginados", description = "...")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = PaginaResponse.class)))
    })
    @GetMapping(value = UrlConst.MEDIO_TRANSPORTE_TTRA_LISTAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<PaginaResponse<CatMedioTransporteTtraResponse>> listar(
            @RequestParam(defaultValue = "0") final int pagina,
            @RequestParam(defaultValue = "20") final int tamano,
            @RequestParam(required = false) final String busqueda,
            @RequestParam(required = false) final String sortBy,
            @RequestParam(required = false) final String sortDir) {
        return catMedioTransporteTtraAPIService.listar(pagina, tamano, busqueda, sortBy, sortDir);
    }

    @Operation(operationId = "crear-medio-transporte-ttra", summary = "Crea un nuevo registro Medio Transporte Ttra")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatMedioTransporteTtraResponse.class)))
    })
    @PostMapping(value = UrlConst.MEDIO_TRANSPORTE_TTRA_CREAR, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatMedioTransporteTtraResponse> crear(@Valid @RequestBody final CatMedioTransporteTtraRequest request) {
        return catMedioTransporteTtraAPIService.crear(request);
    }

    @Operation(operationId = "buscar-medio-transporte-ttra", summary = "Busca un registro Medio Transporte Ttra por id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatMedioTransporteTtraResponse.class)))
    })
    @GetMapping(value = UrlConst.MEDIO_TRANSPORTE_TTRA_BUSCAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatMedioTransporteTtraResponse> findById(
            @Parameter(description = "Id Medio Transporte Ttra") @PathVariable final Integer idMedioTransporteTtra) {
        return catMedioTransporteTtraAPIService.findById(idMedioTransporteTtra);
    }

    @Operation(operationId = "actualizar-medio-transporte-ttra", summary = "Actualiza un registro Medio Transporte Ttra")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatMedioTransporteTtraResponse.class)))
    })
    @PutMapping(value = UrlConst.MEDIO_TRANSPORTE_TTRA_ACTUALIZAR, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatMedioTransporteTtraResponse> actualizar(
            @Parameter(description = "Id Medio Transporte Ttra") @PathVariable final Integer idMedioTransporteTtra,
            @RequestBody final CatMedioTransporteTtraRequest request) {
        return catMedioTransporteTtraAPIService.actualizar(idMedioTransporteTtra, request);
    }
}
