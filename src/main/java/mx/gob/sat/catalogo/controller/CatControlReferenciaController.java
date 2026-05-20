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
import mx.gob.sat.catalogo.controller.request.controlreferencia.CatControlReferenciaRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.controlreferencia.CatControlReferenciaResponse;
import mx.gob.sat.catalogo.service.api.controlreferencia.CatControlReferenciaAPIService;
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

@Tag(name = "Cat. Control Referencia", description = "...")
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class CatControlReferenciaController extends ApiBaseController {

    private final CatControlReferenciaAPIService catControlReferenciaAPIService;

    @Operation(operationId = "listar-control-referencia", summary = "Lista los registros Control de Referencia paginados", description = "...")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = PaginaResponse.class)))
    })
    @GetMapping(value = UrlConst.CONTROL_REFERENCIA_LISTAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<PaginaResponse<CatControlReferenciaResponse>> listar(
            @RequestParam(defaultValue = "0") final int pagina,
            @RequestParam(defaultValue = "20") final int tamano,
            @RequestParam(required = false) final String busqueda,
            @RequestParam(required = false) final String sortBy,
            @RequestParam(required = false) final String sortDir) {
        return catControlReferenciaAPIService.listar(pagina, tamano, busqueda, sortBy, sortDir);
    }

    @Operation(operationId = "crear-control-referencia", summary = "Crea un nuevo registro Control de Referencia")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatControlReferenciaResponse.class)))
    })
    @PostMapping(value = UrlConst.CONTROL_REFERENCIA_CREAR, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatControlReferenciaResponse> crear(@Valid @RequestBody final CatControlReferenciaRequest request) {
        return catControlReferenciaAPIService.crear(request);
    }

    @Operation(operationId = "buscar-control-referencia", summary = "Busca un registro Control de Referencia por id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatControlReferenciaResponse.class)))
    })
    @GetMapping(value = UrlConst.CONTROL_REFERENCIA_BUSCAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatControlReferenciaResponse> findById(
            @Parameter(description = "Id Control de Referencia") @PathVariable final Integer idControlReferencia) {
        return catControlReferenciaAPIService.findById(idControlReferencia);
    }

    @Operation(operationId = "actualizar-control-referencia", summary = "Actualiza un registro Control de Referencia")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatControlReferenciaResponse.class)))
    })
    @PutMapping(value = UrlConst.CONTROL_REFERENCIA_ACTUALIZAR, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatControlReferenciaResponse> actualizar(
            @Parameter(description = "Id Control de Referencia") @PathVariable final Integer idControlReferencia,
            @RequestBody final CatControlReferenciaRequest request) {
        return catControlReferenciaAPIService.actualizar(idControlReferencia, request);
    }
}
