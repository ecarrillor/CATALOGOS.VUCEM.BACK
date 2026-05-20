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
import mx.gob.sat.catalogo.controller.request.tipotramite.CatTipoTramiteRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.tipotramite.CatTipoTramiteResponse;
import mx.gob.sat.catalogo.service.api.tipotramite.CatTipoTramiteAPIService;
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

@Tag(name = "Cat. Tipo Tramite", description = "Servicios de consulta y mantenimiento del catalogo Tipo Tramite")
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class CatTipoTramiteController extends ApiBaseController {

    private final CatTipoTramiteAPIService catTipoTramiteAPIService;

    @Operation(operationId = "listar-tipo-tramite", summary = "Lista los registros Tipo Tramite paginados", description = "Retorna una pagina de registros Tipo Tramite con soporte de busqueda y ordenamiento.")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA, content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = PaginaResponse.class))) })
    @GetMapping(value = UrlConst.TIPO_TRAMITE_LISTAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<PaginaResponse<CatTipoTramiteResponse>> listar(
            @RequestParam(defaultValue = "0") final int pagina,
            @RequestParam(defaultValue = "20") final int tamano,
            @RequestParam(required = false) final String busqueda,
            @RequestParam(required = false) final String sortBy,
            @RequestParam(required = false) final String sortDir) {
        return catTipoTramiteAPIService.listar(pagina, tamano, busqueda, sortBy, sortDir);
    }

    @Operation(operationId = "crear-tipo-tramite", summary = "Crea un nuevo registro Tipo Tramite", description = "Registra un nuevo registro Tipo Tramite en el catalogo.")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA, content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CatTipoTramiteResponse.class))) })
    @PostMapping(value = UrlConst.TIPO_TRAMITE_CREAR, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatTipoTramiteResponse> crear(@Valid @RequestBody final CatTipoTramiteRequest request) {
        return catTipoTramiteAPIService.crear(request);
    }

    @Operation(operationId = "buscar-tipo-tramite", summary = "Busca un registro Tipo Tramite por ID", description = "Retorna la informacion de un registro Tipo Tramite a partir de su ID.")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA, content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CatTipoTramiteResponse.class))) })
    @GetMapping(value = UrlConst.TIPO_TRAMITE_BUSCAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatTipoTramiteResponse> findById(@Parameter(description = "ID Tipo Tramite") @PathVariable final Long idTipoTramite) {
        return catTipoTramiteAPIService.findById(idTipoTramite);
    }

    @Operation(operationId = "actualizar-tipo-tramite", summary = "Actualiza un registro Tipo Tramite", description = "Actualiza los campos no nulos de un registro Tipo Tramite existente.")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA, content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CatTipoTramiteResponse.class))) })
    @PutMapping(value = UrlConst.TIPO_TRAMITE_ACTUALIZAR, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatTipoTramiteResponse> actualizar(@Parameter(description = "ID Tipo Tramite") @PathVariable final Long idTipoTramite, @RequestBody final CatTipoTramiteRequest request) {
        return catTipoTramiteAPIService.actualizar(idTipoTramite, request);
    }
}
