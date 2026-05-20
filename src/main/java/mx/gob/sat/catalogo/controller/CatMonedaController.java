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
import mx.gob.sat.catalogo.controller.request.moneda.CatMonedaRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.moneda.CatMonedaResponse;
import mx.gob.sat.catalogo.service.api.moneda.CatMonedaAPIService;
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
 * <b>Class:</b> CatMonedaController.java <br>
 * <b>Description:</b>
 * <p>Controlador REST para el catalogo de monedas.
 * Expone endpoints de consulta y mantenimiento bajo la ruta base
 * {@code api/catalogo-admin/moneda}.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Controlador
 */
@Tag(name = "Cat. Monedas",
        description = "Servicios de consulta y mantenimiento del catalogo de monedas")
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class CatMonedaController extends ApiBaseController {

    /** Servicio de negocio para el catalogo de monedas. */
    private final CatMonedaAPIService catMonedaAPIService;

    /**
     * Lista las monedas de forma paginada.
     *
     * @param pagina Numero de pagina (base 0, default 0).
     * @param tamano Cantidad de elementos por pagina (default 20).
     * @param busqueda Texto a buscar en nombre o clave (opcional).
     * @param sortBy Columna de ordenamiento (opcional).
     * @param sortDir Direccion de ordenamiento (opcional).
     * @return Pagina de monedas envuelta en BaseResponse.
     */
    @Operation(
        operationId = "listar-monedas",
        summary = "Lista las monedas paginadas",
        description = "Retorna una pagina de monedas con soporte de busqueda y ordenamiento."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200",
            description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = PaginaResponse.class)))
    })
    @GetMapping(value = UrlConst.MONEDA_LISTAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<PaginaResponse<CatMonedaResponse>> listar(
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
        return catMonedaAPIService.listar(pagina, tamano, busqueda, sortBy, sortDir);
    }

    /**
     * Crea una nueva moneda en el catalogo.
     *
     * @param request Datos de la moneda a crear.
     * @return Moneda creada envuelta en BaseResponse.
     */
    @Operation(
        operationId = "crear-moneda",
        summary = "Crea una nueva moneda",
        description = "Registra una nueva moneda en el catalogo."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200",
            description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatMonedaResponse.class)))
    })
    @PostMapping(value = UrlConst.MONEDA_CREAR,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatMonedaResponse> crear(@Valid @RequestBody final CatMonedaRequest request) {
        return catMonedaAPIService.crear(request);
    }

    /**
     * Busca una moneda por su clave.
     *
     * @param cveMoneda Clave de la moneda a buscar.
     * @return Moneda encontrada envuelta en BaseResponse.
     */
    @Operation(
        operationId = "buscar-moneda",
        summary = "Busca una moneda por clave",
        description = "Retorna la informacion de una moneda a partir de su clave."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200",
            description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatMonedaResponse.class)))
    })
    @GetMapping(value = UrlConst.MONEDA_BUSCAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatMonedaResponse> findById(
            @Parameter(description = "Clave de la moneda") @PathVariable final String cveMoneda) {
        return catMonedaAPIService.findById(cveMoneda);
    }

    /**
     * Actualiza los campos de una moneda existente (actualizacion parcial).
     *
     * @param cveMoneda Clave de la moneda a actualizar.
     * @param request Datos a actualizar.
     * @return Moneda actualizada envuelta en BaseResponse.
     */
    @Operation(
        operationId = "actualizar-moneda",
        summary = "Actualiza una moneda",
        description = "Actualiza los campos no nulos de una moneda existente."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200",
            description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatMonedaResponse.class)))
    })
    @PutMapping(value = UrlConst.MONEDA_ACTUALIZAR,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatMonedaResponse> actualizar(
            @Parameter(description = "Clave de la moneda") @PathVariable final String cveMoneda,
            @RequestBody final CatMonedaRequest request) {
        return catMonedaAPIService.actualizar(cveMoneda, request);
    }
}
