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
import mx.gob.sat.catalogo.controller.request.plazomaximoauttramite.CatPlazoMaximoAutTramiteRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.plazomaximoauttramite.CatPlazoMaximoAutTramiteResponse;
import mx.gob.sat.catalogo.service.api.plazomaximoauttramite.CatPlazoMaximoAutTramiteAPIService;
import mx.gob.sat.catalogo.util.consts.UrlConst;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;

@Tag(name = "Cat. Plazo Maximo Aut Tramite", description = "Operaciones sobre el catalogo de plazo maximo de autorizacion de tramite")
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class CatPlazoMaximoAutTramiteController extends ApiBaseController {

    private final CatPlazoMaximoAutTramiteAPIService catPlazoMaximoAutTramiteAPIService;

    @Operation(operationId = "listar-plazo-maximo-aut-tramite", summary = "Lista los registros Plazo Maximo Aut Tramite paginados", description = "Retorna una pagina de registros del catalogo de plazo maximo de autorizacion de tramite")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
        content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = PaginaResponse.class))) })
    @GetMapping(value = UrlConst.PLAZO_MAXIMO_AUT_TRAMITE_LISTAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<PaginaResponse<CatPlazoMaximoAutTramiteResponse>> listar(
            @RequestParam(defaultValue = "0") final int pagina,
            @RequestParam(defaultValue = "20") final int tamano,
            @RequestParam(required = false) final String busqueda,
            @RequestParam(required = false) final String sortBy,
            @RequestParam(required = false) final String sortDir) {
        return catPlazoMaximoAutTramiteAPIService.listar(pagina, tamano, busqueda, sortBy, sortDir);
    }

    @Operation(operationId = "crear-plazo-maximo-aut-tramite", summary = "Crea un nuevo registro Plazo Maximo Aut Tramite")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
        content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CatPlazoMaximoAutTramiteResponse.class))) })
    @PostMapping(value = UrlConst.PLAZO_MAXIMO_AUT_TRAMITE_CREAR, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatPlazoMaximoAutTramiteResponse> crear(@Valid @RequestBody final CatPlazoMaximoAutTramiteRequest request) {
        return catPlazoMaximoAutTramiteAPIService.crear(request);
    }

    @Operation(operationId = "buscar-plazo-maximo-aut-tramite", summary = "Busca un registro Plazo Maximo Aut Tramite por id")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
        content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CatPlazoMaximoAutTramiteResponse.class))) })
    @GetMapping(value = UrlConst.PLAZO_MAXIMO_AUT_TRAMITE_BUSCAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatPlazoMaximoAutTramiteResponse> findById(
            @Parameter(description = "Identificador del tipo de tramite") @PathVariable final Long idTipoTramite,
            @Parameter(description = "Fecha inicio vigencia") @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final LocalDate fecIniVigencia) {
        return catPlazoMaximoAutTramiteAPIService.findById(idTipoTramite, fecIniVigencia);
    }

    @Operation(operationId = "actualizar-plazo-maximo-aut-tramite", summary = "Actualiza un registro Plazo Maximo Aut Tramite")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
        content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CatPlazoMaximoAutTramiteResponse.class))) })
    @PutMapping(value = UrlConst.PLAZO_MAXIMO_AUT_TRAMITE_ACTUALIZAR, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatPlazoMaximoAutTramiteResponse> actualizar(
            @Parameter(description = "Identificador del tipo de tramite") @PathVariable final Long idTipoTramite,
            @Parameter(description = "Fecha inicio vigencia") @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final LocalDate fecIniVigencia,
            @RequestBody final CatPlazoMaximoAutTramiteRequest request) {
        return catPlazoMaximoAutTramiteAPIService.actualizar(idTipoTramite, fecIniVigencia, request);
    }
}
