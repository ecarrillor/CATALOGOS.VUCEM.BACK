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
import mx.gob.sat.catalogo.controller.request.leyendatexto.CatLeyendaTextoRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.leyendatexto.CatLeyendaTextoResponse;
import mx.gob.sat.catalogo.service.api.leyendatexto.CatLeyendaTextoAPIService;
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
 * <b>Class:</b> CatLeyendaTextoController.java <br>
 * <b>Description:</b>
 * <p>Controlador REST para el catalogo Leyenda Texto.
 * Expone endpoints bajo la ruta base {@code api/catalogo-admin/leyenda-texto}.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Controlador
 */
@Tag(name = "Cat. Leyenda Texto",
        description = "Servicios de consulta y mantenimiento del catalogo Leyenda Texto")
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class CatLeyendaTextoController extends ApiBaseController {

    private final CatLeyendaTextoAPIService catLeyendaTextoAPIService;

    @Operation(operationId = "listar-leyenda-texto", summary = "Lista los registros Leyenda Texto paginados",
        description = "Retorna una pagina de registros Leyenda Texto con soporte de busqueda y ordenamiento.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = PaginaResponse.class)))
    })
    @GetMapping(value = UrlConst.LEYENDA_TEXTO_LISTAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<PaginaResponse<CatLeyendaTextoResponse>> listar(
            @RequestParam(defaultValue = "0") final int pagina,
            @RequestParam(defaultValue = "20") final int tamano,
            @RequestParam(required = false) final String busqueda,
            @RequestParam(required = false) final String sortBy,
            @RequestParam(required = false) final String sortDir) {
        return catLeyendaTextoAPIService.listar(pagina, tamano, busqueda, sortBy, sortDir);
    }

    @Operation(operationId = "crear-leyenda-texto", summary = "Crea un nuevo registro Leyenda Texto")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatLeyendaTextoResponse.class)))
    })
    @PostMapping(value = UrlConst.LEYENDA_TEXTO_CREAR,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatLeyendaTextoResponse> crear(@Valid @RequestBody final CatLeyendaTextoRequest request) {
        return catLeyendaTextoAPIService.crear(request);
    }

    @Operation(operationId = "buscar-leyenda-texto", summary = "Busca un registro Leyenda Texto por id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatLeyendaTextoResponse.class)))
    })
    @GetMapping(value = UrlConst.LEYENDA_TEXTO_BUSCAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatLeyendaTextoResponse> findById(
            @Parameter(description = "Id Leyenda Texto") @PathVariable final Long idLeyendaTexto) {
        return catLeyendaTextoAPIService.findById(idLeyendaTexto);
    }

    @Operation(operationId = "actualizar-leyenda-texto", summary = "Actualiza un registro Leyenda Texto")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatLeyendaTextoResponse.class)))
    })
    @PutMapping(value = UrlConst.LEYENDA_TEXTO_ACTUALIZAR,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatLeyendaTextoResponse> actualizar(
            @Parameter(description = "Id Leyenda Texto") @PathVariable final Long idLeyendaTexto,
            @RequestBody final CatLeyendaTextoRequest request) {
        return catLeyendaTextoAPIService.actualizar(idLeyendaTexto, request);
    }
}
