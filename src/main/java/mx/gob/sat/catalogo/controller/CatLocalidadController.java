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
import mx.gob.sat.catalogo.controller.request.localidad.CatLocalidadRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.localidad.CatLocalidadResponse;
import mx.gob.sat.catalogo.service.api.localidad.CatLocalidadAPIService;
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
 * <b>Class:</b> CatLocalidadController.java <br>
 * <b>Description:</b>
 * <p>Controlador REST para el catalogo de localidad.
 * Expone endpoints bajo la ruta base {@code api/catalogo-admin/localidad}.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Controlador
 */
@Tag(name = "Cat. Localidad",
        description = "Servicios de consulta y mantenimiento del catalogo de localidad")
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class CatLocalidadController extends ApiBaseController {

    /** Servicio de negocio para el catalogo de localidad. */
    private final CatLocalidadAPIService catLocalidadAPIService;

    @Operation(operationId = "listar-localidad", summary = "Lista los registros de localidad paginados",
        description = "Retorna una pagina de registros de localidad con soporte de busqueda y ordenamiento.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = PaginaResponse.class)))
    })
    @GetMapping(value = UrlConst.LOCALIDAD_LISTAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<PaginaResponse<CatLocalidadResponse>> listar(
            @RequestParam(defaultValue = "0") final int pagina,
            @RequestParam(defaultValue = "20") final int tamano,
            @RequestParam(required = false) final String busqueda,
            @RequestParam(required = false) final String sortBy,
            @RequestParam(required = false) final String sortDir) {
        return catLocalidadAPIService.listar(pagina, tamano, busqueda, sortBy, sortDir);
    }

    @Operation(operationId = "crear-localidad", summary = "Crea un nuevo registro de localidad")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatLocalidadResponse.class)))
    })
    @PostMapping(value = UrlConst.LOCALIDAD_CREAR,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatLocalidadResponse> crear(@Valid @RequestBody final CatLocalidadRequest request) {
        return catLocalidadAPIService.crear(request);
    }

    @Operation(operationId = "buscar-localidad", summary = "Busca un registro de localidad por clave")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatLocalidadResponse.class)))
    })
    @GetMapping(value = UrlConst.LOCALIDAD_BUSCAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatLocalidadResponse> findById(
            @Parameter(description = "Clave de localidad") @PathVariable final String cveLocalidad) {
        return catLocalidadAPIService.findById(cveLocalidad);
    }

    @Operation(operationId = "actualizar-localidad", summary = "Actualiza un registro de localidad")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatLocalidadResponse.class)))
    })
    @PutMapping(value = UrlConst.LOCALIDAD_ACTUALIZAR,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatLocalidadResponse> actualizar(
            @Parameter(description = "Clave de localidad") @PathVariable final String cveLocalidad,
            @RequestBody final CatLocalidadRequest request) {
        return catLocalidadAPIService.actualizar(cveLocalidad, request);
    }
}
