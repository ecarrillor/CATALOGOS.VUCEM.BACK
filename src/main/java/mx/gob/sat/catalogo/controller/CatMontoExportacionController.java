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
import mx.gob.sat.catalogo.controller.request.montoexportacion.CatMontoExportacionRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.montoexportacion.CatMontoExportacionResponse;
import mx.gob.sat.catalogo.service.api.montoexportacion.CatMontoExportacionAPIService;
import mx.gob.sat.catalogo.util.consts.UrlConst;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;

@Tag(name = "Cat. Monto Exportacion", description = "Operaciones sobre el catalogo de montos de exportacion")
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class CatMontoExportacionController extends ApiBaseController {

    private final CatMontoExportacionAPIService catMontoExportacionAPIService;

    @Operation(operationId = "listar-monto-exportacion", summary = "Lista los registros Monto Exportacion paginados", description = "Retorna una pagina de registros del catalogo de montos de exportacion")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
        content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = PaginaResponse.class))) })
    @GetMapping(value = UrlConst.MONTO_EXPORTACION_LISTAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<PaginaResponse<CatMontoExportacionResponse>> listar(
            @RequestParam(defaultValue = "0") final int pagina,
            @RequestParam(defaultValue = "20") final int tamano,
            @RequestParam(required = false) final String busqueda,
            @RequestParam(required = false) final String sortBy,
            @RequestParam(required = false) final String sortDir) {
        return catMontoExportacionAPIService.listar(pagina, tamano, busqueda, sortBy, sortDir);
    }

    @Operation(operationId = "crear-monto-exportacion", summary = "Crea un nuevo registro Monto Exportacion")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
        content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CatMontoExportacionResponse.class))) })
    @PostMapping(value = UrlConst.MONTO_EXPORTACION_CREAR, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatMontoExportacionResponse> crear(@Valid @RequestBody final CatMontoExportacionRequest request) {
        return catMontoExportacionAPIService.crear(request);
    }

    @Operation(operationId = "buscar-monto-exportacion", summary = "Busca un registro Monto Exportacion por id")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
        content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CatMontoExportacionResponse.class))) })
    @GetMapping(value = UrlConst.MONTO_EXPORTACION_BUSCAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatMontoExportacionResponse> findById(
            @Parameter(description = "RFC Exportador") @PathVariable final String rfcExportador,
            @Parameter(description = "Fecha Monto Exportacion") @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final LocalDate fecMontoExportacion) {
        return catMontoExportacionAPIService.findById(rfcExportador, fecMontoExportacion);
    }

    @Operation(operationId = "actualizar-monto-exportacion", summary = "Actualiza un registro Monto Exportacion")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
        content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CatMontoExportacionResponse.class))) })
    @PutMapping(value = UrlConst.MONTO_EXPORTACION_ACTUALIZAR, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatMontoExportacionResponse> actualizar(
            @Parameter(description = "RFC Exportador") @PathVariable final String rfcExportador,
            @Parameter(description = "Fecha Monto Exportacion") @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final LocalDate fecMontoExportacion,
            @RequestBody final CatMontoExportacionRequest request) {
        return catMontoExportacionAPIService.actualizar(rfcExportador, fecMontoExportacion, request);
    }
}
