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
import mx.gob.sat.catalogo.controller.request.tratadobloque.CatTratadoBloqueRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.tratadobloque.CatTratadoBloqueResponse;
import mx.gob.sat.catalogo.service.api.tratadobloque.CatTratadoBloqueAPIService;
import mx.gob.sat.catalogo.util.consts.UrlConst;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador para el catalogo de tratado bloque.
 *
 * @author Javier Chávez Barrios
 * @since 18 de mayo del 2026
 */
@Tag(name = "Cat. Tratado Bloque", description = "Operaciones sobre el catalogo de tratado bloque")
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class CatTratadoBloqueController extends ApiBaseController {

    private final CatTratadoBloqueAPIService catTratadoBloqueAPIService;

    @Operation(operationId = "listar-tratado-bloque", summary = "Lista los registros Tratado Bloque paginados", description = "Retorna una pagina de registros del catalogo de tratado bloque")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
        content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = PaginaResponse.class))) })
    @GetMapping(value = UrlConst.TRATADO_BLOQUE_LISTAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<PaginaResponse<CatTratadoBloqueResponse>> listar(
            @RequestParam(defaultValue = "0") final int pagina,
            @RequestParam(defaultValue = "20") final int tamano,
            @RequestParam(required = false) final String busqueda,
            @RequestParam(required = false) final String sortBy,
            @RequestParam(required = false) final String sortDir) {
        return catTratadoBloqueAPIService.listar(pagina, tamano, busqueda, sortBy, sortDir);
    }

    @Operation(operationId = "crear-tratado-bloque", summary = "Crea un nuevo registro Tratado Bloque")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
        content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CatTratadoBloqueResponse.class))) })
    @PostMapping(value = UrlConst.TRATADO_BLOQUE_CREAR, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatTratadoBloqueResponse> crear(@Valid @RequestBody final CatTratadoBloqueRequest request) {
        return catTratadoBloqueAPIService.crear(request);
    }

    @Operation(operationId = "buscar-tratado-bloque", summary = "Busca un registro Tratado Bloque por id")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
        content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CatTratadoBloqueResponse.class))) })
    @GetMapping(value = UrlConst.TRATADO_BLOQUE_BUSCAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatTratadoBloqueResponse> findById(
            @Parameter(description = "Id Tratado Acuerdo") @PathVariable final Short idTratadoAcuerdo,
            @Parameter(description = "Id Bloque") @PathVariable final Short idBloque) {
        return catTratadoBloqueAPIService.findById(idTratadoAcuerdo, idBloque);
    }

    @Operation(operationId = "actualizar-tratado-bloque", summary = "Actualiza un registro Tratado Bloque")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
        content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CatTratadoBloqueResponse.class))) })
    @PutMapping(value = UrlConst.TRATADO_BLOQUE_ACTUALIZAR, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatTratadoBloqueResponse> actualizar(
            @Parameter(description = "Id Tratado Acuerdo") @PathVariable final Short idTratadoAcuerdo,
            @Parameter(description = "Id Bloque") @PathVariable final Short idBloque,
            @RequestBody final CatTratadoBloqueRequest request) {
        return catTratadoBloqueAPIService.actualizar(idTratadoAcuerdo, idBloque, request);
    }
}
