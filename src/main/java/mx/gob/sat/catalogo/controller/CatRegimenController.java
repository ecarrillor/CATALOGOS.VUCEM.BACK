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
import mx.gob.sat.catalogo.controller.request.regimen.CatRegimenRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.regimen.CatRegimenResponse;
import mx.gob.sat.catalogo.service.api.regimen.CatRegimenAPIService;
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
 * <b>Class:</b> CatRegimenController.java <br>
 * <b>Description:</b>
 * <p>Controlador REST para el catalogo de regimenes.
 * Expone endpoints bajo la ruta base {@code api/catalogo-admin/regimen}.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Controlador
 */
@Tag(name = "Cat. Regimenes",
        description = "Servicios de consulta y mantenimiento del catalogo de regimenes")
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class CatRegimenController extends ApiBaseController {

    /** Servicio de negocio para el catalogo de regimenes. */
    private final CatRegimenAPIService catRegimenAPIService;

    /**
     * Lista los regimenes de forma paginada.
     *
     * @param pagina Numero de pagina (base 0, default 0).
     * @param tamano Cantidad de elementos por pagina (default 20).
     * @param busqueda Texto a buscar (opcional).
     * @param sortBy Columna de ordenamiento (opcional).
     * @param sortDir Direccion de ordenamiento (opcional).
     * @return Pagina de regimenes envuelta en BaseResponse.
     */
    @Operation(operationId = "listar-regimenes", summary = "Lista los regimenes paginados",
        description = "Retorna una pagina de regimenes con soporte de busqueda y ordenamiento.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = PaginaResponse.class)))
    })
    @GetMapping(value = UrlConst.REGIMEN_LISTAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<PaginaResponse<CatRegimenResponse>> listar(
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
        return catRegimenAPIService.listar(pagina, tamano, busqueda, sortBy, sortDir);
    }

    /**
     * Crea un nuevo regimen.
     *
     * @param request Datos del regimen a crear.
     * @return Regimen creado envuelto en BaseResponse.
     */
    @Operation(operationId = "crear-regimen", summary = "Crea un nuevo regimen",
        description = "Registra un nuevo regimen en el catalogo.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatRegimenResponse.class)))
    })
    @PostMapping(value = UrlConst.REGIMEN_CREAR,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatRegimenResponse> crear(@Valid @RequestBody final CatRegimenRequest request) {
        return catRegimenAPIService.crear(request);
    }

    /**
     * Busca un regimen por su clave.
     *
     * @param cveRegimen Clave del regimen.
     * @return Regimen encontrado envuelto en BaseResponse.
     */
    @Operation(operationId = "buscar-regimen", summary = "Busca un regimen por clave",
        description = "Retorna la informacion de un regimen a partir de su clave.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatRegimenResponse.class)))
    })
    @GetMapping(value = UrlConst.REGIMEN_BUSCAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatRegimenResponse> findById(
            @Parameter(description = "Clave del regimen") @PathVariable final String cveRegimen) {
        return catRegimenAPIService.findById(cveRegimen);
    }

    /**
     * Actualiza los campos de un regimen existente.
     *
     * @param cveRegimen Clave del regimen.
     * @param request Datos a actualizar.
     * @return Regimen actualizado envuelto en BaseResponse.
     */
    @Operation(operationId = "actualizar-regimen", summary = "Actualiza un regimen",
        description = "Actualiza los campos no nulos de un regimen existente.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatRegimenResponse.class)))
    })
    @PutMapping(value = UrlConst.REGIMEN_ACTUALIZAR,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatRegimenResponse> actualizar(
            @Parameter(description = "Clave del regimen") @PathVariable final String cveRegimen,
            @RequestBody final CatRegimenRequest request) {
        return catRegimenAPIService.actualizar(cveRegimen, request);
    }
}
