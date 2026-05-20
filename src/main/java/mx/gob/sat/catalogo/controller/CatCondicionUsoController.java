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
import mx.gob.sat.catalogo.controller.request.condicionuso.CatCondicionUsoRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.condicionuso.CatCondicionUsoResponse;
import mx.gob.sat.catalogo.service.api.condicionuso.CatCondicionUsoAPIService;
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
 * <b>Class:</b> CatCondicionUsoController.java <br>
 * <b>Description:</b>
 * <p>Controlador REST para el catalogo de condicion uso.
 * Expone endpoints bajo la ruta base {@code api/catalogo-admin/condicion-uso}.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Controlador
 */
@Tag(name = "Cat. Condicion Uso",
        description = "Servicios de consulta y mantenimiento del catalogo de condicion uso")
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class CatCondicionUsoController extends ApiBaseController {

    /** Servicio de negocio para el catalogo de condicion uso. */
    private final CatCondicionUsoAPIService catCondicionUsoAPIService;

    @Operation(operationId = "listar-condicion-uso", summary = "Lista los registros de condicion uso paginados",
        description = "Retorna una pagina de registros de condicion uso con soporte de busqueda y ordenamiento.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = PaginaResponse.class)))
    })
    @GetMapping(value = UrlConst.CONDICION_USO_LISTAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<PaginaResponse<CatCondicionUsoResponse>> listar(
            @RequestParam(defaultValue = "0") final int pagina,
            @RequestParam(defaultValue = "20") final int tamano,
            @RequestParam(required = false) final String busqueda,
            @RequestParam(required = false) final String sortBy,
            @RequestParam(required = false) final String sortDir) {
        return catCondicionUsoAPIService.listar(pagina, tamano, busqueda, sortBy, sortDir);
    }

    @Operation(operationId = "crear-condicion-uso", summary = "Crea un nuevo registro de condicion uso")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatCondicionUsoResponse.class)))
    })
    @PostMapping(value = UrlConst.CONDICION_USO_CREAR,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatCondicionUsoResponse> crear(@Valid @RequestBody final CatCondicionUsoRequest request) {
        return catCondicionUsoAPIService.crear(request);
    }

    @Operation(operationId = "buscar-condicion-uso", summary = "Busca un registro de condicion uso por identificador")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatCondicionUsoResponse.class)))
    })
    @GetMapping(value = UrlConst.CONDICION_USO_BUSCAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatCondicionUsoResponse> findById(
            @Parameter(description = "Identificador de condicion uso") @PathVariable final Short idCondicionUso) {
        return catCondicionUsoAPIService.findById(idCondicionUso);
    }

    @Operation(operationId = "actualizar-condicion-uso", summary = "Actualiza un registro de condicion uso")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatCondicionUsoResponse.class)))
    })
    @PutMapping(value = UrlConst.CONDICION_USO_ACTUALIZAR,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatCondicionUsoResponse> actualizar(
            @Parameter(description = "Identificador de condicion uso") @PathVariable final Short idCondicionUso,
            @RequestBody final CatCondicionUsoRequest request) {
        return catCondicionUsoAPIService.actualizar(idCondicionUso, request);
    }
}
