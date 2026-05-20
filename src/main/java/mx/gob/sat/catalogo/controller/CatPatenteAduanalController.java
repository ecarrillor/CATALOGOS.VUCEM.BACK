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
import mx.gob.sat.catalogo.controller.request.patenteaduanal.CatPatenteAduanalRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.patenteaduanal.CatPatenteAduanalResponse;
import mx.gob.sat.catalogo.service.api.patenteaduanal.CatPatenteAduanalAPIService;
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

@Tag(name = "Cat. Patente Aduanal", description = "...")
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class CatPatenteAduanalController extends ApiBaseController {

    private final CatPatenteAduanalAPIService catPatenteAduanalAPIService;

    @Operation(operationId = "listar-patente-aduanal", summary = "Lista los registros Patente Aduanal paginados", description = "...")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = PaginaResponse.class)))
    })
    @GetMapping(value = UrlConst.PATENTE_ADUANAL_LISTAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<PaginaResponse<CatPatenteAduanalResponse>> listar(
            @RequestParam(defaultValue = "0") final int pagina,
            @RequestParam(defaultValue = "20") final int tamano,
            @RequestParam(required = false) final String busqueda,
            @RequestParam(required = false) final String sortBy,
            @RequestParam(required = false) final String sortDir) {
        return catPatenteAduanalAPIService.listar(pagina, tamano, busqueda, sortBy, sortDir);
    }

    @Operation(operationId = "crear-patente-aduanal", summary = "Crea un nuevo registro Patente Aduanal")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatPatenteAduanalResponse.class)))
    })
    @PostMapping(value = UrlConst.PATENTE_ADUANAL_CREAR, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatPatenteAduanalResponse> crear(@Valid @RequestBody final CatPatenteAduanalRequest request) {
        return catPatenteAduanalAPIService.crear(request);
    }

    @Operation(operationId = "buscar-patente-aduanal", summary = "Busca un registro Patente Aduanal por clave")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatPatenteAduanalResponse.class)))
    })
    @GetMapping(value = UrlConst.PATENTE_ADUANAL_BUSCAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatPatenteAduanalResponse> findById(
            @Parameter(description = "Clave Patente Aduanal") @PathVariable final String cvePatenteAduanal) {
        return catPatenteAduanalAPIService.findById(cvePatenteAduanal);
    }

    @Operation(operationId = "actualizar-patente-aduanal", summary = "Actualiza un registro Patente Aduanal")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatPatenteAduanalResponse.class)))
    })
    @PutMapping(value = UrlConst.PATENTE_ADUANAL_ACTUALIZAR, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatPatenteAduanalResponse> actualizar(
            @Parameter(description = "Clave Patente Aduanal") @PathVariable final String cvePatenteAduanal,
            @RequestBody final CatPatenteAduanalRequest request) {
        return catPatenteAduanalAPIService.actualizar(cvePatenteAduanal, request);
    }
}
