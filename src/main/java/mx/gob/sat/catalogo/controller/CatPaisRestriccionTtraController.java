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
import mx.gob.sat.catalogo.controller.request.paisrestriccionttra.CatPaisRestriccionTtraRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.paisrestriccionttra.CatPaisRestriccionTtraResponse;
import mx.gob.sat.catalogo.service.api.paisrestriccionttra.CatPaisRestriccionTtraAPIService;
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
 * <b>Class:</b> CatPaisRestriccionTtraController.java <br>
 * <b>Description:</b>
 * <p>Controlador REST para el catalogo de paises restriccion TTRA.
 * Expone endpoints bajo la ruta base {@code api/catalogo-admin/pais-restriccion-ttra}.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Controlador
 */
@Tag(name = "Cat. Pais Restriccion TTRA",
        description = "Servicios de consulta y mantenimiento del catalogo de paises restriccion TTRA")
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class CatPaisRestriccionTtraController extends ApiBaseController {

    /** Servicio de negocio para el catalogo de paises restriccion TTRA. */
    private final CatPaisRestriccionTtraAPIService catPaisRestriccionTtraAPIService;

    @Operation(operationId = "listar-pais-restriccion-ttra", summary = "Lista los registros de pais restriccion TTRA paginados",
            description = "Retorna una pagina de registros de pais restriccion TTRA con soporte de busqueda y ordenamiento.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = PaginaResponse.class)))
    })
    @GetMapping(value = UrlConst.PAIS_RESTRICCION_TTRA_LISTAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<PaginaResponse<CatPaisRestriccionTtraResponse>> listar(
            @RequestParam(defaultValue = "0") final int pagina,
            @RequestParam(defaultValue = "20") final int tamano,
            @RequestParam(required = false) final String busqueda,
            @RequestParam(required = false) final String sortBy,
            @RequestParam(required = false) final String sortDir) {
        return catPaisRestriccionTtraAPIService.listar(pagina, tamano, busqueda, sortBy, sortDir);
    }

    @Operation(operationId = "crear-pais-restriccion-ttra", summary = "Crea un nuevo registro de pais restriccion TTRA")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatPaisRestriccionTtraResponse.class)))
    })
    @PostMapping(value = UrlConst.PAIS_RESTRICCION_TTRA_CREAR,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatPaisRestriccionTtraResponse> crear(
            @Valid @RequestBody final CatPaisRestriccionTtraRequest request) {
        return catPaisRestriccionTtraAPIService.crear(request);
    }

    @Operation(operationId = "buscar-pais-restriccion-ttra", summary = "Busca un registro de pais restriccion TTRA por identificador")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatPaisRestriccionTtraResponse.class)))
    })
    @GetMapping(value = UrlConst.PAIS_RESTRICCION_TTRA_BUSCAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatPaisRestriccionTtraResponse> findById(
            @Parameter(description = "Identificador de pais restriccion TTRA")
            @PathVariable final Integer idPaisRestriccionTtra) {
        return catPaisRestriccionTtraAPIService.findById(idPaisRestriccionTtra);
    }

    @Operation(operationId = "actualizar-pais-restriccion-ttra", summary = "Actualiza un registro de pais restriccion TTRA")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatPaisRestriccionTtraResponse.class)))
    })
    @PutMapping(value = UrlConst.PAIS_RESTRICCION_TTRA_ACTUALIZAR,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatPaisRestriccionTtraResponse> actualizar(
            @Parameter(description = "Identificador de pais restriccion TTRA")
            @PathVariable final Integer idPaisRestriccionTtra,
            @RequestBody final CatPaisRestriccionTtraRequest request) {
        return catPaisRestriccionTtraAPIService.actualizar(idPaisRestriccionTtra, request);
    }
}
