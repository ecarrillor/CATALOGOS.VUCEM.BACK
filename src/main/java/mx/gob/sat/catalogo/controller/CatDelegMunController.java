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
import mx.gob.sat.catalogo.controller.request.delegmun.CatDelegMunRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.delegmun.CatDelegMunResponse;
import mx.gob.sat.catalogo.service.api.delegmun.CatDelegMunAPIService;
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
 * <b>Class:</b> CatDelegMunController.java <br>
 * <b>Description:</b>
 * <p>Controlador REST para el catalogo de delegacion municipio.
 * Expone endpoints bajo la ruta base {@code api/catalogo-admin/deleg-mun}.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Controlador
 */
@Tag(name = "Cat. Deleg Mun",
        description = "Servicios de consulta y mantenimiento del catalogo de delegacion municipio")
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class CatDelegMunController extends ApiBaseController {

    /** Servicio de negocio para el catalogo de delegacion municipio. */
    private final CatDelegMunAPIService catDelegMunAPIService;

    @Operation(operationId = "listar-deleg-mun", summary = "Lista los registros de delegacion municipio paginados",
        description = "Retorna una pagina de registros de delegacion municipio con soporte de busqueda y ordenamiento.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = PaginaResponse.class)))
    })
    @GetMapping(value = UrlConst.DELEG_MUN_LISTAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<PaginaResponse<CatDelegMunResponse>> listar(
            @RequestParam(defaultValue = "0") final int pagina,
            @RequestParam(defaultValue = "20") final int tamano,
            @RequestParam(required = false) final String busqueda,
            @RequestParam(required = false) final String sortBy,
            @RequestParam(required = false) final String sortDir) {
        return catDelegMunAPIService.listar(pagina, tamano, busqueda, sortBy, sortDir);
    }

    @Operation(operationId = "crear-deleg-mun", summary = "Crea un nuevo registro de delegacion municipio")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatDelegMunResponse.class)))
    })
    @PostMapping(value = UrlConst.DELEG_MUN_CREAR,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatDelegMunResponse> crear(@Valid @RequestBody final CatDelegMunRequest request) {
        return catDelegMunAPIService.crear(request);
    }

    @Operation(operationId = "buscar-deleg-mun", summary = "Busca un registro de delegacion municipio por clave")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatDelegMunResponse.class)))
    })
    @GetMapping(value = UrlConst.DELEG_MUN_BUSCAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatDelegMunResponse> findById(
            @Parameter(description = "Clave de delegacion municipio") @PathVariable final String cveDelegMun) {
        return catDelegMunAPIService.findById(cveDelegMun);
    }

    @Operation(operationId = "actualizar-deleg-mun", summary = "Actualiza un registro de delegacion municipio")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatDelegMunResponse.class)))
    })
    @PutMapping(value = UrlConst.DELEG_MUN_ACTUALIZAR,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatDelegMunResponse> actualizar(
            @Parameter(description = "Clave de delegacion municipio") @PathVariable final String cveDelegMun,
            @RequestBody final CatDelegMunRequest request) {
        return catDelegMunAPIService.actualizar(cveDelegMun, request);
    }
}
