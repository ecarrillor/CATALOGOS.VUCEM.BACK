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
import mx.gob.sat.catalogo.controller.request.tratadobloquepai.CatTratadoBloquePaiRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.tratadobloquepai.CatTratadoBloquePaiResponse;
import mx.gob.sat.catalogo.service.api.tratadobloquepai.CatTratadoBloquePaiAPIService;
import mx.gob.sat.catalogo.util.consts.UrlConst;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador para el catalogo de tratado bloque pais.
 *
 * @author Javier Chávez Barrios
 * @since 18 de mayo del 2026
 */
@Tag(name = "Cat. Tratado Bloque Pais", description = "Operaciones sobre el catalogo de tratado bloque pais")
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class CatTratadoBloquePaiController extends ApiBaseController {

    private final CatTratadoBloquePaiAPIService catTratadoBloquePaiAPIService;

    @Operation(operationId = "listar-tratado-bloque-pai", summary = "Lista los registros Tratado Bloque Pais paginados", description = "Retorna una pagina de registros del catalogo de tratado bloque pais")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
        content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = PaginaResponse.class))) })
    @GetMapping(value = UrlConst.TRATADO_BLOQUE_PAI_LISTAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<PaginaResponse<CatTratadoBloquePaiResponse>> listar(
            @RequestParam(defaultValue = "0") final int pagina,
            @RequestParam(defaultValue = "20") final int tamano,
            @RequestParam(required = false) final String busqueda,
            @RequestParam(required = false) final String sortBy,
            @RequestParam(required = false) final String sortDir) {
        return catTratadoBloquePaiAPIService.listar(pagina, tamano, busqueda, sortBy, sortDir);
    }

    @Operation(operationId = "crear-tratado-bloque-pai", summary = "Crea un nuevo registro Tratado Bloque Pais")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
        content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CatTratadoBloquePaiResponse.class))) })
    @PostMapping(value = UrlConst.TRATADO_BLOQUE_PAI_CREAR, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatTratadoBloquePaiResponse> crear(@Valid @RequestBody final CatTratadoBloquePaiRequest request) {
        return catTratadoBloquePaiAPIService.crear(request);
    }

    @Operation(operationId = "buscar-tratado-bloque-pai", summary = "Busca un registro Tratado Bloque Pais por id")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
        content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CatTratadoBloquePaiResponse.class))) })
    @GetMapping(value = UrlConst.TRATADO_BLOQUE_PAI_BUSCAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatTratadoBloquePaiResponse> findById(
            @Parameter(description = "Clave Pais") @PathVariable final String cvePais,
            @Parameter(description = "Id Tratado Acuerdo") @PathVariable final Short idTratadoAcuerdo) {
        return catTratadoBloquePaiAPIService.findById(cvePais, idTratadoAcuerdo);
    }

    @Operation(operationId = "actualizar-tratado-bloque-pai", summary = "Actualiza un registro Tratado Bloque Pais")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
        content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CatTratadoBloquePaiResponse.class))) })
    @PutMapping(value = UrlConst.TRATADO_BLOQUE_PAI_ACTUALIZAR, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatTratadoBloquePaiResponse> actualizar(
            @Parameter(description = "Clave Pais") @PathVariable final String cvePais,
            @Parameter(description = "Id Tratado Acuerdo") @PathVariable final Short idTratadoAcuerdo,
            @RequestBody final CatTratadoBloquePaiRequest request) {
        return catTratadoBloquePaiAPIService.actualizar(cvePais, idTratadoAcuerdo, request);
    }
}
