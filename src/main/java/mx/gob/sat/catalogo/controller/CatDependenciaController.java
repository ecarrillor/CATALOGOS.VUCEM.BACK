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
import mx.gob.sat.catalogo.controller.request.dependencia.CatDependenciaRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.SelectResponse;
import mx.gob.sat.catalogo.controller.response.dependencia.CatDependenciaResponse;
import mx.gob.sat.catalogo.service.api.dependencia.CatDependenciaAPIService;
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
 * <b>Class:</b> CatDependenciaController.java <br>
 * <b>Description:</b>
 * <p>Controlador REST para el catalogo de dependencias.
 * Expone endpoints bajo la ruta base {@code api/catalogo-admin/dependencia}.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Controlador
 */
@Tag(name = "Cat. Dependencias",
        description = "Servicios de consulta y mantenimiento del catalogo de dependencias")
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class CatDependenciaController extends ApiBaseController {

    /** Servicio de negocio para el catalogo de dependencias. */
    private final CatDependenciaAPIService catDependenciaAPIService;

    /**
     * Lista las dependencias de forma paginada.
     *
     * @param pagina Numero de pagina (base 0, default 0).
     * @param tamano Cantidad de elementos por pagina (default 20).
     * @param busqueda Texto de busqueda o "activo"/"inactivo" (opcional).
     * @param sortBy Columna de ordenamiento (opcional).
     * @param sortDir Direccion de ordenamiento (opcional).
     * @return Pagina de dependencias envuelta en BaseResponse.
     */
    @Operation(operationId = "listar-dependencias", summary = "Lista las dependencias paginadas",
        description = "Retorna una pagina de dependencias. Si busqueda es 'activo' o 'inactivo' filtra por estado.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = PaginaResponse.class)))
    })
    @GetMapping(value = UrlConst.DEPENDENCIA_LISTAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<PaginaResponse<CatDependenciaResponse>> listar(
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
        return catDependenciaAPIService.listar(pagina, tamano, busqueda, sortBy, sortDir);
    }

    /**
     * Crea una nueva dependencia.
     *
     * @param request Datos de la dependencia a crear.
     * @return Dependencia creada envuelta en BaseResponse.
     */
    @Operation(operationId = "crear-dependencia", summary = "Crea una nueva dependencia",
        description = "Registra una nueva dependencia en el catalogo.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatDependenciaResponse.class)))
    })
    @PostMapping(value = UrlConst.DEPENDENCIA_CREAR,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatDependenciaResponse> crear(@Valid @RequestBody final CatDependenciaRequest request) {
        return catDependenciaAPIService.crear(request);
    }

    /**
     * Busca una dependencia por su identificador.
     *
     * @param id Identificador de la dependencia.
     * @return Dependencia encontrada envuelta en BaseResponse.
     */
    @Operation(operationId = "buscar-dependencia", summary = "Busca una dependencia por identificador",
        description = "Retorna la informacion de una dependencia a partir de su identificador.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatDependenciaResponse.class)))
    })
    @GetMapping(value = UrlConst.DEPENDENCIA_BUSCAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatDependenciaResponse> findById(
            @Parameter(description = "Identificador de la dependencia") @PathVariable final Long id) {
        return catDependenciaAPIService.findById(id);
    }

    /**
     * Actualiza los campos de una dependencia existente.
     *
     * @param id Identificador de la dependencia.
     * @param request Datos a actualizar.
     * @return Dependencia actualizada envuelta en BaseResponse.
     */
    @Operation(operationId = "actualizar-dependencia", summary = "Actualiza una dependencia",
        description = "Actualiza los campos no nulos de una dependencia existente.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatDependenciaResponse.class)))
    })
    @PutMapping(value = UrlConst.DEPENDENCIA_ACTUALIZAR,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatDependenciaResponse> actualizar(
            @Parameter(description = "Identificador de la dependencia") @PathVariable final Long id,
            @RequestBody final CatDependenciaRequest request) {
        return catDependenciaAPIService.actualizar(id, request);
    }

    /**
     * Retorna la lista de calendarios disponibles para dropdowns.
     *
     * @return Lista de seleccion de calendarios envuelta en BaseResponse.
     */
    @Operation(operationId = "listar-calendarios-para-dependencia",
        summary = "Lista los calendarios disponibles",
        description = "Retorna todos los calendarios en formato clave-nombre para uso en dropdowns.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = List.class)))
    })
    @GetMapping(value = UrlConst.DEPENDENCIA_CALENDARIOS, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<List<SelectResponse>> listarCalendarios() {
        return catDependenciaAPIService.listarCalendarios();
    }
}
