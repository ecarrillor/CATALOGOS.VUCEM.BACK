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
import mx.gob.sat.catalogo.controller.request.calendario.CatCalendarioRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.calendario.CatCalendarioResponse;
import mx.gob.sat.catalogo.service.api.calendario.CatCalendarioAPIService;
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
 * <b>Class:</b> CatCalendarioController.java <br>
 * <b>Description:</b>
 * <p>Controlador REST para el catalogo de calendarios.
 * Expone endpoints bajo la ruta base {@code api/catalogo-admin/calendario}.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Controlador
 */
@Tag(name = "Cat. Calendarios",
        description = "Servicios de consulta y mantenimiento del catalogo de calendarios")
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class CatCalendarioController extends ApiBaseController {

    /** Servicio de negocio para el catalogo de calendarios. */
    private final CatCalendarioAPIService catCalendarioAPIService;

    /**
     * Lista los calendarios de forma paginada.
     *
     * @param pagina Numero de pagina (base 0, default 0).
     * @param tamano Cantidad de elementos por pagina (default 20).
     * @param busqueda Texto a buscar (opcional).
     * @param sortBy Columna de ordenamiento (opcional).
     * @param sortDir Direccion de ordenamiento (opcional).
     * @return Pagina de calendarios envuelta en BaseResponse.
     */
    @Operation(operationId = "listar-calendarios", summary = "Lista los calendarios paginados",
        description = "Retorna una pagina de calendarios con soporte de busqueda y ordenamiento.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = PaginaResponse.class)))
    })
    @GetMapping(value = UrlConst.CALENDARIO_LISTAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<PaginaResponse<CatCalendarioResponse>> listar(
            @Parameter(description = "Numero de pagina (base 0)")
            @RequestParam(defaultValue = "0") final int pagina,
            @Parameter(description = "Tamano de pagina")
            @RequestParam(defaultValue = "20") final int tamano,
            @Parameter(description = "Texto de busqueda")
            @RequestParam(required = false) final String busqueda,
            @Parameter(description = "Columna de ordenamiento")
            @RequestParam(required = false) final String sortBy,
            @Parameter(description = "Direccion: asc o desc")
            @RequestParam(required = false) final String sortDir) {
        return catCalendarioAPIService.listar(pagina, tamano, busqueda, sortBy, sortDir);
    }

    /**
     * Crea un nuevo calendario.
     *
     * @param request Datos del calendario a crear.
     * @return Calendario creado envuelto en BaseResponse.
     */
    @Operation(operationId = "crear-calendario", summary = "Crea un nuevo calendario",
        description = "Registra un nuevo calendario en el catalogo.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatCalendarioResponse.class)))
    })
    @PostMapping(value = UrlConst.CALENDARIO_CREAR,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatCalendarioResponse> crear(@Valid @RequestBody final CatCalendarioRequest request) {
        return catCalendarioAPIService.crear(request);
    }

    /**
     * Busca un calendario por su clave.
     *
     * @param cveCalendario Clave del calendario.
     * @return Calendario encontrado envuelto en BaseResponse.
     */
    @Operation(operationId = "buscar-calendario", summary = "Busca un calendario por clave",
        description = "Retorna la informacion de un calendario a partir de su clave.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatCalendarioResponse.class)))
    })
    @GetMapping(value = UrlConst.CALENDARIO_BUSCAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatCalendarioResponse> findById(
            @Parameter(description = "Clave del calendario") @PathVariable final String cveCalendario) {
        return catCalendarioAPIService.findById(cveCalendario);
    }

    /**
     * Actualiza los campos de un calendario existente.
     *
     * @param cveCalendario Clave del calendario.
     * @param request Datos a actualizar.
     * @return Calendario actualizado envuelto en BaseResponse.
     */
    @Operation(operationId = "actualizar-calendario", summary = "Actualiza un calendario",
        description = "Actualiza los campos no nulos de un calendario existente.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatCalendarioResponse.class)))
    })
    @PutMapping(value = UrlConst.CALENDARIO_ACTUALIZAR,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatCalendarioResponse> actualizar(
            @Parameter(description = "Clave del calendario") @PathVariable final String cveCalendario,
            @RequestBody final CatCalendarioRequest request) {
        return catCalendarioAPIService.actualizar(cveCalendario, request);
    }
}
