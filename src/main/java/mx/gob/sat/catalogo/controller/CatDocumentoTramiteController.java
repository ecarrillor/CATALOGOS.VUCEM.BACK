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
import mx.gob.sat.catalogo.controller.request.documentotramite.CatDocumentoTramiteRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.documentotramite.CatDocumentoTramiteResponse;
import mx.gob.sat.catalogo.service.api.documentotramite.CatDocumentoTramiteAPIService;
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

@Tag(name = "Cat. Documento Tramite", description = "Operaciones sobre el catalogo de documento tramite")
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class CatDocumentoTramiteController extends ApiBaseController {

    private final CatDocumentoTramiteAPIService catDocumentoTramiteAPIService;

    @Operation(operationId = "listar-documento-tramite", summary = "Lista los registros Documento Tramite paginados", description = "Retorna una pagina de registros del catalogo de documento tramite")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
        content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = PaginaResponse.class))) })
    @GetMapping(value = UrlConst.DOCUMENTO_TRAMITE_LISTAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<PaginaResponse<CatDocumentoTramiteResponse>> listar(
            @RequestParam(defaultValue = "0") final int pagina,
            @RequestParam(defaultValue = "20") final int tamano,
            @RequestParam(required = false) final String busqueda,
            @RequestParam(required = false) final String sortBy,
            @RequestParam(required = false) final String sortDir) {
        return catDocumentoTramiteAPIService.listar(pagina, tamano, busqueda, sortBy, sortDir);
    }

    @Operation(operationId = "crear-documento-tramite", summary = "Crea un nuevo registro Documento Tramite")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
        content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CatDocumentoTramiteResponse.class))) })
    @PostMapping(value = UrlConst.DOCUMENTO_TRAMITE_CREAR, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatDocumentoTramiteResponse> crear(@Valid @RequestBody final CatDocumentoTramiteRequest request) {
        return catDocumentoTramiteAPIService.crear(request);
    }

    @Operation(operationId = "buscar-documento-tramite", summary = "Busca un registro Documento Tramite por id compuesto")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
        content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CatDocumentoTramiteResponse.class))) })
    @GetMapping(value = UrlConst.DOCUMENTO_TRAMITE_BUSCAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatDocumentoTramiteResponse> findById(
            @Parameter(description = "Id Tipo Doc") @PathVariable final Short idTipoDoc,
            @Parameter(description = "Id Tipo Tramite") @PathVariable final Long idTipoTramite) {
        return catDocumentoTramiteAPIService.findById(idTipoDoc, idTipoTramite);
    }

    @Operation(operationId = "actualizar-documento-tramite", summary = "Actualiza un registro Documento Tramite")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
        content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CatDocumentoTramiteResponse.class))) })
    @PutMapping(value = UrlConst.DOCUMENTO_TRAMITE_ACTUALIZAR, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatDocumentoTramiteResponse> actualizar(
            @Parameter(description = "Id Tipo Doc") @PathVariable final Short idTipoDoc,
            @Parameter(description = "Id Tipo Tramite") @PathVariable final Long idTipoTramite,
            @RequestBody final CatDocumentoTramiteRequest request) {
        return catDocumentoTramiteAPIService.actualizar(idTipoDoc, idTipoTramite, request);
    }
}
