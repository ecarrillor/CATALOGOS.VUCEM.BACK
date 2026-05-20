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
import mx.gob.sat.catalogo.controller.request.empresarecif.CatEmpresaRecifRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.empresarecif.CatEmpresaRecifResponse;
import mx.gob.sat.catalogo.service.api.empresarecif.CatEmpresaRecifAPIService;
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
 * <b>Class:</b> CatEmpresaRecifController.java <br>
 * <b>Description:</b>
 * <p>Controlador REST para el catalogo de empresas RECIF.
 * Expone endpoints bajo la ruta base {@code api/catalogo-admin/empresa-recif}.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Controlador
 */
@Tag(name = "Cat. Empresa RECIF",
        description = "Servicios de consulta y mantenimiento del catalogo de empresas RECIF")
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class CatEmpresaRecifController extends ApiBaseController {

    /** Servicio de negocio para el catalogo de empresas RECIF. */
    private final CatEmpresaRecifAPIService catEmpresaRecifAPIService;

    @Operation(operationId = "listar-empresa-recif", summary = "Lista los registros de empresa RECIF paginados",
            description = "Retorna una pagina de registros de empresa RECIF con soporte de busqueda y ordenamiento.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = PaginaResponse.class)))
    })
    @GetMapping(value = UrlConst.EMPRESA_RECIF_LISTAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<PaginaResponse<CatEmpresaRecifResponse>> listar(
            @RequestParam(defaultValue = "0") final int pagina,
            @RequestParam(defaultValue = "20") final int tamano,
            @RequestParam(required = false) final String busqueda,
            @RequestParam(required = false) final String sortBy,
            @RequestParam(required = false) final String sortDir) {
        return catEmpresaRecifAPIService.listar(pagina, tamano, busqueda, sortBy, sortDir);
    }

    @Operation(operationId = "crear-empresa-recif", summary = "Crea un nuevo registro de empresa RECIF")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatEmpresaRecifResponse.class)))
    })
    @PostMapping(value = UrlConst.EMPRESA_RECIF_CREAR,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatEmpresaRecifResponse> crear(@Valid @RequestBody final CatEmpresaRecifRequest request) {
        return catEmpresaRecifAPIService.crear(request);
    }

    @Operation(operationId = "buscar-empresa-recif", summary = "Busca un registro de empresa RECIF por clave RECIF")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatEmpresaRecifResponse.class)))
    })
    @GetMapping(value = UrlConst.EMPRESA_RECIF_BUSCAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatEmpresaRecifResponse> findById(
            @Parameter(description = "Clave RECIF") @PathVariable final String recif) {
        return catEmpresaRecifAPIService.findById(recif);
    }

    @Operation(operationId = "actualizar-empresa-recif", summary = "Actualiza un registro de empresa RECIF")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatEmpresaRecifResponse.class)))
    })
    @PutMapping(value = UrlConst.EMPRESA_RECIF_ACTUALIZAR,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatEmpresaRecifResponse> actualizar(
            @Parameter(description = "Clave RECIF") @PathVariable final String recif,
            @RequestBody final CatEmpresaRecifRequest request) {
        return catEmpresaRecifAPIService.actualizar(recif, request);
    }
}
