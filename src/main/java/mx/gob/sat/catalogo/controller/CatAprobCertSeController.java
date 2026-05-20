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
import mx.gob.sat.catalogo.controller.request.aprobcertse.CatAprobCertSeRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.aprobcertse.CatAprobCertSeResponse;
import mx.gob.sat.catalogo.service.api.aprobcertse.CatAprobCertSeAPIService;
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
 * <b>Class:</b> CatAprobCertSeController.java <br>
 * <b>Description:</b>
 * <p>Controlador REST para el catalogo de aprobaciones de certificado SE.
 * Expone endpoints bajo la ruta base {@code api/catalogo-admin/aprob-cert-se}.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Controlador
 */
@Tag(name = "Cat. Aprob Cert SE",
        description = "Servicios de consulta y mantenimiento del catalogo de aprobaciones de certificado SE")
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class CatAprobCertSeController extends ApiBaseController {

    /** Servicio de negocio para el catalogo de aprobaciones de certificado SE. */
    private final CatAprobCertSeAPIService catAprobCertSeAPIService;

    @Operation(operationId = "listar-aprob-cert-se", summary = "Lista los registros de aprobacion certificado SE paginados",
            description = "Retorna una pagina de registros de aprobacion certificado SE con soporte de busqueda y ordenamiento.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = PaginaResponse.class)))
    })
    @GetMapping(value = UrlConst.APROB_CERT_SE_LISTAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<PaginaResponse<CatAprobCertSeResponse>> listar(
            @RequestParam(defaultValue = "0") final int pagina,
            @RequestParam(defaultValue = "20") final int tamano,
            @RequestParam(required = false) final String busqueda,
            @RequestParam(required = false) final String sortBy,
            @RequestParam(required = false) final String sortDir) {
        return catAprobCertSeAPIService.listar(pagina, tamano, busqueda, sortBy, sortDir);
    }

    @Operation(operationId = "crear-aprob-cert-se", summary = "Crea un nuevo registro de aprobacion certificado SE")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatAprobCertSeResponse.class)))
    })
    @PostMapping(value = UrlConst.APROB_CERT_SE_CREAR,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatAprobCertSeResponse> crear(@Valid @RequestBody final CatAprobCertSeRequest request) {
        return catAprobCertSeAPIService.crear(request);
    }

    @Operation(operationId = "buscar-aprob-cert-se", summary = "Busca un registro de aprobacion certificado SE por identificador")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatAprobCertSeResponse.class)))
    })
    @GetMapping(value = UrlConst.APROB_CERT_SE_BUSCAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatAprobCertSeResponse> findById(
            @Parameter(description = "Identificador de aprobacion certificado SE") @PathVariable final Short idAprobCertSe) {
        return catAprobCertSeAPIService.findById(idAprobCertSe);
    }

    @Operation(operationId = "actualizar-aprob-cert-se", summary = "Actualiza un registro de aprobacion certificado SE")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatAprobCertSeResponse.class)))
    })
    @PutMapping(value = UrlConst.APROB_CERT_SE_ACTUALIZAR,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatAprobCertSeResponse> actualizar(
            @Parameter(description = "Identificador de aprobacion certificado SE") @PathVariable final Short idAprobCertSe,
            @RequestBody final CatAprobCertSeRequest request) {
        return catAprobCertSeAPIService.actualizar(idAprobCertSe, request);
    }
}
