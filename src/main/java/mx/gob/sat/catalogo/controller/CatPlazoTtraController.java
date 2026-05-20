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
import mx.gob.sat.catalogo.controller.request.plazottra.CatPlazoTtraRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.plazottra.CatPlazoTtraResponse;
import mx.gob.sat.catalogo.service.api.plazottra.CatPlazoTtraAPIService;
import mx.gob.sat.catalogo.util.consts.UrlConst;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Cat. Plazo Ttra", description = "Operaciones sobre el catalogo de plazo ttra")
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class CatPlazoTtraController extends ApiBaseController {

    private final CatPlazoTtraAPIService catPlazoTtraAPIService;

    @Operation(operationId = "listar-plazo-ttra", summary = "Lista los registros Plazo Ttra paginados", description = "Retorna una pagina de registros del catalogo de plazo ttra")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
        content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = PaginaResponse.class))) })
    @GetMapping(value = UrlConst.PLAZO_TTRA_LISTAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<PaginaResponse<CatPlazoTtraResponse>> listar(
            @RequestParam(defaultValue = "0") final int pagina,
            @RequestParam(defaultValue = "20") final int tamano,
            @RequestParam(required = false) final String busqueda,
            @RequestParam(required = false) final String sortBy,
            @RequestParam(required = false) final String sortDir) {
        return catPlazoTtraAPIService.listar(pagina, tamano, busqueda, sortBy, sortDir);
    }

    @Operation(operationId = "crear-plazo-ttra", summary = "Crea un nuevo registro Plazo Ttra")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
        content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CatPlazoTtraResponse.class))) })
    @PostMapping(value = UrlConst.PLAZO_TTRA_CREAR, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatPlazoTtraResponse> crear(@Valid @RequestBody final CatPlazoTtraRequest request) {
        return catPlazoTtraAPIService.crear(request);
    }

    @Operation(operationId = "buscar-plazo-ttra", summary = "Busca un registro Plazo Ttra por id")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
        content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CatPlazoTtraResponse.class))) })
    @GetMapping(value = UrlConst.PLAZO_TTRA_BUSCAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatPlazoTtraResponse> findById(
            @Parameter(description = "Identificador del tipo de tramite") @PathVariable final Long idTipoTramite,
            @Parameter(description = "Identificador del plazo de vigencia") @PathVariable final String idePlazoVigencia) {
        return catPlazoTtraAPIService.findById(idTipoTramite, idePlazoVigencia);
    }

    @Operation(operationId = "actualizar-plazo-ttra", summary = "Actualiza un registro Plazo Ttra")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
        content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CatPlazoTtraResponse.class))) })
    @PutMapping(value = UrlConst.PLAZO_TTRA_ACTUALIZAR, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatPlazoTtraResponse> actualizar(
            @Parameter(description = "Identificador del tipo de tramite") @PathVariable final Long idTipoTramite,
            @Parameter(description = "Identificador del plazo de vigencia") @PathVariable final String idePlazoVigencia,
            @RequestBody final CatPlazoTtraRequest request) {
        return catPlazoTtraAPIService.actualizar(idTipoTramite, idePlazoVigencia, request);
    }
}
