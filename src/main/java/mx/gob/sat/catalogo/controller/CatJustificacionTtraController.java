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
import mx.gob.sat.catalogo.controller.request.justificacionttra.CatJustificacionTtraRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.justificacionttra.CatJustificacionTtraResponse;
import mx.gob.sat.catalogo.service.api.justificacionttra.CatJustificacionTtraAPIService;
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

@Tag(name = "Cat. Justificacion Ttra", description = "...")
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class CatJustificacionTtraController extends ApiBaseController {

    private final CatJustificacionTtraAPIService catJustificacionTtraAPIService;

    @Operation(operationId = "listar-justificacion-ttra", summary = "Lista los registros Justificacion Ttra paginados", description = "...")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = PaginaResponse.class)))
    })
    @GetMapping(value = UrlConst.JUSTIFICACION_TTRA_LISTAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<PaginaResponse<CatJustificacionTtraResponse>> listar(
            @RequestParam(defaultValue = "0") final int pagina,
            @RequestParam(defaultValue = "20") final int tamano,
            @RequestParam(required = false) final String busqueda,
            @RequestParam(required = false) final String sortBy,
            @RequestParam(required = false) final String sortDir) {
        return catJustificacionTtraAPIService.listar(pagina, tamano, busqueda, sortBy, sortDir);
    }

    @Operation(operationId = "crear-justificacion-ttra", summary = "Crea un nuevo registro Justificacion Ttra")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatJustificacionTtraResponse.class)))
    })
    @PostMapping(value = UrlConst.JUSTIFICACION_TTRA_CREAR, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatJustificacionTtraResponse> crear(@Valid @RequestBody final CatJustificacionTtraRequest request) {
        return catJustificacionTtraAPIService.crear(request);
    }

    @Operation(operationId = "buscar-justificacion-ttra", summary = "Busca un registro Justificacion Ttra por id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatJustificacionTtraResponse.class)))
    })
    @GetMapping(value = UrlConst.JUSTIFICACION_TTRA_BUSCAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatJustificacionTtraResponse> findById(
            @Parameter(description = "Id Justificacion Ttra") @PathVariable final Long idJustificacionTtra) {
        return catJustificacionTtraAPIService.findById(idJustificacionTtra);
    }

    @Operation(operationId = "actualizar-justificacion-ttra", summary = "Actualiza un registro Justificacion Ttra")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatJustificacionTtraResponse.class)))
    })
    @PutMapping(value = UrlConst.JUSTIFICACION_TTRA_ACTUALIZAR, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatJustificacionTtraResponse> actualizar(
            @Parameter(description = "Id Justificacion Ttra") @PathVariable final Long idJustificacionTtra,
            @RequestBody final CatJustificacionTtraRequest request) {
        return catJustificacionTtraAPIService.actualizar(idJustificacionTtra, request);
    }
}
