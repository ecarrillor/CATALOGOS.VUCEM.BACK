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
import mx.gob.sat.catalogo.controller.request.genero.CatGeneroRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.genero.CatGeneroResponse;
import mx.gob.sat.catalogo.service.api.genero.CatGeneroAPIService;
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
 * <b>Class:</b> CatGeneroController.java <br>
 * <b>Description:</b>
 * <p>Controlador REST para el catalogo de generos.
 * Expone endpoints bajo la ruta base {@code api/catalogo-admin/genero}.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Controlador
 */
@Tag(name = "Cat. Generos",
        description = "Servicios de consulta y mantenimiento del catalogo de generos")
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class CatGeneroController extends ApiBaseController {

    /** Servicio de negocio para el catalogo de generos. */
    private final CatGeneroAPIService catGeneroAPIService;

    /**
     * Lista los generos de forma paginada.
     *
     * @param pagina Numero de pagina (base 0, default 0).
     * @param tamano Cantidad de elementos por pagina (default 20).
     * @param busqueda Texto a buscar en descripcion (opcional).
     * @param sortBy Columna de ordenamiento (opcional).
     * @param sortDir Direccion de ordenamiento (opcional).
     * @return Pagina de generos envuelta en BaseResponse.
     */
    @Operation(operationId = "listar-generos", summary = "Lista los generos paginados",
        description = "Retorna una pagina de generos con soporte de busqueda y ordenamiento.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = PaginaResponse.class)))
    })
    @GetMapping(value = UrlConst.GENERO_LISTAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<PaginaResponse<CatGeneroResponse>> listar(
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
        return catGeneroAPIService.listar(pagina, tamano, busqueda, sortBy, sortDir);
    }

    /**
     * Crea un nuevo genero.
     *
     * @param request Datos del genero a crear.
     * @return Genero creado envuelto en BaseResponse.
     */
    @Operation(operationId = "crear-genero", summary = "Crea un nuevo genero",
        description = "Registra un nuevo genero en el catalogo.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatGeneroResponse.class)))
    })
    @PostMapping(value = UrlConst.GENERO_CREAR,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatGeneroResponse> crear(@Valid @RequestBody final CatGeneroRequest request) {
        return catGeneroAPIService.crear(request);
    }

    /**
     * Busca un genero por su identificador.
     *
     * @param idGenero Identificador del genero.
     * @return Genero encontrado envuelto en BaseResponse.
     */
    @Operation(operationId = "buscar-genero", summary = "Busca un genero por identificador",
        description = "Retorna la informacion de un genero a partir de su identificador.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatGeneroResponse.class)))
    })
    @GetMapping(value = UrlConst.GENERO_BUSCAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatGeneroResponse> findById(
            @Parameter(description = "Identificador del genero") @PathVariable final Integer idGenero) {
        return catGeneroAPIService.findById(idGenero);
    }

    /**
     * Actualiza los campos de un genero existente.
     *
     * @param idGenero Identificador del genero.
     * @param request Datos a actualizar.
     * @return Genero actualizado envuelto en BaseResponse.
     */
    @Operation(operationId = "actualizar-genero", summary = "Actualiza un genero",
        description = "Actualiza los campos no nulos de un genero existente.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatGeneroResponse.class)))
    })
    @PutMapping(value = UrlConst.GENERO_ACTUALIZAR,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatGeneroResponse> actualizar(
            @Parameter(description = "Identificador del genero") @PathVariable final Integer idGenero,
            @RequestBody final CatGeneroRequest request) {
        return catGeneroAPIService.actualizar(idGenero, request);
    }
}
