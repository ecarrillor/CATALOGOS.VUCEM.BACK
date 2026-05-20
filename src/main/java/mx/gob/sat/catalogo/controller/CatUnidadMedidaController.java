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
import mx.gob.sat.catalogo.controller.request.unidadmedida.CatUnidadMedidaRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.unidadmedida.CatUnidadMedidaResponse;
import mx.gob.sat.catalogo.service.api.unidadmedida.CatUnidadMedidaAPIService;
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
 * <b>Class:</b> CatUnidadMedidaController.java <br>
 * <b>Description:</b>
 * <p>Controlador REST para el catalogo de unidades de medida.
 * Expone endpoints bajo la ruta base {@code api/catalogo-admin/unidad-medida}.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Controlador
 */
@Tag(name = "Cat. Unidades de Medida",
        description = "Servicios de consulta y mantenimiento del catalogo de unidades de medida")
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class CatUnidadMedidaController extends ApiBaseController {

    /** Servicio de negocio para el catalogo de unidades de medida. */
    private final CatUnidadMedidaAPIService catUnidadMedidaAPIService;

    /**
     * Lista las unidades de medida de forma paginada.
     *
     * @param pagina Numero de pagina (base 0, default 0).
     * @param tamano Cantidad de elementos por pagina (default 20).
     * @param busqueda Texto a buscar (opcional).
     * @param sortBy Columna de ordenamiento (opcional).
     * @param sortDir Direccion de ordenamiento (opcional).
     * @return Pagina de unidades de medida envuelta en BaseResponse.
     */
    @Operation(operationId = "listar-unidades-medida", summary = "Lista las unidades de medida paginadas",
        description = "Retorna una pagina de unidades de medida con soporte de busqueda y ordenamiento.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = PaginaResponse.class)))
    })
    @GetMapping(value = UrlConst.UNIDAD_MEDIDA_LISTAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<PaginaResponse<CatUnidadMedidaResponse>> listar(
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
        return catUnidadMedidaAPIService.listar(pagina, tamano, busqueda, sortBy, sortDir);
    }

    /**
     * Crea una nueva unidad de medida.
     *
     * @param request Datos de la unidad de medida a crear.
     * @return Unidad de medida creada envuelta en BaseResponse.
     */
    @Operation(operationId = "crear-unidad-medida", summary = "Crea una nueva unidad de medida",
        description = "Registra una nueva unidad de medida en el catalogo.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatUnidadMedidaResponse.class)))
    })
    @PostMapping(value = UrlConst.UNIDAD_MEDIDA_CREAR,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatUnidadMedidaResponse> crear(@Valid @RequestBody final CatUnidadMedidaRequest request) {
        return catUnidadMedidaAPIService.crear(request);
    }

    /**
     * Busca una unidad de medida por su clave.
     *
     * @param cveUnidadMedida Clave de la unidad de medida.
     * @return Unidad de medida encontrada envuelta en BaseResponse.
     */
    @Operation(operationId = "buscar-unidad-medida", summary = "Busca una unidad de medida por clave",
        description = "Retorna la informacion de una unidad de medida a partir de su clave.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatUnidadMedidaResponse.class)))
    })
    @GetMapping(value = UrlConst.UNIDAD_MEDIDA_BUSCAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatUnidadMedidaResponse> findById(
            @Parameter(description = "Clave de la unidad de medida") @PathVariable final String cveUnidadMedida) {
        return catUnidadMedidaAPIService.findById(cveUnidadMedida);
    }

    /**
     * Actualiza los campos de una unidad de medida existente.
     *
     * @param cveUnidadMedida Clave de la unidad de medida.
     * @param request Datos a actualizar.
     * @return Unidad de medida actualizada envuelta en BaseResponse.
     */
    @Operation(operationId = "actualizar-unidad-medida", summary = "Actualiza una unidad de medida",
        description = "Actualiza los campos no nulos de una unidad de medida existente.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatUnidadMedidaResponse.class)))
    })
    @PutMapping(value = UrlConst.UNIDAD_MEDIDA_ACTUALIZAR,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatUnidadMedidaResponse> actualizar(
            @Parameter(description = "Clave de la unidad de medida") @PathVariable final String cveUnidadMedida,
            @RequestBody final CatUnidadMedidaRequest request) {
        return catUnidadMedidaAPIService.actualizar(cveUnidadMedida, request);
    }
}
