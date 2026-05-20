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
import mx.gob.sat.catalogo.controller.request.paistratadoacuerdo.CatPaisTratadoAcuerdoRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.paistratadoacuerdo.CatPaisTratadoAcuerdoResponse;
import mx.gob.sat.catalogo.service.api.paistratadoacuerdo.CatPaisTratadoAcuerdoAPIService;
import mx.gob.sat.catalogo.util.consts.UrlConst;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador para el catalogo de pais tratado acuerdo.
 *
 * @author Javier Chávez Barrios
 * @since 18 de mayo del 2026
 */
@Tag(name = "Cat. Pais Tratado Acuerdo", description = "Operaciones sobre el catalogo de pais tratado acuerdo")
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class CatPaisTratadoAcuerdoController extends ApiBaseController {

    private final CatPaisTratadoAcuerdoAPIService catPaisTratadoAcuerdoAPIService;

    @Operation(operationId = "listar-pais-tratado-acuerdo", summary = "Lista los registros Pais Tratado Acuerdo paginados", description = "Retorna una pagina de registros del catalogo de pais tratado acuerdo")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
        content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = PaginaResponse.class))) })
    @GetMapping(value = UrlConst.PAIS_TRATADO_ACUERDO_LISTAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<PaginaResponse<CatPaisTratadoAcuerdoResponse>> listar(
            @RequestParam(defaultValue = "0") final int pagina,
            @RequestParam(defaultValue = "20") final int tamano,
            @RequestParam(required = false) final String busqueda,
            @RequestParam(required = false) final String sortBy,
            @RequestParam(required = false) final String sortDir) {
        return catPaisTratadoAcuerdoAPIService.listar(pagina, tamano, busqueda, sortBy, sortDir);
    }

    @Operation(operationId = "crear-pais-tratado-acuerdo", summary = "Crea un nuevo registro Pais Tratado Acuerdo")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
        content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CatPaisTratadoAcuerdoResponse.class))) })
    @PostMapping(value = UrlConst.PAIS_TRATADO_ACUERDO_CREAR, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatPaisTratadoAcuerdoResponse> crear(@Valid @RequestBody final CatPaisTratadoAcuerdoRequest request) {
        return catPaisTratadoAcuerdoAPIService.crear(request);
    }

    @Operation(operationId = "buscar-pais-tratado-acuerdo", summary = "Busca un registro Pais Tratado Acuerdo por id")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
        content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CatPaisTratadoAcuerdoResponse.class))) })
    @GetMapping(value = UrlConst.PAIS_TRATADO_ACUERDO_BUSCAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatPaisTratadoAcuerdoResponse> findById(
            @Parameter(description = "Clave Pais") @PathVariable final String cvePais,
            @Parameter(description = "Id Tratado Acuerdo") @PathVariable final Short idTratadoAcuerdo) {
        return catPaisTratadoAcuerdoAPIService.findById(cvePais, idTratadoAcuerdo);
    }

    @Operation(operationId = "actualizar-pais-tratado-acuerdo", summary = "Actualiza un registro Pais Tratado Acuerdo")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
        content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CatPaisTratadoAcuerdoResponse.class))) })
    @PutMapping(value = UrlConst.PAIS_TRATADO_ACUERDO_ACTUALIZAR, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatPaisTratadoAcuerdoResponse> actualizar(
            @Parameter(description = "Clave Pais") @PathVariable final String cvePais,
            @Parameter(description = "Id Tratado Acuerdo") @PathVariable final Short idTratadoAcuerdo,
            @RequestBody final CatPaisTratadoAcuerdoRequest request) {
        return catPaisTratadoAcuerdoAPIService.actualizar(cvePais, idTratadoAcuerdo, request);
    }
}
