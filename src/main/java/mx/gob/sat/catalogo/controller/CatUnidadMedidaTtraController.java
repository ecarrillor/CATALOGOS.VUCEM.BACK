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
import mx.gob.sat.catalogo.controller.request.unidadmedidattra.CatUnidadMedidaTtraRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.unidadmedidattra.CatUnidadMedidaTtraResponse;
import mx.gob.sat.catalogo.service.api.unidadmedidattra.CatUnidadMedidaTtraAPIService;
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
 * <b>Class:</b> CatUnidadMedidaTtraController.java <br>
 * <b>Description:</b>
 * <p>Controlador REST para el catalogo de unidad de medida ttra.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Controlador
 */
@Tag(name = "Cat. Unidad Medida Ttra", description = "...")
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class CatUnidadMedidaTtraController extends ApiBaseController {

    private final CatUnidadMedidaTtraAPIService catUnidadMedidaTtraAPIService;

    @Operation(operationId = "listar-unidad-medida-ttra", summary = "Lista los registros Unidad Medida Ttra paginados", description = "...")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = PaginaResponse.class)))
    })
    @GetMapping(value = UrlConst.UNIDAD_MEDIDA_TTRA_LISTAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<PaginaResponse<CatUnidadMedidaTtraResponse>> listar(
            @RequestParam(defaultValue = "0") final int pagina,
            @RequestParam(defaultValue = "20") final int tamano,
            @RequestParam(required = false) final String busqueda,
            @RequestParam(required = false) final String sortBy,
            @RequestParam(required = false) final String sortDir) {
        return catUnidadMedidaTtraAPIService.listar(pagina, tamano, busqueda, sortBy, sortDir);
    }

    @Operation(operationId = "crear-unidad-medida-ttra", summary = "Crea un nuevo registro Unidad Medida Ttra")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatUnidadMedidaTtraResponse.class)))
    })
    @PostMapping(value = UrlConst.UNIDAD_MEDIDA_TTRA_CREAR, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatUnidadMedidaTtraResponse> crear(@Valid @RequestBody final CatUnidadMedidaTtraRequest request) {
        return catUnidadMedidaTtraAPIService.crear(request);
    }

    @Operation(operationId = "buscar-unidad-medida-ttra", summary = "Busca un registro Unidad Medida Ttra por id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatUnidadMedidaTtraResponse.class)))
    })
    @GetMapping(value = UrlConst.UNIDAD_MEDIDA_TTRA_BUSCAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatUnidadMedidaTtraResponse> findById(
            @Parameter(description = "Id Unidad Medida Ttra") @PathVariable final Integer idUnidadMedidaTtra) {
        return catUnidadMedidaTtraAPIService.findById(idUnidadMedidaTtra);
    }

    @Operation(operationId = "actualizar-unidad-medida-ttra", summary = "Actualiza un registro Unidad Medida Ttra")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatUnidadMedidaTtraResponse.class)))
    })
    @PutMapping(value = UrlConst.UNIDAD_MEDIDA_TTRA_ACTUALIZAR, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatUnidadMedidaTtraResponse> actualizar(
            @Parameter(description = "Id Unidad Medida Ttra") @PathVariable final Integer idUnidadMedidaTtra,
            @RequestBody final CatUnidadMedidaTtraRequest request) {
        return catUnidadMedidaTtraAPIService.actualizar(idUnidadMedidaTtra, request);
    }
}
