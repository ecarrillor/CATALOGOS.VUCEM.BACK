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
import mx.gob.sat.catalogo.controller.request.declaraciontramite.CatDeclaracionTramiteRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.declaraciontramite.CatDeclaracionTramiteResponse;
import mx.gob.sat.catalogo.service.api.declaraciontramite.CatDeclaracionTramiteAPIService;
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

@Tag(name = "Cat. Declaracion Tramite", description = "Operaciones sobre el catalogo de declaracion tramite")
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class CatDeclaracionTramiteController extends ApiBaseController {

    private final CatDeclaracionTramiteAPIService catDeclaracionTramiteAPIService;

    @Operation(operationId = "listar-declaracion-tramite", summary = "Lista los registros Declaracion Tramite paginados", description = "Retorna una pagina de registros del catalogo de declaracion tramite")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
        content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = PaginaResponse.class))) })
    @GetMapping(value = UrlConst.DECLARACION_TRAMITE_LISTAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<PaginaResponse<CatDeclaracionTramiteResponse>> listar(
            @RequestParam(defaultValue = "0") final int pagina,
            @RequestParam(defaultValue = "20") final int tamano,
            @RequestParam(required = false) final String busqueda,
            @RequestParam(required = false) final String sortBy,
            @RequestParam(required = false) final String sortDir) {
        return catDeclaracionTramiteAPIService.listar(pagina, tamano, busqueda, sortBy, sortDir);
    }

    @Operation(operationId = "crear-declaracion-tramite", summary = "Crea un nuevo registro Declaracion Tramite")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
        content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CatDeclaracionTramiteResponse.class))) })
    @PostMapping(value = UrlConst.DECLARACION_TRAMITE_CREAR, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatDeclaracionTramiteResponse> crear(@Valid @RequestBody final CatDeclaracionTramiteRequest request) {
        return catDeclaracionTramiteAPIService.crear(request);
    }

    @Operation(operationId = "buscar-declaracion-tramite", summary = "Busca un registro Declaracion Tramite por id compuesto")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
        content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CatDeclaracionTramiteResponse.class))) })
    @GetMapping(value = UrlConst.DECLARACION_TRAMITE_BUSCAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatDeclaracionTramiteResponse> findById(
            @Parameter(description = "Clave Declaracion") @PathVariable final String cveDeclaracion,
            @Parameter(description = "Id Tipo Tramite") @PathVariable final Long idTipoTramite) {
        return catDeclaracionTramiteAPIService.findById(cveDeclaracion, idTipoTramite);
    }

    @Operation(operationId = "actualizar-declaracion-tramite", summary = "Actualiza un registro Declaracion Tramite")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
        content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CatDeclaracionTramiteResponse.class))) })
    @PutMapping(value = UrlConst.DECLARACION_TRAMITE_ACTUALIZAR, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatDeclaracionTramiteResponse> actualizar(
            @Parameter(description = "Clave Declaracion") @PathVariable final String cveDeclaracion,
            @Parameter(description = "Id Tipo Tramite") @PathVariable final Long idTipoTramite,
            @RequestBody final CatDeclaracionTramiteRequest request) {
        return catDeclaracionTramiteAPIService.actualizar(cveDeclaracion, idTipoTramite, request);
    }
}
