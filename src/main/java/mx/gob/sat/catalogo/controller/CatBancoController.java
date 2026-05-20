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
import mx.gob.sat.catalogo.controller.request.banco.CatBancoRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.banco.CatBancoResponse;
import mx.gob.sat.catalogo.service.api.banco.CatBancoAPIService;
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
 * <b>Class:</b> CatBancoController.java <br>
 * <b>Description:</b>
 * <p>Controlador REST para el catalogo de bancos.
 * Expone endpoints bajo la ruta base {@code api/catalogo-admin/banco}.
 * El listado solo retorna bancos activos.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Controlador
 */
@Tag(name = "Cat. Bancos",
        description = "Servicios de consulta y mantenimiento del catalogo de bancos")
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class CatBancoController extends ApiBaseController {

    /** Servicio de negocio para el catalogo de bancos. */
    private final CatBancoAPIService catBancoAPIService;

    /**
     * Lista los bancos activos de forma paginada.
     *
     * @param pagina Numero de pagina (base 0, default 0).
     * @param tamano Cantidad de elementos por pagina (default 20).
     * @param busqueda Texto a buscar en nombre o clave (opcional).
     * @param sortBy Columna de ordenamiento (opcional).
     * @param sortDir Direccion de ordenamiento (opcional).
     * @return Pagina de bancos activos envuelta en BaseResponse.
     */
    @Operation(operationId = "listar-bancos", summary = "Lista los bancos activos paginados",
        description = "Retorna una pagina de bancos activos con soporte de busqueda y ordenamiento.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = PaginaResponse.class)))
    })
    @GetMapping(value = UrlConst.BANCO_LISTAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<PaginaResponse<CatBancoResponse>> listar(
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
        return catBancoAPIService.listar(pagina, tamano, busqueda, sortBy, sortDir);
    }

    /**
     * Crea un nuevo banco.
     *
     * @param request Datos del banco a crear.
     * @return Banco creado envuelto en BaseResponse.
     */
    @Operation(operationId = "crear-banco", summary = "Crea un nuevo banco",
        description = "Registra un nuevo banco en el catalogo.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatBancoResponse.class)))
    })
    @PostMapping(value = UrlConst.BANCO_CREAR,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatBancoResponse> crear(@Valid @RequestBody final CatBancoRequest request) {
        return catBancoAPIService.crear(request);
    }

    /**
     * Busca un banco por su clave.
     *
     * @param cveBanco Clave del banco.
     * @return Banco encontrado envuelto en BaseResponse.
     */
    @Operation(operationId = "buscar-banco", summary = "Busca un banco por clave",
        description = "Retorna la informacion de un banco a partir de su clave.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatBancoResponse.class)))
    })
    @GetMapping(value = UrlConst.BANCO_BUSCAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatBancoResponse> findById(
            @Parameter(description = "Clave del banco") @PathVariable final String cveBanco) {
        return catBancoAPIService.findById(cveBanco);
    }

    /**
     * Actualiza los campos de un banco existente.
     *
     * @param cveBanco Clave del banco.
     * @param request Datos a actualizar.
     * @return Banco actualizado envuelto en BaseResponse.
     */
    @Operation(operationId = "actualizar-banco", summary = "Actualiza un banco",
        description = "Actualiza los campos no nulos de un banco existente.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatBancoResponse.class)))
    })
    @PutMapping(value = UrlConst.BANCO_ACTUALIZAR,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatBancoResponse> actualizar(
            @Parameter(description = "Clave del banco") @PathVariable final String cveBanco,
            @RequestBody final CatBancoRequest request) {
        return catBancoAPIService.actualizar(cveBanco, request);
    }
}
