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
import mx.gob.sat.catalogo.controller.request.especie.CatEspecieRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.especie.CatEspecieResponse;
import mx.gob.sat.catalogo.service.api.especie.CatEspecieAPIService;
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

@Tag(name = "Cat. Especie", description = "...")
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class CatEspecieController extends ApiBaseController {

    private final CatEspecieAPIService catEspecieAPIService;

    @Operation(operationId = "listar-especie", summary = "Lista los registros Especie paginados", description = "...")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = PaginaResponse.class)))
    })
    @GetMapping(value = UrlConst.ESPECIE_LISTAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<PaginaResponse<CatEspecieResponse>> listar(
            @RequestParam(defaultValue = "0") final int pagina,
            @RequestParam(defaultValue = "20") final int tamano,
            @RequestParam(required = false) final String busqueda,
            @RequestParam(required = false) final String sortBy,
            @RequestParam(required = false) final String sortDir) {
        return catEspecieAPIService.listar(pagina, tamano, busqueda, sortBy, sortDir);
    }

    @Operation(operationId = "crear-especie", summary = "Crea un nuevo registro Especie")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatEspecieResponse.class)))
    })
    @PostMapping(value = UrlConst.ESPECIE_CREAR, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatEspecieResponse> crear(@Valid @RequestBody final CatEspecieRequest request) {
        return catEspecieAPIService.crear(request);
    }

    @Operation(operationId = "buscar-especie", summary = "Busca un registro Especie por id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatEspecieResponse.class)))
    })
    @GetMapping(value = UrlConst.ESPECIE_BUSCAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatEspecieResponse> findById(
            @Parameter(description = "Id Especie") @PathVariable final Integer idEspecie) {
        return catEspecieAPIService.findById(idEspecie);
    }

    @Operation(operationId = "actualizar-especie", summary = "Actualiza un registro Especie")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatEspecieResponse.class)))
    })
    @PutMapping(value = UrlConst.ESPECIE_ACTUALIZAR, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatEspecieResponse> actualizar(
            @Parameter(description = "Id Especie") @PathVariable final Integer idEspecie,
            @RequestBody final CatEspecieRequest request) {
        return catEspecieAPIService.actualizar(idEspecie, request);
    }
}
