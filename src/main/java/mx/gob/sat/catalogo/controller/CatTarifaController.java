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
import mx.gob.sat.catalogo.controller.request.tarifa.CatTarifaRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.tarifa.CatTarifaResponse;
import mx.gob.sat.catalogo.service.api.tarifa.CatTarifaAPIService;
import mx.gob.sat.catalogo.util.consts.UrlConst;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;

@Tag(name = "Cat. Tarifa", description = "Operaciones sobre el catalogo de tarifas")
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class CatTarifaController extends ApiBaseController {

    private final CatTarifaAPIService catTarifaAPIService;

    @Operation(operationId = "listar-tarifa", summary = "Lista los registros Tarifa paginados", description = "Retorna una pagina de registros del catalogo de tarifas")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
        content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = PaginaResponse.class))) })
    @GetMapping(value = UrlConst.TARIFA_LISTAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<PaginaResponse<CatTarifaResponse>> listar(
            @RequestParam(defaultValue = "0") final int pagina,
            @RequestParam(defaultValue = "20") final int tamano,
            @RequestParam(required = false) final String busqueda,
            @RequestParam(required = false) final String sortBy,
            @RequestParam(required = false) final String sortDir) {
        return catTarifaAPIService.listar(pagina, tamano, busqueda, sortBy, sortDir);
    }

    @Operation(operationId = "crear-tarifa", summary = "Crea un nuevo registro Tarifa")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
        content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CatTarifaResponse.class))) })
    @PostMapping(value = UrlConst.TARIFA_CREAR, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatTarifaResponse> crear(@Valid @RequestBody final CatTarifaRequest request) {
        return catTarifaAPIService.crear(request);
    }

    @Operation(operationId = "buscar-tarifa", summary = "Busca un registro Tarifa por id")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
        content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CatTarifaResponse.class))) })
    @GetMapping(value = UrlConst.TARIFA_BUSCAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatTarifaResponse> findById(
            @Parameter(description = "Identificador del tipo de tramite") @PathVariable final Long idTipoTramite,
            @Parameter(description = "Fecha inicio vigencia") @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final LocalDate fecIniVigencia,
            @Parameter(description = "Identificador del tipo de tarifa") @PathVariable final String ideTipoTarifa) {
        return catTarifaAPIService.findById(idTipoTramite, fecIniVigencia, ideTipoTarifa);
    }

    @Operation(operationId = "actualizar-tarifa", summary = "Actualiza un registro Tarifa")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
        content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CatTarifaResponse.class))) })
    @PutMapping(value = UrlConst.TARIFA_ACTUALIZAR, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatTarifaResponse> actualizar(
            @Parameter(description = "Identificador del tipo de tramite") @PathVariable final Long idTipoTramite,
            @Parameter(description = "Fecha inicio vigencia") @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final LocalDate fecIniVigencia,
            @Parameter(description = "Identificador del tipo de tarifa") @PathVariable final String ideTipoTarifa,
            @RequestBody final CatTarifaRequest request) {
        return catTarifaAPIService.actualizar(idTipoTramite, fecIniVigencia, ideTipoTarifa, request);
    }
}
