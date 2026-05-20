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
import mx.gob.sat.catalogo.controller.request.aga.CatAgaRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.aga.CatAgaResponse;
import mx.gob.sat.catalogo.service.api.aga.CatAgaAPIService;
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
 * <b>Class:</b> CatAgaController.java <br>
 * <b>Description:</b>
 * <p>Controlador REST para el catalogo de AGA.
 * Expone endpoints bajo la ruta base {@code api/catalogo-admin/aga}.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Controlador
 */
@Tag(name = "Cat. AGA",
        description = "Servicios de consulta y mantenimiento del catalogo de AGA")
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class CatAgaController extends ApiBaseController {

    /** Servicio de negocio para el catalogo de AGA. */
    private final CatAgaAPIService catAgaAPIService;

    /**
     * Lista los registros AGA de forma paginada.
     *
     * @param pagina Numero de pagina (base 0, default 0).
     * @param tamano Cantidad de elementos por pagina (default 20).
     * @param busqueda Texto a buscar (opcional).
     * @param sortBy Columna de ordenamiento (opcional).
     * @param sortDir Direccion de ordenamiento (opcional).
     * @return Pagina de registros AGA envuelta en BaseResponse.
     */
    @Operation(operationId = "listar-aga", summary = "Lista los registros AGA paginados",
        description = "Retorna una pagina de registros AGA con soporte de busqueda y ordenamiento.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = PaginaResponse.class)))
    })
    @GetMapping(value = UrlConst.AGA_LISTAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<PaginaResponse<CatAgaResponse>> listar(
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
        return catAgaAPIService.listar(pagina, tamano, busqueda, sortBy, sortDir);
    }

    /**
     * Crea un nuevo registro AGA.
     *
     * @param request Datos del registro AGA a crear.
     * @return Registro AGA creado envuelto en BaseResponse.
     */
    @Operation(operationId = "crear-aga", summary = "Crea un nuevo registro AGA",
        description = "Registra un nuevo parametro AGA en el catalogo.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatAgaResponse.class)))
    })
    @PostMapping(value = UrlConst.AGA_CREAR,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatAgaResponse> crear(@Valid @RequestBody final CatAgaRequest request) {
        return catAgaAPIService.crear(request);
    }

    /**
     * Busca un registro AGA por su clave.
     *
     * @param cveParametro Clave del parametro AGA.
     * @return Registro AGA encontrado envuelto en BaseResponse.
     */
    @Operation(operationId = "buscar-aga", summary = "Busca un registro AGA por clave",
        description = "Retorna la informacion de un parametro AGA a partir de su clave.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatAgaResponse.class)))
    })
    @GetMapping(value = UrlConst.AGA_BUSCAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatAgaResponse> findById(
            @Parameter(description = "Clave del parametro AGA") @PathVariable final String cveParametro) {
        return catAgaAPIService.findById(cveParametro);
    }

    /**
     * Actualiza los campos de un registro AGA existente.
     *
     * @param cveParametro Clave del parametro AGA.
     * @param request Datos a actualizar.
     * @return Registro AGA actualizado envuelto en BaseResponse.
     */
    @Operation(operationId = "actualizar-aga", summary = "Actualiza un registro AGA",
        description = "Actualiza los campos no nulos de un parametro AGA existente.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatAgaResponse.class)))
    })
    @PutMapping(value = UrlConst.AGA_ACTUALIZAR,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatAgaResponse> actualizar(
            @Parameter(description = "Clave del parametro AGA") @PathVariable final String cveParametro,
            @RequestBody final CatAgaRequest request) {
        return catAgaAPIService.actualizar(cveParametro, request);
    }
}
