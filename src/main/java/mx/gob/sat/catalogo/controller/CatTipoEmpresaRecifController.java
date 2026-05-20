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
import mx.gob.sat.catalogo.controller.request.tipoempresarecif.CatTipoEmpresaRecifRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.tipoempresarecif.CatTipoEmpresaRecifResponse;
import mx.gob.sat.catalogo.service.api.tipoempresarecif.CatTipoEmpresaRecifAPIService;
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
 * <b>Class:</b> CatTipoEmpresaRecifController.java <br>
 * <b>Description:</b>
 * <p>Controlador REST para el catalogo de tipo empresa RECIF.
 * Expone endpoints bajo la ruta base {@code api/catalogo-admin/tipo-empresa-recif}.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Controlador
 */
@Tag(name = "Cat. Tipo Empresa Recif",
        description = "Servicios de consulta y mantenimiento del catalogo de tipo empresa RECIF")
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class CatTipoEmpresaRecifController extends ApiBaseController {

    /** Servicio de negocio para el catalogo de tipo empresa RECIF. */
    private final CatTipoEmpresaRecifAPIService catTipoEmpresaRecifAPIService;

    @Operation(operationId = "listar-tipo-empresa-recif", summary = "Lista los registros de tipo empresa RECIF paginados",
        description = "Retorna una pagina de registros de tipo empresa RECIF con soporte de busqueda y ordenamiento.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = PaginaResponse.class)))
    })
    @GetMapping(value = UrlConst.TIPO_EMPRESA_RECIF_LISTAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<PaginaResponse<CatTipoEmpresaRecifResponse>> listar(
            @RequestParam(defaultValue = "0") final int pagina,
            @RequestParam(defaultValue = "20") final int tamano,
            @RequestParam(required = false) final String busqueda,
            @RequestParam(required = false) final String sortBy,
            @RequestParam(required = false) final String sortDir) {
        return catTipoEmpresaRecifAPIService.listar(pagina, tamano, busqueda, sortBy, sortDir);
    }

    @Operation(operationId = "crear-tipo-empresa-recif", summary = "Crea un nuevo registro de tipo empresa RECIF")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatTipoEmpresaRecifResponse.class)))
    })
    @PostMapping(value = UrlConst.TIPO_EMPRESA_RECIF_CREAR,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatTipoEmpresaRecifResponse> crear(@Valid @RequestBody final CatTipoEmpresaRecifRequest request) {
        return catTipoEmpresaRecifAPIService.crear(request);
    }

    @Operation(operationId = "buscar-tipo-empresa-recif", summary = "Busca un registro de tipo empresa RECIF por clave")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatTipoEmpresaRecifResponse.class)))
    })
    @GetMapping(value = UrlConst.TIPO_EMPRESA_RECIF_BUSCAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatTipoEmpresaRecifResponse> findById(
            @Parameter(description = "Clave del tipo empresa RECIF") @PathVariable final String cveTipoEmpresaRecif) {
        return catTipoEmpresaRecifAPIService.findById(cveTipoEmpresaRecif);
    }

    @Operation(operationId = "actualizar-tipo-empresa-recif", summary = "Actualiza un registro de tipo empresa RECIF")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatTipoEmpresaRecifResponse.class)))
    })
    @PutMapping(value = UrlConst.TIPO_EMPRESA_RECIF_ACTUALIZAR,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatTipoEmpresaRecifResponse> actualizar(
            @Parameter(description = "Clave del tipo empresa RECIF") @PathVariable final String cveTipoEmpresaRecif,
            @RequestBody final CatTipoEmpresaRecifRequest request) {
        return catTipoEmpresaRecifAPIService.actualizar(cveTipoEmpresaRecif, request);
    }
}
