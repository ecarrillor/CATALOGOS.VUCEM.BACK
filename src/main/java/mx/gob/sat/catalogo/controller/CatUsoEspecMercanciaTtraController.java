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
import mx.gob.sat.catalogo.controller.request.usoespecmercanciattra.CatUsoEspecMercanciaTtraRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.usoespecmercanciattra.CatUsoEspecMercanciaTtraResponse;
import mx.gob.sat.catalogo.service.api.usoespecmercanciattra.CatUsoEspecMercanciaTtraAPIService;
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
 * <b>Class:</b> CatUsoEspecMercanciaTtraController.java <br>
 * <b>Description:</b>
 * <p>Controlador REST para el catalogo de uso especial de mercancia ttra.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Controlador
 */
@Tag(name = "Cat. Uso Espec Mercancia Ttra", description = "...")
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class CatUsoEspecMercanciaTtraController extends ApiBaseController {

    private final CatUsoEspecMercanciaTtraAPIService catUsoEspecMercanciaTtraAPIService;

    @Operation(operationId = "listar-uso-espec-mercancia-ttra", summary = "Lista los registros Uso Espec Mercancia Ttra paginados", description = "...")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = PaginaResponse.class)))
    })
    @GetMapping(value = UrlConst.USO_ESPEC_MERCANCIA_TTRA_LISTAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<PaginaResponse<CatUsoEspecMercanciaTtraResponse>> listar(
            @RequestParam(defaultValue = "0") final int pagina,
            @RequestParam(defaultValue = "20") final int tamano,
            @RequestParam(required = false) final String busqueda,
            @RequestParam(required = false) final String sortBy,
            @RequestParam(required = false) final String sortDir) {
        return catUsoEspecMercanciaTtraAPIService.listar(pagina, tamano, busqueda, sortBy, sortDir);
    }

    @Operation(operationId = "crear-uso-espec-mercancia-ttra", summary = "Crea un nuevo registro Uso Espec Mercancia Ttra")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatUsoEspecMercanciaTtraResponse.class)))
    })
    @PostMapping(value = UrlConst.USO_ESPEC_MERCANCIA_TTRA_CREAR, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatUsoEspecMercanciaTtraResponse> crear(@Valid @RequestBody final CatUsoEspecMercanciaTtraRequest request) {
        return catUsoEspecMercanciaTtraAPIService.crear(request);
    }

    @Operation(operationId = "buscar-uso-espec-mercancia-ttra", summary = "Busca un registro Uso Espec Mercancia Ttra por id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatUsoEspecMercanciaTtraResponse.class)))
    })
    @GetMapping(value = UrlConst.USO_ESPEC_MERCANCIA_TTRA_BUSCAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatUsoEspecMercanciaTtraResponse> findById(
            @Parameter(description = "Id Uso Espec Mercancia Ttra") @PathVariable final Short idUsoEspecMercanciaTtra) {
        return catUsoEspecMercanciaTtraAPIService.findById(idUsoEspecMercanciaTtra);
    }

    @Operation(operationId = "actualizar-uso-espec-mercancia-ttra", summary = "Actualiza un registro Uso Espec Mercancia Ttra")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatUsoEspecMercanciaTtraResponse.class)))
    })
    @PutMapping(value = UrlConst.USO_ESPEC_MERCANCIA_TTRA_ACTUALIZAR, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatUsoEspecMercanciaTtraResponse> actualizar(
            @Parameter(description = "Id Uso Espec Mercancia Ttra") @PathVariable final Short idUsoEspecMercanciaTtra,
            @RequestBody final CatUsoEspecMercanciaTtraRequest request) {
        return catUsoEspecMercanciaTtraAPIService.actualizar(idUsoEspecMercanciaTtra, request);
    }
}
