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
import mx.gob.sat.catalogo.controller.request.tipoaduana.CatTipoAduanaRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.aduana.CatTipoAduanaResponse;
import mx.gob.sat.catalogo.service.api.tipoaduana.CatTipoAduanaAPIService;
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
 * <b>Class:</b> CatTipoAduanaController.java <br>
 * <b>Description:</b>
 * <p>Controlador REST para el catalogo de tipos de aduana.
 * Expone endpoints de consulta y mantenimiento bajo la ruta base
 * {@code api/catalogo-admin/tipo-aduana}.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Controlador
 */
@Tag(name = "Cat. Tipos de Aduana",
        description = "Servicios de consulta y mantenimiento del catalogo de tipos de aduana")
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class CatTipoAduanaController extends ApiBaseController {

    /** Servicio de negocio para el catalogo de tipos de aduana. */
    private final CatTipoAduanaAPIService catTipoAduanaAPIService;

    /**
     * Lista los tipos de aduana de forma paginada.
     *
     * @param pagina Numero de pagina (base 0, default 0).
     * @param tamano Cantidad de elementos por pagina (default 20).
     * @param busqueda Texto a buscar en nombre o clave (opcional).
     * @param sortBy Columna de ordenamiento (opcional).
     * @param sortDir Direccion de ordenamiento (opcional).
     * @return Pagina de tipos de aduana envuelta en BaseResponse.
     */
    @Operation(
        operationId = "listar-tipos-aduana-catalogo",
        summary = "Lista los tipos de aduana paginados",
        description = "Retorna una pagina de tipos de aduana con soporte de busqueda y ordenamiento."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200",
            description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = PaginaResponse.class)))
    })
    @GetMapping(value = UrlConst.TIPO_ADUANA_LISTAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<PaginaResponse<CatTipoAduanaResponse>> listar(
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
        return catTipoAduanaAPIService.listar(pagina, tamano, busqueda, sortBy, sortDir);
    }

    /**
     * Crea un nuevo tipo de aduana en el catalogo.
     *
     * @param request Datos del tipo de aduana a crear.
     * @return Tipo de aduana creado envuelto en BaseResponse.
     */
    @Operation(
        operationId = "crear-tipo-aduana",
        summary = "Crea un nuevo tipo de aduana",
        description = "Registra un nuevo tipo de aduana en el catalogo."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200",
            description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatTipoAduanaResponse.class)))
    })
    @PostMapping(value = UrlConst.TIPO_ADUANA_CREAR,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatTipoAduanaResponse> crear(@Valid @RequestBody final CatTipoAduanaRequest request) {
        return catTipoAduanaAPIService.crear(request);
    }

    /**
     * Busca un tipo de aduana por su clave.
     *
     * @param cveTipoAduana Clave del tipo de aduana a buscar.
     * @return Tipo de aduana encontrado envuelto en BaseResponse.
     */
    @Operation(
        operationId = "buscar-tipo-aduana",
        summary = "Busca un tipo de aduana por clave",
        description = "Retorna la informacion de un tipo de aduana a partir de su clave."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200",
            description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatTipoAduanaResponse.class)))
    })
    @GetMapping(value = UrlConst.TIPO_ADUANA_BUSCAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatTipoAduanaResponse> findById(
            @Parameter(description = "Clave del tipo de aduana") @PathVariable final String cveTipoAduana) {
        return catTipoAduanaAPIService.findById(cveTipoAduana);
    }

    /**
     * Actualiza los campos de un tipo de aduana existente (actualizacion parcial).
     *
     * @param cveTipoAduana Clave del tipo de aduana a actualizar.
     * @param request Datos a actualizar.
     * @return Tipo de aduana actualizado envuelto en BaseResponse.
     */
    @Operation(
        operationId = "actualizar-tipo-aduana",
        summary = "Actualiza un tipo de aduana",
        description = "Actualiza los campos no nulos de un tipo de aduana existente."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200",
            description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatTipoAduanaResponse.class)))
    })
    @PutMapping(value = UrlConst.TIPO_ADUANA_ACTUALIZAR,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatTipoAduanaResponse> actualizar(
            @Parameter(description = "Clave del tipo de aduana") @PathVariable final String cveTipoAduana,
            @RequestBody final CatTipoAduanaRequest request) {
        return catTipoAduanaAPIService.actualizar(cveTipoAduana, request);
    }
}
