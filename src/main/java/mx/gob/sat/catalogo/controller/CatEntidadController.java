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
import mx.gob.sat.catalogo.controller.request.entidad.CatEntidadRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.SelectResponse;
import mx.gob.sat.catalogo.controller.response.aduana.CatEntidadResponse;
import mx.gob.sat.catalogo.controller.response.pais.CatPaisResponse;
import mx.gob.sat.catalogo.service.api.entidad.CatEntidadAPIService;
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
 * <b>Class:</b> CatEntidadController.java <br>
 * <b>Description:</b>
 * <p>Controlador REST para el catalogo de entidades federativas.
 * Expone endpoints de consulta y mantenimiento bajo la ruta base
 * {@code api/catalogo-admin/entidad}.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Controlador
 */
@Tag(name = "Cat. Entidades Federativas",
        description = "Servicios de consulta y mantenimiento del catalogo de entidades federativas")
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class CatEntidadController extends ApiBaseController {

    /** Servicio de negocio para el catalogo de entidades federativas. */
    private final CatEntidadAPIService catEntidadAPIService;

    /**
     * Lista las entidades federativas de forma paginada.
     *
     * @param pagina Numero de pagina (base 0, default 0).
     * @param tamano Cantidad de elementos por pagina (default 20).
     * @param busqueda Texto a buscar en nombre o clave (opcional).
     * @param sortBy Columna de ordenamiento (opcional).
     * @param sortDir Direccion de ordenamiento (opcional).
     * @return Pagina de entidades envuelta en BaseResponse.
     */
    @Operation(
        operationId = "listar-entidades-catalogo",
        summary = "Lista las entidades federativas paginadas",
        description = "Retorna una pagina de entidades federativas con soporte de busqueda y ordenamiento."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200",
            description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = PaginaResponse.class)))
    })
    @GetMapping(value = UrlConst.ENTIDAD_LISTAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<PaginaResponse<CatEntidadResponse>> listar(
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
        return catEntidadAPIService.listar(pagina, tamano, busqueda, sortBy, sortDir);
    }

    /**
     * Crea una nueva entidad federativa en el catalogo.
     *
     * @param request Datos de la entidad a crear.
     * @return Entidad creada envuelta en BaseResponse.
     */
    @Operation(
        operationId = "crear-entidad",
        summary = "Crea una nueva entidad federativa",
        description = "Registra una nueva entidad federativa en el catalogo."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200",
            description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatEntidadResponse.class)))
    })
    @PostMapping(value = UrlConst.ENTIDAD_CREAR,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatEntidadResponse> crear(@Valid @RequestBody final CatEntidadRequest request) {
        return catEntidadAPIService.crear(request);
    }

    /**
     * Busca una entidad federativa por su clave.
     *
     * @param cveEntidad Clave de la entidad a buscar.
     * @return Entidad encontrada envuelta en BaseResponse.
     */
    @Operation(
        operationId = "buscar-entidad",
        summary = "Busca una entidad federativa por clave",
        description = "Retorna la informacion de una entidad federativa a partir de su clave."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200",
            description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatEntidadResponse.class)))
    })
    @GetMapping(value = UrlConst.ENTIDAD_BUSCAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatEntidadResponse> findById(
            @Parameter(description = "Clave de la entidad") @PathVariable final String cveEntidad) {
        return catEntidadAPIService.findById(cveEntidad);
    }

    /**
     * Actualiza los campos de una entidad federativa existente (actualizacion parcial).
     *
     * @param cveEntidad Clave de la entidad a actualizar.
     * @param request Datos a actualizar.
     * @return Entidad actualizada envuelta en BaseResponse.
     */
    @Operation(
        operationId = "actualizar-entidad",
        summary = "Actualiza una entidad federativa",
        description = "Actualiza los campos no nulos de una entidad federativa existente."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200",
            description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatEntidadResponse.class)))
    })
    @PutMapping(value = UrlConst.ENTIDAD_ACTUALIZAR,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatEntidadResponse> actualizar(
            @Parameter(description = "Clave de la entidad") @PathVariable final String cveEntidad,
            @RequestBody final CatEntidadRequest request) {
        return catEntidadAPIService.actualizar(cveEntidad, request);
    }

    /**
     * Retorna todos los paises disponibles para asociar a una entidad.
     *
     * @return Lista de paises envuelta en BaseResponse.
     */
    @Operation(
        operationId = "listar-paises-para-entidad",
        summary = "Lista los paises disponibles",
        description = "Retorna todos los paises disponibles para asociar a una entidad federativa."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200",
            description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = List.class)))
    })
    @GetMapping(value = UrlConst.ENTIDAD_PAISES, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<List<CatPaisResponse>> listarPaises() {
        return catEntidadAPIService.listarPaises();
    }

    /**
     * Retorna la lista de entidades en formato clave-nombre para dropdowns.
     *
     * @return Lista de seleccion envuelta en BaseResponse.
     */
    @Operation(
        operationId = "listar-nombres-entidades",
        summary = "Lista entidades en formato select",
        description = "Retorna todas las entidades en formato clave-nombre para uso en dropdowns."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200",
            description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = List.class)))
    })
    @GetMapping(value = UrlConst.ENTIDAD_NOMBRES, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<List<SelectResponse>> listarNombres() {
        return catEntidadAPIService.listarNombres();
    }
}
