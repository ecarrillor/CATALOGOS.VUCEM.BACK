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
import mx.gob.sat.catalogo.controller.request.actividadeconomicasat.CatActividadEconomicaSatRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.actividadeconomicasat.CatActividadEconomicaSatResponse;
import mx.gob.sat.catalogo.service.api.actividadeconomicasat.CatActividadEconomicaSatAPIService;
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

@Tag(name = "Cat. Actividad Economica SAT", description = "...")
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class CatActividadEconomicaSatController extends ApiBaseController {

    private final CatActividadEconomicaSatAPIService catActividadEconomicaSatAPIService;

    @Operation(operationId = "listar-actividad-economica-sat", summary = "Lista los registros Actividad Economica SAT paginados", description = "...")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = PaginaResponse.class)))
    })
    @GetMapping(value = UrlConst.ACTIVIDAD_ECONOMICA_SAT_LISTAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<PaginaResponse<CatActividadEconomicaSatResponse>> listar(
            @RequestParam(defaultValue = "0") final int pagina,
            @RequestParam(defaultValue = "20") final int tamano,
            @RequestParam(required = false) final String busqueda,
            @RequestParam(required = false) final String sortBy,
            @RequestParam(required = false) final String sortDir) {
        return catActividadEconomicaSatAPIService.listar(pagina, tamano, busqueda, sortBy, sortDir);
    }

    @Operation(operationId = "crear-actividad-economica-sat", summary = "Crea un nuevo registro Actividad Economica SAT")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatActividadEconomicaSatResponse.class)))
    })
    @PostMapping(value = UrlConst.ACTIVIDAD_ECONOMICA_SAT_CREAR, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatActividadEconomicaSatResponse> crear(@Valid @RequestBody final CatActividadEconomicaSatRequest request) {
        return catActividadEconomicaSatAPIService.crear(request);
    }

    @Operation(operationId = "buscar-actividad-economica-sat", summary = "Busca un registro Actividad Economica SAT por id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatActividadEconomicaSatResponse.class)))
    })
    @GetMapping(value = UrlConst.ACTIVIDAD_ECONOMICA_SAT_BUSCAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatActividadEconomicaSatResponse> findById(
            @Parameter(description = "Id Actividad Economica SAT") @PathVariable final Long idActividadEconomicaSat) {
        return catActividadEconomicaSatAPIService.findById(idActividadEconomicaSat);
    }

    @Operation(operationId = "actualizar-actividad-economica-sat", summary = "Actualiza un registro Actividad Economica SAT")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatActividadEconomicaSatResponse.class)))
    })
    @PutMapping(value = UrlConst.ACTIVIDAD_ECONOMICA_SAT_ACTUALIZAR, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatActividadEconomicaSatResponse> actualizar(
            @Parameter(description = "Id Actividad Economica SAT") @PathVariable final Long idActividadEconomicaSat,
            @RequestBody final CatActividadEconomicaSatRequest request) {
        return catActividadEconomicaSatAPIService.actualizar(idActividadEconomicaSat, request);
    }
}
