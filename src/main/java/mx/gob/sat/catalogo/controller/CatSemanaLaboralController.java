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
import mx.gob.sat.catalogo.controller.request.semanalaboral.CatSemanaLaboralRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.semanalaboral.CatSemanaLaboralResponse;
import mx.gob.sat.catalogo.service.api.semanalaboral.CatSemanaLaboralAPIService;
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

@Tag(name = "Cat. Semana Laboral", description = "...")
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class CatSemanaLaboralController extends ApiBaseController {

    private final CatSemanaLaboralAPIService catSemanaLaboralAPIService;

    @Operation(operationId = "listar-semana-laboral", summary = "Lista los registros Semana Laboral paginados", description = "...")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = PaginaResponse.class)))
    })
    @GetMapping(value = UrlConst.SEMANA_LABORAL_LISTAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<PaginaResponse<CatSemanaLaboralResponse>> listar(
            @RequestParam(defaultValue = "0") final int pagina,
            @RequestParam(defaultValue = "20") final int tamano,
            @RequestParam(required = false) final String busqueda,
            @RequestParam(required = false) final String sortBy,
            @RequestParam(required = false) final String sortDir) {
        return catSemanaLaboralAPIService.listar(pagina, tamano, busqueda, sortBy, sortDir);
    }

    @Operation(operationId = "crear-semana-laboral", summary = "Crea un nuevo registro Semana Laboral")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatSemanaLaboralResponse.class)))
    })
    @PostMapping(value = UrlConst.SEMANA_LABORAL_CREAR, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatSemanaLaboralResponse> crear(@Valid @RequestBody final CatSemanaLaboralRequest request) {
        return catSemanaLaboralAPIService.crear(request);
    }

    @Operation(operationId = "buscar-semana-laboral", summary = "Busca un registro Semana Laboral por id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatSemanaLaboralResponse.class)))
    })
    @GetMapping(value = UrlConst.SEMANA_LABORAL_BUSCAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatSemanaLaboralResponse> findById(
            @Parameter(description = "Id Semana Laboral") @PathVariable final Short idSemanaLaboral) {
        return catSemanaLaboralAPIService.findById(idSemanaLaboral);
    }

    @Operation(operationId = "actualizar-semana-laboral", summary = "Actualiza un registro Semana Laboral")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatSemanaLaboralResponse.class)))
    })
    @PutMapping(value = UrlConst.SEMANA_LABORAL_ACTUALIZAR, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatSemanaLaboralResponse> actualizar(
            @Parameter(description = "Id Semana Laboral") @PathVariable final Short idSemanaLaboral,
            @RequestBody final CatSemanaLaboralRequest request) {
        return catSemanaLaboralAPIService.actualizar(idSemanaLaboral, request);
    }
}
