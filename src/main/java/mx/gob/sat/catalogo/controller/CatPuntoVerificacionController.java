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
import mx.gob.sat.catalogo.controller.request.puntoverificacion.CatPuntoVerificacionRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.puntoverificacion.CatPuntoVerificacionResponse;
import mx.gob.sat.catalogo.service.api.puntoverificacion.CatPuntoVerificacionAPIService;
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

@Tag(name = "Cat. Punto Verificacion", description = "...")
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class CatPuntoVerificacionController extends ApiBaseController {

    private final CatPuntoVerificacionAPIService catPuntoVerificacionAPIService;

    @Operation(operationId = "listar-punto-verificacion", summary = "Lista los registros Punto Verificacion paginados", description = "...")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = PaginaResponse.class)))
    })
    @GetMapping(value = UrlConst.PUNTO_VERIFICACION_LISTAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<PaginaResponse<CatPuntoVerificacionResponse>> listar(
            @RequestParam(defaultValue = "0") final int pagina,
            @RequestParam(defaultValue = "20") final int tamano,
            @RequestParam(required = false) final String busqueda,
            @RequestParam(required = false) final String sortBy,
            @RequestParam(required = false) final String sortDir) {
        return catPuntoVerificacionAPIService.listar(pagina, tamano, busqueda, sortBy, sortDir);
    }

    @Operation(operationId = "crear-punto-verificacion", summary = "Crea un nuevo registro Punto Verificacion")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatPuntoVerificacionResponse.class)))
    })
    @PostMapping(value = UrlConst.PUNTO_VERIFICACION_CREAR, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatPuntoVerificacionResponse> crear(@Valid @RequestBody final CatPuntoVerificacionRequest request) {
        return catPuntoVerificacionAPIService.crear(request);
    }

    @Operation(operationId = "buscar-punto-verificacion", summary = "Busca un registro Punto Verificacion por id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatPuntoVerificacionResponse.class)))
    })
    @GetMapping(value = UrlConst.PUNTO_VERIFICACION_BUSCAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatPuntoVerificacionResponse> findById(
            @Parameter(description = "Id Punto Verificacion") @PathVariable final Integer idPuntoVerificacion) {
        return catPuntoVerificacionAPIService.findById(idPuntoVerificacion);
    }

    @Operation(operationId = "actualizar-punto-verificacion", summary = "Actualiza un registro Punto Verificacion")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatPuntoVerificacionResponse.class)))
    })
    @PutMapping(value = UrlConst.PUNTO_VERIFICACION_ACTUALIZAR, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatPuntoVerificacionResponse> actualizar(
            @Parameter(description = "Id Punto Verificacion") @PathVariable final Integer idPuntoVerificacion,
            @RequestBody final CatPuntoVerificacionRequest request) {
        return catPuntoVerificacionAPIService.actualizar(idPuntoVerificacion, request);
    }
}
