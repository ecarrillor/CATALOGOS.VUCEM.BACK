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
import mx.gob.sat.catalogo.controller.request.identificadorprevalidador.CatIdentificadorPrevalidadorRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.identificadorprevalidador.CatIdentificadorPrevalidadorResponse;
import mx.gob.sat.catalogo.service.api.identificadorprevalidador.CatIdentificadorPrevalidadorAPIService;
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
 * <b>Class:</b> CatIdentificadorPrevalidadorController.java <br>
 * <b>Description:</b>
 * <p>Controlador REST para el catalogo Identificador Prevalidador.
 * Expone endpoints bajo la ruta base {@code api/catalogo-admin/identificador-prevalidador}.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Controlador
 */
@Tag(name = "Cat. Identificador Prevalidador",
        description = "Servicios de consulta y mantenimiento del catalogo Identificador Prevalidador")
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class CatIdentificadorPrevalidadorController extends ApiBaseController {

    private final CatIdentificadorPrevalidadorAPIService catIdentificadorPrevalidadorAPIService;

    @Operation(operationId = "listar-identificador-prevalidador", summary = "Lista los registros Identificador Prevalidador paginados",
        description = "Retorna una pagina de registros Identificador Prevalidador con soporte de busqueda y ordenamiento.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = PaginaResponse.class)))
    })
    @GetMapping(value = UrlConst.IDENTIFICADOR_PREVALIDADOR_LISTAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<PaginaResponse<CatIdentificadorPrevalidadorResponse>> listar(
            @RequestParam(defaultValue = "0") final int pagina,
            @RequestParam(defaultValue = "20") final int tamano,
            @RequestParam(required = false) final String busqueda,
            @RequestParam(required = false) final String sortBy,
            @RequestParam(required = false) final String sortDir) {
        return catIdentificadorPrevalidadorAPIService.listar(pagina, tamano, busqueda, sortBy, sortDir);
    }

    @Operation(operationId = "crear-identificador-prevalidador", summary = "Crea un nuevo registro Identificador Prevalidador")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatIdentificadorPrevalidadorResponse.class)))
    })
    @PostMapping(value = UrlConst.IDENTIFICADOR_PREVALIDADOR_CREAR,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatIdentificadorPrevalidadorResponse> crear(@Valid @RequestBody final CatIdentificadorPrevalidadorRequest request) {
        return catIdentificadorPrevalidadorAPIService.crear(request);
    }

    @Operation(operationId = "buscar-identificador-prevalidador", summary = "Busca un registro Identificador Prevalidador por id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatIdentificadorPrevalidadorResponse.class)))
    })
    @GetMapping(value = UrlConst.IDENTIFICADOR_PREVALIDADOR_BUSCAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatIdentificadorPrevalidadorResponse> findById(
            @Parameter(description = "Id Identificador Prevalidador") @PathVariable final Long idIdentificadorPrevalidador) {
        return catIdentificadorPrevalidadorAPIService.findById(idIdentificadorPrevalidador);
    }

    @Operation(operationId = "actualizar-identificador-prevalidador", summary = "Actualiza un registro Identificador Prevalidador")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatIdentificadorPrevalidadorResponse.class)))
    })
    @PutMapping(value = UrlConst.IDENTIFICADOR_PREVALIDADOR_ACTUALIZAR,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatIdentificadorPrevalidadorResponse> actualizar(
            @Parameter(description = "Id Identificador Prevalidador") @PathVariable final Long idIdentificadorPrevalidador,
            @RequestBody final CatIdentificadorPrevalidadorRequest request) {
        return catIdentificadorPrevalidadorAPIService.actualizar(idIdentificadorPrevalidador, request);
    }
}
