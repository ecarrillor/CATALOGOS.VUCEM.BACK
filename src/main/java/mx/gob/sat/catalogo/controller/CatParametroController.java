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
import mx.gob.sat.catalogo.controller.request.parametro.CatParametroRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.SelectResponse;
import mx.gob.sat.catalogo.controller.response.parametro.CatParametroResponse;
import mx.gob.sat.catalogo.service.api.parametro.CatParametroAPIService;
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

import java.util.List;

/**
 * <b>Class:</b> CatParametroController.java <br>
 * <b>Description:</b>
 * <p>Controlador REST para el catalogo de parametros del sistema.
 * Expone endpoints bajo la ruta base {@code api/catalogo-admin/parametro}.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Controlador
 */
@Tag(name = "Cat. Parametros",
        description = "Servicios de consulta y mantenimiento del catalogo de parametros del sistema")
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class CatParametroController extends ApiBaseController {

    /** Servicio de negocio para el catalogo de parametros. */
    private final CatParametroAPIService catParametroAPIService;

    /**
     * Lista los parametros de forma paginada.
     *
     * @param pagina Numero de pagina (base 0, default 0).
     * @param tamano Cantidad de elementos por pagina (default 20).
     * @param busqueda Texto de busqueda o "activo"/"inactivo" (opcional).
     * @param sortBy Columna de ordenamiento (opcional).
     * @param sortDir Direccion de ordenamiento (opcional).
     * @return Pagina de parametros envuelta en BaseResponse.
     */
    @Operation(operationId = "listar-parametros", summary = "Lista los parametros paginados",
        description = "Retorna una pagina de parametros. Si busqueda es 'activo' o 'inactivo' filtra por estado.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = PaginaResponse.class)))
    })
    @GetMapping(value = UrlConst.PARAMETRO_LISTAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<PaginaResponse<CatParametroResponse>> listar(
            @Parameter(description = "Numero de pagina (base 0)")
            @RequestParam(defaultValue = "0") final int pagina,
            @Parameter(description = "Tamano de pagina")
            @RequestParam(defaultValue = "20") final int tamano,
            @Parameter(description = "Texto de busqueda o 'activo'/'inactivo'")
            @RequestParam(required = false) final String busqueda,
            @Parameter(description = "Columna de ordenamiento")
            @RequestParam(required = false) final String sortBy,
            @Parameter(description = "Direccion: asc o desc")
            @RequestParam(required = false) final String sortDir) {
        return catParametroAPIService.listar(pagina, tamano, busqueda, sortBy, sortDir);
    }

    /**
     * Crea un nuevo parametro.
     *
     * @param request Datos del parametro a crear.
     * @return Parametro creado envuelto en BaseResponse.
     */
    @Operation(operationId = "crear-parametro", summary = "Crea un nuevo parametro",
        description = "Registra un nuevo parametro del sistema en el catalogo.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatParametroResponse.class)))
    })
    @PostMapping(value = UrlConst.PARAMETRO_CREAR,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatParametroResponse> crear(@Valid @RequestBody final CatParametroRequest request) {
        return catParametroAPIService.crear(request);
    }

    /**
     * Busca un parametro por su clave.
     *
     * @param cveParametro Clave del parametro.
     * @return Parametro encontrado envuelto en BaseResponse.
     */
    @Operation(operationId = "buscar-parametro", summary = "Busca un parametro por clave",
        description = "Retorna la informacion de un parametro a partir de su clave.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatParametroResponse.class)))
    })
    @GetMapping(value = UrlConst.PARAMETRO_BUSCAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatParametroResponse> findById(
            @Parameter(description = "Clave del parametro") @PathVariable final String cveParametro) {
        return catParametroAPIService.findById(cveParametro);
    }

    /**
     * Actualiza los campos de un parametro existente.
     *
     * @param cveParametro Clave del parametro.
     * @param request Datos a actualizar.
     * @return Parametro actualizado envuelto en BaseResponse.
     */
    @Operation(operationId = "actualizar-parametro", summary = "Actualiza un parametro",
        description = "Actualiza los campos no nulos de un parametro existente.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatParametroResponse.class)))
    })
    @PutMapping(value = UrlConst.PARAMETRO_ACTUALIZAR,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatParametroResponse> actualizar(
            @Parameter(description = "Clave del parametro") @PathVariable final String cveParametro,
            @RequestBody final CatParametroRequest request) {
        return catParametroAPIService.actualizar(cveParametro, request);
    }

    /**
     * Retorna la lista de dependencias disponibles para dropdowns.
     *
     * @return Lista de seleccion de dependencias envuelta en BaseResponse.
     */
    @Operation(operationId = "listar-dependencias-para-parametro",
        summary = "Lista las dependencias disponibles",
        description = "Retorna todas las dependencias activas en formato id-nombre para uso en dropdowns.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = List.class)))
    })
    @GetMapping(value = UrlConst.PARAMETRO_DEPENDENCIAS, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<List<SelectResponse>> listarDependencias() {
        return catParametroAPIService.listarDependencias();
    }
}
