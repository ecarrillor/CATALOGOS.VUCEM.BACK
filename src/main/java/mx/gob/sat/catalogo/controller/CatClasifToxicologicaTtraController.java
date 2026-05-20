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
import mx.gob.sat.catalogo.controller.request.clasiftoxicologicattra.CatClasifToxicologicaTtraRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.clasiftoxicologicattra.CatClasifToxicologicaTtraResponse;
import mx.gob.sat.catalogo.service.api.clasiftoxicologicattra.CatClasifToxicologicaTtraAPIService;
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
 * <b>Class:</b> CatClasifToxicologicaTtraController.java <br>
 * <b>Description:</b>
 * <p>Controlador REST para el catalogo de clasificaciones toxicologicas ttra.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Controlador
 */
@Tag(name = "Cat. Clasif Toxicologica Ttra", description = "...")
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class CatClasifToxicologicaTtraController extends ApiBaseController {

    private final CatClasifToxicologicaTtraAPIService catClasifToxicologicaTtraAPIService;

    @Operation(operationId = "listar-clasif-toxicologica-ttra", summary = "Lista los registros Clasif Toxicologica Ttra paginados", description = "...")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = PaginaResponse.class)))
    })
    @GetMapping(value = UrlConst.CLASIF_TOXICOLOGICA_TTRA_LISTAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<PaginaResponse<CatClasifToxicologicaTtraResponse>> listar(
            @RequestParam(defaultValue = "0") final int pagina,
            @RequestParam(defaultValue = "20") final int tamano,
            @RequestParam(required = false) final String busqueda,
            @RequestParam(required = false) final String sortBy,
            @RequestParam(required = false) final String sortDir) {
        return catClasifToxicologicaTtraAPIService.listar(pagina, tamano, busqueda, sortBy, sortDir);
    }

    @Operation(operationId = "crear-clasif-toxicologica-ttra", summary = "Crea un nuevo registro Clasif Toxicologica Ttra")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatClasifToxicologicaTtraResponse.class)))
    })
    @PostMapping(value = UrlConst.CLASIF_TOXICOLOGICA_TTRA_CREAR, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatClasifToxicologicaTtraResponse> crear(@Valid @RequestBody final CatClasifToxicologicaTtraRequest request) {
        return catClasifToxicologicaTtraAPIService.crear(request);
    }

    @Operation(operationId = "buscar-clasif-toxicologica-ttra", summary = "Busca un registro Clasif Toxicologica Ttra por id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatClasifToxicologicaTtraResponse.class)))
    })
    @GetMapping(value = UrlConst.CLASIF_TOXICOLOGICA_TTRA_BUSCAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatClasifToxicologicaTtraResponse> findById(
            @Parameter(description = "Id Clasif Toxicologica Ttra") @PathVariable final Short idClasifToxicologicaTtra) {
        return catClasifToxicologicaTtraAPIService.findById(idClasifToxicologicaTtra);
    }

    @Operation(operationId = "actualizar-clasif-toxicologica-ttra", summary = "Actualiza un registro Clasif Toxicologica Ttra")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatClasifToxicologicaTtraResponse.class)))
    })
    @PutMapping(value = UrlConst.CLASIF_TOXICOLOGICA_TTRA_ACTUALIZAR, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatClasifToxicologicaTtraResponse> actualizar(
            @Parameter(description = "Id Clasif Toxicologica Ttra") @PathVariable final Short idClasifToxicologicaTtra,
            @RequestBody final CatClasifToxicologicaTtraRequest request) {
        return catClasifToxicologicaTtraAPIService.actualizar(idClasifToxicologicaTtra, request);
    }
}
