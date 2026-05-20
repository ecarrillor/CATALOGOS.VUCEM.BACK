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
import mx.gob.sat.catalogo.controller.request.combinacionsg.CatCombinacionSgRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.combinacionsg.CatCombinacionSgResponse;
import mx.gob.sat.catalogo.service.api.combinacionsg.CatCombinacionSgAPIService;
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

@Tag(name = "Cat. Combinacion SG", description = "...")
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class CatCombinacionSgController extends ApiBaseController {

    private final CatCombinacionSgAPIService catCombinacionSgAPIService;

    @Operation(operationId = "listar-combinacion-sg", summary = "Lista los registros Combinacion SG paginados")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = PaginaResponse.class)))
    })
    @GetMapping(value = UrlConst.COMBINACION_SG_LISTAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<PaginaResponse<CatCombinacionSgResponse>> listar(
            @RequestParam(defaultValue = "0") final int pagina,
            @RequestParam(defaultValue = "20") final int tamano,
            @RequestParam(required = false) final String busqueda,
            @RequestParam(required = false) final String sortBy,
            @RequestParam(required = false) final String sortDir) {
        return catCombinacionSgAPIService.listar(pagina, tamano, busqueda, sortBy, sortDir);
    }

    @Operation(operationId = "crear-combinacion-sg", summary = "Crea un nuevo registro Combinacion SG")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatCombinacionSgResponse.class)))
    })
    @PostMapping(value = UrlConst.COMBINACION_SG_CREAR, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatCombinacionSgResponse> crear(@Valid @RequestBody final CatCombinacionSgRequest request) {
        return catCombinacionSgAPIService.crear(request);
    }

    @Operation(operationId = "buscar-combinacion-sg", summary = "Busca un registro Combinacion SG por id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatCombinacionSgResponse.class)))
    })
    @GetMapping(value = UrlConst.COMBINACION_SG_BUSCAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatCombinacionSgResponse> findById(
            @Parameter(description = "Id Combinacion SG") @PathVariable final Long idCombinacionSg) {
        return catCombinacionSgAPIService.findById(idCombinacionSg);
    }

    @Operation(operationId = "actualizar-combinacion-sg", summary = "Actualiza un registro Combinacion SG")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatCombinacionSgResponse.class)))
    })
    @PutMapping(value = UrlConst.COMBINACION_SG_ACTUALIZAR, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatCombinacionSgResponse> actualizar(
            @Parameter(description = "Id Combinacion SG") @PathVariable final Long idCombinacionSg,
            @RequestBody final CatCombinacionSgRequest request) {
        return catCombinacionSgAPIService.actualizar(idCombinacionSg, request);
    }
}
