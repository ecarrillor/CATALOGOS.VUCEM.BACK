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
import mx.gob.sat.catalogo.controller.request.tipoproductottra.CatTipoProductoTtraRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.tipoproductottra.CatTipoProductoTtraResponse;
import mx.gob.sat.catalogo.service.api.tipoproductottra.CatTipoProductoTtraAPIService;
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
 * <b>Class:</b> CatTipoProductoTtraController.java <br>
 * <b>Description:</b>
 * <p>Controlador REST para el catalogo de tipos de producto ttra.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Controlador
 */
@Tag(name = "Cat. Tipo Producto Ttra", description = "...")
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class CatTipoProductoTtraController extends ApiBaseController {

    private final CatTipoProductoTtraAPIService catTipoProductoTtraAPIService;

    @Operation(operationId = "listar-tipo-producto-ttra", summary = "Lista los registros Tipo Producto Ttra paginados", description = "...")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = PaginaResponse.class)))
    })
    @GetMapping(value = UrlConst.TIPO_PRODUCTO_TTRA_LISTAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<PaginaResponse<CatTipoProductoTtraResponse>> listar(
            @RequestParam(defaultValue = "0") final int pagina,
            @RequestParam(defaultValue = "20") final int tamano,
            @RequestParam(required = false) final String busqueda,
            @RequestParam(required = false) final String sortBy,
            @RequestParam(required = false) final String sortDir) {
        return catTipoProductoTtraAPIService.listar(pagina, tamano, busqueda, sortBy, sortDir);
    }

    @Operation(operationId = "crear-tipo-producto-ttra", summary = "Crea un nuevo registro Tipo Producto Ttra")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatTipoProductoTtraResponse.class)))
    })
    @PostMapping(value = UrlConst.TIPO_PRODUCTO_TTRA_CREAR, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatTipoProductoTtraResponse> crear(@Valid @RequestBody final CatTipoProductoTtraRequest request) {
        return catTipoProductoTtraAPIService.crear(request);
    }

    @Operation(operationId = "buscar-tipo-producto-ttra", summary = "Busca un registro Tipo Producto Ttra por id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatTipoProductoTtraResponse.class)))
    })
    @GetMapping(value = UrlConst.TIPO_PRODUCTO_TTRA_BUSCAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatTipoProductoTtraResponse> findById(
            @Parameter(description = "Id Tipo Producto Ttra") @PathVariable final Short idTipoProductoTtra) {
        return catTipoProductoTtraAPIService.findById(idTipoProductoTtra);
    }

    @Operation(operationId = "actualizar-tipo-producto-ttra", summary = "Actualiza un registro Tipo Producto Ttra")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatTipoProductoTtraResponse.class)))
    })
    @PutMapping(value = UrlConst.TIPO_PRODUCTO_TTRA_ACTUALIZAR, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatTipoProductoTtraResponse> actualizar(
            @Parameter(description = "Id Tipo Producto Ttra") @PathVariable final Short idTipoProductoTtra,
            @RequestBody final CatTipoProductoTtraRequest request) {
        return catTipoProductoTtraAPIService.actualizar(idTipoProductoTtra, request);
    }
}
