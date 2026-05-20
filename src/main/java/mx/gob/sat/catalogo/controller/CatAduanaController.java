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
import mx.gob.sat.catalogo.controller.request.aduana.CatAduanaRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.aduana.CatAduanaResponse;
import mx.gob.sat.catalogo.controller.response.aduana.CatEntidadResponse;
import mx.gob.sat.catalogo.controller.response.aduana.CatTipoAduanaResponse;
import mx.gob.sat.catalogo.service.api.aduana.CatAduanaAPIService;
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
 * <b>Class:</b> CatAduanaController.java <br>
 * <b>Description:</b>
 * <p>Controlador REST para el catalogo de aduanas.
 * Expone endpoints de consulta y mantenimiento bajo la ruta base {@code api/catalogo-admin/aduana}.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Controlador
 */
@Tag(name = "Cat. Aduanas", description = "Servicios de consulta y mantenimiento del catalogo de aduanas")
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class CatAduanaController extends ApiBaseController {

    /****** SERVICIOS API ********/

    /** Servicio de negocio para el catalogo de aduanas. */
    private final CatAduanaAPIService catAduanaAPIService;

    /**
     * Lista las aduanas de forma paginada con soporte de busqueda y ordenamiento.
     *
     * @param pagina Numero de pagina (base 0, default 0).
     * @param tamano Cantidad de elementos por pagina (default 20).
     * @param busqueda Texto a buscar en nombre o clave (opcional).
     * @param sortBy Columna de ordenamiento (opcional).
     * @param sortDir Direccion de ordenamiento: {@code asc} o {@code desc} (opcional).
     * @return Pagina de aduanas envuelta en BaseResponse.
     */
    @Operation(
        operationId = "listar-aduanas",
        summary = "Lista las aduanas paginadas",
        description = "Retorna una pagina de aduanas con soporte de busqueda por nombre o clave y ordenamiento."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200",
            description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = PaginaResponse.class)))
    })
    @GetMapping(value = UrlConst.ADUANA_LISTAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<PaginaResponse<CatAduanaResponse>> listarAduanas(
            @Parameter(description = "Numero de pagina (base 0)") @RequestParam(defaultValue = "0") final int pagina,
            @Parameter(description = "Tamano de pagina") @RequestParam(defaultValue = "20") final int tamano,
            @Parameter(description = "Texto de busqueda") @RequestParam(required = false) final String busqueda,
            @Parameter(description = "Columna de ordenamiento") @RequestParam(required = false) final String sortBy,
            @Parameter(description = "Direccion: asc o desc") @RequestParam(required = false) final String sortDir) {
        return catAduanaAPIService.listarAduanas(pagina, tamano, busqueda, sortBy, sortDir);
    }

    /**
     * Crea una nueva aduana en el catalogo.
     *
     * @param request Datos de la aduana a crear.
     * @return Aduana creada envuelta en BaseResponse.
     */
    @Operation(
        operationId = "crear-aduana",
        summary = "Crea una nueva aduana",
        description = "Registra una nueva aduana en el catalogo validando que la clave no exista."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200",
            description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatAduanaResponse.class)))
    })
    @PostMapping(value = UrlConst.ADUANA_CREAR,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatAduanaResponse> crearAduana(@Valid @RequestBody final CatAduanaRequest request) {
        return catAduanaAPIService.crearAduana(request);
    }

    /**
     * Busca una aduana por su clave.
     *
     * @param cveAduana Clave de la aduana a buscar.
     * @return Aduana encontrada envuelta en BaseResponse.
     */
    @Operation(
        operationId = "buscar-aduana",
        summary = "Busca una aduana por clave",
        description = "Retorna la informacion de una aduana a partir de su clave."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200",
            description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatAduanaResponse.class)))
    })
    @GetMapping(value = UrlConst.ADUANA_BUSCAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatAduanaResponse> findByCveAduana(
            @Parameter(description = "Clave de la aduana") @PathVariable final String cveAduana) {
        return catAduanaAPIService.findByCveAduana(cveAduana);
    }

    /**
     * Actualiza los campos de una aduana existente (actualizacion parcial).
     *
     * @param cveAduana Clave de la aduana a actualizar.
     * @param request Datos a actualizar.
     * @return Aduana actualizada envuelta en BaseResponse.
     */
    @Operation(
        operationId = "actualizar-aduana",
        summary = "Actualiza una aduana",
        description = "Actualiza los campos no nulos de una aduana existente."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200",
            description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatAduanaResponse.class)))
    })
    @PutMapping(value = UrlConst.ADUANA_ACTUALIZAR,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatAduanaResponse> actualizarAduana(
            @Parameter(description = "Clave de la aduana") @PathVariable final String cveAduana,
            @RequestBody final CatAduanaRequest request) {
        return catAduanaAPIService.actualizarAduana(cveAduana, request);
    }

    /**
     * Retorna el listado de todos los tipos de aduana disponibles.
     *
     * @return Lista de tipos de aduana envuelta en BaseResponse.
     */
    @Operation(
        operationId = "listar-tipos-aduana",
        summary = "Lista los tipos de aduana",
        description = "Retorna todos los tipos de aduana disponibles en el catalogo."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200",
            description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = List.class)))
    })
    @GetMapping(value = UrlConst.ADUANA_TIPOS, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<List<CatTipoAduanaResponse>> listarTiposAduana() {
        return catAduanaAPIService.listarTiposAduana();
    }

    /**
     * Retorna el listado de todas las entidades federativas disponibles.
     *
     * @return Lista de entidades envuelta en BaseResponse.
     */
    @Operation(
        operationId = "listar-entidades",
        summary = "Lista las entidades federativas",
        description = "Retorna todas las entidades federativas disponibles en el catalogo."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200",
            description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = List.class)))
    })
    @GetMapping(value = UrlConst.ADUANA_ENTIDADES, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<List<CatEntidadResponse>> listarEntidades() {
        return catAduanaAPIService.listarEntidades();
    }

    /**
     * Retorna la ultima clave de aduana registrada.
     *
     * @return Ultima clave de aduana envuelta en BaseResponse.
     */
    @Operation(
        operationId = "ultimo-id-aduana",
        summary = "Obtiene la ultima clave de aduana",
        description = "Retorna la ultima clave de aduana segun orden descendente."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200",
            description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = String.class)))
    })
    @GetMapping(value = UrlConst.ADUANA_ULTIMO_ID, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<String> obtenerUltimaCveAduana() {
        return catAduanaAPIService.obtenerUltimaCveAduana();
    }
}
