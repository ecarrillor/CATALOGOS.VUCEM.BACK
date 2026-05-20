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
import mx.gob.sat.catalogo.controller.request.esquemaregla8.CatEsquemaRegla8Request;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.esquemaregla8.CatEsquemaRegla8Response;
import mx.gob.sat.catalogo.service.api.esquemaregla8.CatEsquemaRegla8APIService;
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
 * <b>Class:</b> CatEsquemaRegla8Controller.java <br>
 * <b>Description:</b>
 * <p>Controlador REST para el catalogo Esquema Regla 8.
 * Expone endpoints bajo la ruta base {@code api/catalogo-admin/esquema-regla8}.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Controlador
 */
@Tag(name = "Cat. Esquema Regla 8",
        description = "Servicios de consulta y mantenimiento del catalogo Esquema Regla 8")
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class CatEsquemaRegla8Controller extends ApiBaseController {

    private final CatEsquemaRegla8APIService catEsquemaRegla8APIService;

    @Operation(operationId = "listar-esquema-regla8", summary = "Lista los registros Esquema Regla 8 paginados",
        description = "Retorna una pagina de registros Esquema Regla 8 con soporte de busqueda y ordenamiento.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = PaginaResponse.class)))
    })
    @GetMapping(value = UrlConst.ESQUEMA_REGLA8_LISTAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<PaginaResponse<CatEsquemaRegla8Response>> listar(
            @RequestParam(defaultValue = "0") final int pagina,
            @RequestParam(defaultValue = "20") final int tamano,
            @RequestParam(required = false) final String busqueda,
            @RequestParam(required = false) final String sortBy,
            @RequestParam(required = false) final String sortDir) {
        return catEsquemaRegla8APIService.listar(pagina, tamano, busqueda, sortBy, sortDir);
    }

    @Operation(operationId = "crear-esquema-regla8", summary = "Crea un nuevo registro Esquema Regla 8")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatEsquemaRegla8Response.class)))
    })
    @PostMapping(value = UrlConst.ESQUEMA_REGLA8_CREAR,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatEsquemaRegla8Response> crear(@Valid @RequestBody final CatEsquemaRegla8Request request) {
        return catEsquemaRegla8APIService.crear(request);
    }

    @Operation(operationId = "buscar-esquema-regla8", summary = "Busca un registro Esquema Regla 8 por id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatEsquemaRegla8Response.class)))
    })
    @GetMapping(value = UrlConst.ESQUEMA_REGLA8_BUSCAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatEsquemaRegla8Response> findById(
            @Parameter(description = "Id Esquema Regla 8") @PathVariable final Short idEsquemaRegla8) {
        return catEsquemaRegla8APIService.findById(idEsquemaRegla8);
    }

    @Operation(operationId = "actualizar-esquema-regla8", summary = "Actualiza un registro Esquema Regla 8")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatEsquemaRegla8Response.class)))
    })
    @PutMapping(value = UrlConst.ESQUEMA_REGLA8_ACTUALIZAR,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatEsquemaRegla8Response> actualizar(
            @Parameter(description = "Id Esquema Regla 8") @PathVariable final Short idEsquemaRegla8,
            @RequestBody final CatEsquemaRegla8Request request) {
        return catEsquemaRegla8APIService.actualizar(idEsquemaRegla8, request);
    }
}
