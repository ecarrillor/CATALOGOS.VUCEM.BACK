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
import mx.gob.sat.catalogo.controller.request.pais.CatPaisRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.SelectResponse;
import mx.gob.sat.catalogo.controller.response.pais.CatPaisResponse;
import mx.gob.sat.catalogo.service.api.pais.CatPaisAPIService;
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
 * <b>Class:</b> CatPaisController.java <br>
 * <b>Description:</b>
 * <p>Controlador REST para el catalogo de paises.
 * Expone endpoints de consulta y mantenimiento bajo la ruta base
 * {@code api/catalogo-admin/pais}.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Controlador
 */
@Tag(name = "Cat. Paises",
        description = "Servicios de consulta y mantenimiento del catalogo de paises")
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class CatPaisController extends ApiBaseController {

    /** Servicio de negocio para el catalogo de paises. */
    private final CatPaisAPIService catPaisAPIService;

    /**
     * Lista los paises de forma paginada. Si {@code busqueda} es "activo" o "inactivo"
     * filtra por estado; en otro caso busca por texto en nombre o clave.
     *
     * @param pagina Numero de pagina (base 0, default 0).
     * @param tamano Cantidad de elementos por pagina (default 20).
     * @param busqueda Texto de busqueda, "activo" o "inactivo" (opcional).
     * @param sortBy Columna de ordenamiento (opcional).
     * @param sortDir Direccion de ordenamiento (opcional).
     * @return Pagina de paises envuelta en BaseResponse.
     */
    @Operation(
        operationId = "listar-paises",
        summary = "Lista los paises paginados",
        description = "Retorna una pagina de paises. Si busqueda es 'activo' o 'inactivo' filtra por estado."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200",
            description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = PaginaResponse.class)))
    })
    @GetMapping(value = UrlConst.PAIS_LISTAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<PaginaResponse<CatPaisResponse>> listar(
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
        return catPaisAPIService.listar(pagina, tamano, busqueda, sortBy, sortDir);
    }

    /**
     * Busca un pais por su clave.
     *
     * @param cvePais Clave del pais a buscar.
     * @return Pais encontrado envuelto en BaseResponse.
     */
    @Operation(
        operationId = "buscar-pais",
        summary = "Busca un pais por clave",
        description = "Retorna la informacion de un pais a partir de su clave."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200",
            description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatPaisResponse.class)))
    })
    @GetMapping(value = UrlConst.PAIS_BUSCAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatPaisResponse> findById(
            @Parameter(description = "Clave del pais") @PathVariable final String cvePais) {
        return catPaisAPIService.findById(cvePais);
    }

    /**
     * Crea un nuevo pais en el catalogo. Asigna automaticamente fecCaptura=hoy,
     * blnActivo=true, blnRestriccion=true y nombreAlterno=nombre si no se especifica.
     *
     * @param request Datos del pais a crear.
     * @return Pais creado envuelto en BaseResponse.
     */
    @Operation(
        operationId = "crear-pais",
        summary = "Crea un nuevo pais",
        description = "Registra un nuevo pais. fecCaptura, blnActivo y blnRestriccion se asignan automaticamente."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200",
            description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatPaisResponse.class)))
    })
    @PostMapping(value = UrlConst.PAIS_CREAR,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatPaisResponse> crear(@Valid @RequestBody final CatPaisRequest request) {
        return catPaisAPIService.crear(request);
    }

    /**
     * Actualiza los campos de un pais existente (actualizacion parcial).
     *
     * @param cvePais Clave del pais a actualizar.
     * @param request Datos a actualizar.
     * @return Pais actualizado envuelto en BaseResponse.
     */
    @Operation(
        operationId = "actualizar-pais",
        summary = "Actualiza un pais",
        description = "Actualiza los campos no nulos de un pais existente."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200",
            description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatPaisResponse.class)))
    })
    @PutMapping(value = UrlConst.PAIS_ACTUALIZAR,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatPaisResponse> actualizar(
            @Parameter(description = "Clave del pais") @PathVariable final String cvePais,
            @RequestBody final CatPaisRequest request) {
        return catPaisAPIService.actualizar(cvePais, request);
    }

    /**
     * Retorna la lista de monedas en formato clave-nombre para dropdowns.
     *
     * @return Lista de seleccion de monedas envuelta en BaseResponse.
     */
    @Operation(
        operationId = "listar-monedas-para-pais",
        summary = "Lista las monedas disponibles",
        description = "Retorna todas las monedas en formato clave-nombre para uso en dropdowns."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200",
            description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = List.class)))
    })
    @GetMapping(value = UrlConst.PAIS_MONEDAS, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<List<SelectResponse>> listarMonedas() {
        return catPaisAPIService.listarMonedas();
    }
}
