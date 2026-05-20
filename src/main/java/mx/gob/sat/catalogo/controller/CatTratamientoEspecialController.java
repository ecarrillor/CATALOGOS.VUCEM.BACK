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
import mx.gob.sat.catalogo.controller.request.tratamientoespecial.CatTratamientoEspecialRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.tratamientoespecial.CatTratamientoEspecialResponse;
import mx.gob.sat.catalogo.service.api.tratamientoespecial.CatTratamientoEspecialAPIService;
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
 * <b>Class:</b> CatTratamientoEspecialController.java <br>
 * <b>Description:</b>
 * <p>Controlador REST para el catalogo de tratamiento especial.
 * Expone endpoints bajo la ruta base {@code api/catalogo-admin/tratamiento-especial}.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Controlador
 */
@Tag(name = "Cat. Tratamiento Especial",
        description = "Servicios de consulta y mantenimiento del catalogo de tratamiento especial")
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class CatTratamientoEspecialController extends ApiBaseController {

    /** Servicio de negocio para el catalogo de tratamiento especial. */
    private final CatTratamientoEspecialAPIService catTratamientoEspecialAPIService;

    @Operation(operationId = "listar-tratamiento-especial", summary = "Lista los registros de tratamiento especial paginados",
        description = "Retorna una pagina de registros de tratamiento especial con soporte de busqueda y ordenamiento.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = PaginaResponse.class)))
    })
    @GetMapping(value = UrlConst.TRATAMIENTO_ESPECIAL_LISTAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<PaginaResponse<CatTratamientoEspecialResponse>> listar(
            @RequestParam(defaultValue = "0") final int pagina,
            @RequestParam(defaultValue = "20") final int tamano,
            @RequestParam(required = false) final String busqueda,
            @RequestParam(required = false) final String sortBy,
            @RequestParam(required = false) final String sortDir) {
        return catTratamientoEspecialAPIService.listar(pagina, tamano, busqueda, sortBy, sortDir);
    }

    @Operation(operationId = "crear-tratamiento-especial", summary = "Crea un nuevo registro de tratamiento especial")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatTratamientoEspecialResponse.class)))
    })
    @PostMapping(value = UrlConst.TRATAMIENTO_ESPECIAL_CREAR,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatTratamientoEspecialResponse> crear(@Valid @RequestBody final CatTratamientoEspecialRequest request) {
        return catTratamientoEspecialAPIService.crear(request);
    }

    @Operation(operationId = "buscar-tratamiento-especial", summary = "Busca un registro de tratamiento especial por identificador")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatTratamientoEspecialResponse.class)))
    })
    @GetMapping(value = UrlConst.TRATAMIENTO_ESPECIAL_BUSCAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatTratamientoEspecialResponse> findById(
            @Parameter(description = "Identificador del tratamiento especial") @PathVariable final Short idTratamientoEspecial) {
        return catTratamientoEspecialAPIService.findById(idTratamientoEspecial);
    }

    @Operation(operationId = "actualizar-tratamiento-especial", summary = "Actualiza un registro de tratamiento especial")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatTratamientoEspecialResponse.class)))
    })
    @PutMapping(value = UrlConst.TRATAMIENTO_ESPECIAL_ACTUALIZAR,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatTratamientoEspecialResponse> actualizar(
            @Parameter(description = "Identificador del tratamiento especial") @PathVariable final Short idTratamientoEspecial,
            @RequestBody final CatTratamientoEspecialRequest request) {
        return catTratamientoEspecialAPIService.actualizar(idTratamientoEspecial, request);
    }
}
