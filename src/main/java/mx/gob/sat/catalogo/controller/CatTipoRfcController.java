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
import mx.gob.sat.catalogo.controller.request.tiporfc.CatTipoRfcRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.tiporfc.CatTipoRfcResponse;
import mx.gob.sat.catalogo.service.api.tiporfc.CatTipoRfcAPIService;
import mx.gob.sat.catalogo.util.consts.UrlConst;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Cat. Tipo RFC", description = "...")
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class CatTipoRfcController extends ApiBaseController {

    private final CatTipoRfcAPIService catTipoRfcAPIService;

    @Operation(operationId = "listar-tipo-rfc", summary = "Lista los registros Tipo RFC paginados", description = "...")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
        content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = PaginaResponse.class))) })
    @GetMapping(value = UrlConst.TIPO_RFC_LISTAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<PaginaResponse<CatTipoRfcResponse>> listar(
            @RequestParam(defaultValue = "0") final int pagina,
            @RequestParam(defaultValue = "20") final int tamano,
            @RequestParam(required = false) final String busqueda,
            @RequestParam(required = false) final String sortBy,
            @RequestParam(required = false) final String sortDir) {
        return catTipoRfcAPIService.listar(pagina, tamano, busqueda, sortBy, sortDir);
    }

    @Operation(operationId = "crear-tipo-rfc", summary = "Crea un nuevo registro Tipo RFC")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
        content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CatTipoRfcResponse.class))) })
    @PostMapping(value = UrlConst.TIPO_RFC_CREAR, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatTipoRfcResponse> crear(@Valid @RequestBody final CatTipoRfcRequest request) {
        return catTipoRfcAPIService.crear(request);
    }

    @Operation(operationId = "buscar-tipo-rfc", summary = "Busca un registro Tipo RFC por id")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
        content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CatTipoRfcResponse.class))) })
    @GetMapping(value = UrlConst.TIPO_RFC_BUSCAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatTipoRfcResponse> findById(
            @Parameter(description = "RFC") @PathVariable final String rfc,
            @Parameter(description = "Ide Tipo RFC") @PathVariable final String ideTipoRfc) {
        return catTipoRfcAPIService.findById(rfc, ideTipoRfc);
    }

    @Operation(operationId = "actualizar-tipo-rfc", summary = "Actualiza un registro Tipo RFC")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
        content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CatTipoRfcResponse.class))) })
    @PutMapping(value = UrlConst.TIPO_RFC_ACTUALIZAR, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatTipoRfcResponse> actualizar(
            @Parameter(description = "RFC") @PathVariable final String rfc,
            @Parameter(description = "Ide Tipo RFC") @PathVariable final String ideTipoRfc,
            @RequestBody final CatTipoRfcRequest request) {
        return catTipoRfcAPIService.actualizar(rfc, ideTipoRfc, request);
    }
}
