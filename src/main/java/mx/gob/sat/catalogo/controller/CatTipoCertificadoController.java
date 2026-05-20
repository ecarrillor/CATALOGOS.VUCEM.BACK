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
import mx.gob.sat.catalogo.controller.request.tipocertificado.CatTipoCertificadoRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.tipocertificado.CatTipoCertificadoResponse;
import mx.gob.sat.catalogo.service.api.tipocertificado.CatTipoCertificadoAPIService;
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

@Tag(name = "Cat. Tipo Certificado", description = "...")
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class CatTipoCertificadoController extends ApiBaseController {

    private final CatTipoCertificadoAPIService catTipoCertificadoAPIService;

    @Operation(operationId = "listar-tipo-certificado", summary = "Lista los registros Tipo Certificado paginados", description = "...")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = PaginaResponse.class)))
    })
    @GetMapping(value = UrlConst.TIPO_CERTIFICADO_LISTAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<PaginaResponse<CatTipoCertificadoResponse>> listar(
            @RequestParam(defaultValue = "0") final int pagina,
            @RequestParam(defaultValue = "20") final int tamano,
            @RequestParam(required = false) final String busqueda,
            @RequestParam(required = false) final String sortBy,
            @RequestParam(required = false) final String sortDir) {
        return catTipoCertificadoAPIService.listar(pagina, tamano, busqueda, sortBy, sortDir);
    }

    @Operation(operationId = "crear-tipo-certificado", summary = "Crea un nuevo registro Tipo Certificado")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatTipoCertificadoResponse.class)))
    })
    @PostMapping(value = UrlConst.TIPO_CERTIFICADO_CREAR, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatTipoCertificadoResponse> crear(@Valid @RequestBody final CatTipoCertificadoRequest request) {
        return catTipoCertificadoAPIService.crear(request);
    }

    @Operation(operationId = "buscar-tipo-certificado", summary = "Busca un registro Tipo Certificado por id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatTipoCertificadoResponse.class)))
    })
    @GetMapping(value = UrlConst.TIPO_CERTIFICADO_BUSCAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatTipoCertificadoResponse> findById(
            @Parameter(description = "Id Tipo Certificado") @PathVariable final Long idTipoCertificado) {
        return catTipoCertificadoAPIService.findById(idTipoCertificado);
    }

    @Operation(operationId = "actualizar-tipo-certificado", summary = "Actualiza un registro Tipo Certificado")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatTipoCertificadoResponse.class)))
    })
    @PutMapping(value = UrlConst.TIPO_CERTIFICADO_ACTUALIZAR, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatTipoCertificadoResponse> actualizar(
            @Parameter(description = "Id Tipo Certificado") @PathVariable final Long idTipoCertificado,
            @RequestBody final CatTipoCertificadoRequest request) {
        return catTipoCertificadoAPIService.actualizar(idTipoCertificado, request);
    }
}
