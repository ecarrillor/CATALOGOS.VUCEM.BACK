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
import mx.gob.sat.catalogo.controller.request.lenguaje.CatLenguajeRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.lenguaje.CatLenguajeResponse;
import mx.gob.sat.catalogo.service.api.lenguaje.CatLenguajeAPIService;
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
 * <b>Class:</b> CatLenguajeController.java <br>
 * <b>Description:</b>
 * <p>Controlador REST para el catalogo de lenguajes.
 * Expone endpoints bajo la ruta base {@code api/catalogo-admin/lenguaje}.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Controlador
 */
@Tag(name = "Cat. Lenguajes",
        description = "Servicios de consulta y mantenimiento del catalogo de lenguajes")
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class CatLenguajeController extends ApiBaseController {

    /** Servicio de negocio para el catalogo de lenguajes. */
    private final CatLenguajeAPIService catLenguajeAPIService;

    /**
     * Lista los lenguajes de forma paginada.
     *
     * @param pagina Numero de pagina (base 0, default 0).
     * @param tamano Cantidad de elementos por pagina (default 20).
     * @param busqueda Texto a buscar (opcional).
     * @param sortBy Columna de ordenamiento (opcional).
     * @param sortDir Direccion de ordenamiento (opcional).
     * @return Pagina de lenguajes envuelta en BaseResponse.
     */
    @Operation(operationId = "listar-lenguajes", summary = "Lista los lenguajes paginados",
        description = "Retorna una pagina de lenguajes con soporte de busqueda y ordenamiento.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = PaginaResponse.class)))
    })
    @GetMapping(value = UrlConst.LENGUAJE_LISTAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<PaginaResponse<CatLenguajeResponse>> listar(
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
        return catLenguajeAPIService.listar(pagina, tamano, busqueda, sortBy, sortDir);
    }

    /**
     * Crea un nuevo lenguaje.
     *
     * @param request Datos del lenguaje a crear.
     * @return Lenguaje creado envuelto en BaseResponse.
     */
    @Operation(operationId = "crear-lenguaje", summary = "Crea un nuevo lenguaje",
        description = "Registra un nuevo lenguaje en el catalogo.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatLenguajeResponse.class)))
    })
    @PostMapping(value = UrlConst.LENGUAJE_CREAR,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatLenguajeResponse> crear(@Valid @RequestBody final CatLenguajeRequest request) {
        return catLenguajeAPIService.crear(request);
    }

    /**
     * Busca un lenguaje por su clave.
     *
     * @param cveLenguaje Clave del lenguaje.
     * @return Lenguaje encontrado envuelto en BaseResponse.
     */
    @Operation(operationId = "buscar-lenguaje", summary = "Busca un lenguaje por clave",
        description = "Retorna la informacion de un lenguaje a partir de su clave.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatLenguajeResponse.class)))
    })
    @GetMapping(value = UrlConst.LENGUAJE_BUSCAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatLenguajeResponse> findById(
            @Parameter(description = "Clave del lenguaje") @PathVariable final String cveLenguaje) {
        return catLenguajeAPIService.findById(cveLenguaje);
    }

    /**
     * Actualiza los campos de un lenguaje existente.
     *
     * @param cveLenguaje Clave del lenguaje.
     * @param request Datos a actualizar.
     * @return Lenguaje actualizado envuelto en BaseResponse.
     */
    @Operation(operationId = "actualizar-lenguaje", summary = "Actualiza un lenguaje",
        description = "Actualiza los campos no nulos de un lenguaje existente.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatLenguajeResponse.class)))
    })
    @PutMapping(value = UrlConst.LENGUAJE_ACTUALIZAR,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatLenguajeResponse> actualizar(
            @Parameter(description = "Clave del lenguaje") @PathVariable final String cveLenguaje,
            @RequestBody final CatLenguajeRequest request) {
        return catLenguajeAPIService.actualizar(cveLenguaje, request);
    }
}
