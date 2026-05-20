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
import mx.gob.sat.catalogo.controller.request.tipodocumento.CatTipoDocumentoRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.tipodocumento.CatTipoDocumentoResponse;
import mx.gob.sat.catalogo.service.api.tipodocumento.CatTipoDocumentoAPIService;
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

/**
 * <b>Class:</b> CatTipoDocumentoController.java <br>
 * <b>Description:</b>
 * <p>Controlador REST para el catalogo Tipo Documento.
 * Expone endpoints bajo la ruta base {@code api/catalogo-admin/tipo-documento}.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Controlador
 */
@Tag(name = "Cat. Tipo Documento",
        description = "Servicios de consulta y mantenimiento del catalogo Tipo Documento")
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class CatTipoDocumentoController extends ApiBaseController {

    private final CatTipoDocumentoAPIService catTipoDocumentoAPIService;

    @Operation(operationId = "listar-tipo-documento", summary = "Lista los registros Tipo Documento paginados",
        description = "Retorna una pagina de registros Tipo Documento con soporte de busqueda y ordenamiento.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = PaginaResponse.class)))
    })
    @GetMapping(value = UrlConst.TIPO_DOCUMENTO_LISTAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<PaginaResponse<CatTipoDocumentoResponse>> listar(
            @RequestParam(defaultValue = "0") final int pagina,
            @RequestParam(defaultValue = "20") final int tamano,
            @RequestParam(required = false) final String busqueda,
            @RequestParam(required = false) final String sortBy,
            @RequestParam(required = false) final String sortDir) {
        return catTipoDocumentoAPIService.listar(pagina, tamano, busqueda, sortBy, sortDir);
    }

    @Operation(operationId = "crear-tipo-documento", summary = "Crea un nuevo registro Tipo Documento")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatTipoDocumentoResponse.class)))
    })
    @PostMapping(value = UrlConst.TIPO_DOCUMENTO_CREAR,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatTipoDocumentoResponse> crear(@Valid @RequestBody final CatTipoDocumentoRequest request) {
        return catTipoDocumentoAPIService.crear(request);
    }

    @Operation(operationId = "buscar-tipo-documento", summary = "Busca un registro Tipo Documento por id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatTipoDocumentoResponse.class)))
    })
    @GetMapping(value = UrlConst.TIPO_DOCUMENTO_BUSCAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatTipoDocumentoResponse> findById(
            @Parameter(description = "Id Tipo Documento") @PathVariable final Short idTipoDocumento) {
        return catTipoDocumentoAPIService.findById(idTipoDocumento);
    }

    @Operation(operationId = "actualizar-tipo-documento", summary = "Actualiza un registro Tipo Documento")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatTipoDocumentoResponse.class)))
    })
    @PutMapping(value = UrlConst.TIPO_DOCUMENTO_ACTUALIZAR,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatTipoDocumentoResponse> actualizar(
            @Parameter(description = "Id Tipo Documento") @PathVariable final Short idTipoDocumento,
            @RequestBody final CatTipoDocumentoRequest request) {
        return catTipoDocumentoAPIService.actualizar(idTipoDocumento, request);
    }
}
