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
import mx.gob.sat.catalogo.controller.request.dictamentramite.CatDictamenTramiteRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.dictamentramite.CatDictamenTramiteResponse;
import mx.gob.sat.catalogo.service.api.dictamentramite.CatDictamenTramiteAPIService;
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

@Tag(name = "Cat. Dictamen Tramite", description = "Operaciones sobre el catalogo de dictamen tramite")
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class CatDictamenTramiteController extends ApiBaseController {

    private final CatDictamenTramiteAPIService catDictamenTramiteAPIService;

    @Operation(operationId = "listar-dictamen-tramite", summary = "Lista los registros Dictamen Tramite paginados", description = "Retorna una pagina de registros del catalogo de dictamen tramite")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
        content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = PaginaResponse.class))) })
    @GetMapping(value = UrlConst.DICTAMEN_TRAMITE_LISTAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<PaginaResponse<CatDictamenTramiteResponse>> listar(
            @RequestParam(defaultValue = "0") final int pagina,
            @RequestParam(defaultValue = "20") final int tamano,
            @RequestParam(required = false) final String busqueda,
            @RequestParam(required = false) final String sortBy,
            @RequestParam(required = false) final String sortDir) {
        return catDictamenTramiteAPIService.listar(pagina, tamano, busqueda, sortBy, sortDir);
    }

    @Operation(operationId = "crear-dictamen-tramite", summary = "Crea un nuevo registro Dictamen Tramite")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
        content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CatDictamenTramiteResponse.class))) })
    @PostMapping(value = UrlConst.DICTAMEN_TRAMITE_CREAR, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatDictamenTramiteResponse> crear(@Valid @RequestBody final CatDictamenTramiteRequest request) {
        return catDictamenTramiteAPIService.crear(request);
    }

    @Operation(operationId = "buscar-dictamen-tramite", summary = "Busca un registro Dictamen Tramite por id compuesto")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
        content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CatDictamenTramiteResponse.class))) })
    @GetMapping(value = UrlConst.DICTAMEN_TRAMITE_BUSCAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatDictamenTramiteResponse> findById(
            @Parameter(description = "Id Tipo Tramite") @PathVariable final Long idTipoTramite,
            @Parameter(description = "Id Tipo Dictamen") @PathVariable final Long idTipoDictamen) {
        return catDictamenTramiteAPIService.findById(idTipoTramite, idTipoDictamen);
    }

    @Operation(operationId = "actualizar-dictamen-tramite", summary = "Actualiza un registro Dictamen Tramite")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
        content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CatDictamenTramiteResponse.class))) })
    @PutMapping(value = UrlConst.DICTAMEN_TRAMITE_ACTUALIZAR, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatDictamenTramiteResponse> actualizar(
            @Parameter(description = "Id Tipo Tramite") @PathVariable final Long idTipoTramite,
            @Parameter(description = "Id Tipo Dictamen") @PathVariable final Long idTipoDictamen,
            @RequestBody final CatDictamenTramiteRequest request) {
        return catDictamenTramiteAPIService.actualizar(idTipoTramite, idTipoDictamen, request);
    }
}
