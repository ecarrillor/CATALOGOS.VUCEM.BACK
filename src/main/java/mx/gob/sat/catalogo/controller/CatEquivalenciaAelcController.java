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
import mx.gob.sat.catalogo.controller.request.equivalenciaaelc.CatEquivalenciaAelcRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.equivalenciaaelc.CatEquivalenciaAelcResponse;
import mx.gob.sat.catalogo.service.api.equivalenciaaelc.CatEquivalenciaAelcAPIService;
import mx.gob.sat.catalogo.util.consts.UrlConst;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;

@Tag(name = "Cat. Equivalencia AELC", description = "Operaciones sobre el catalogo de equivalencias AELC")
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class CatEquivalenciaAelcController extends ApiBaseController {

    private final CatEquivalenciaAelcAPIService catEquivalenciaAelcAPIService;

    @Operation(operationId = "listar-equivalencia-aelc", summary = "Lista los registros Equivalencia AELC paginados", description = "Retorna una pagina de registros del catalogo de equivalencias AELC")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
        content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = PaginaResponse.class))) })
    @GetMapping(value = UrlConst.EQUIVALENCIA_AELC_LISTAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<PaginaResponse<CatEquivalenciaAelcResponse>> listar(
            @RequestParam(defaultValue = "0") final int pagina,
            @RequestParam(defaultValue = "20") final int tamano,
            @RequestParam(required = false) final String busqueda,
            @RequestParam(required = false) final String sortBy,
            @RequestParam(required = false) final String sortDir) {
        return catEquivalenciaAelcAPIService.listar(pagina, tamano, busqueda, sortBy, sortDir);
    }

    @Operation(operationId = "crear-equivalencia-aelc", summary = "Crea un nuevo registro Equivalencia AELC")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
        content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CatEquivalenciaAelcResponse.class))) })
    @PostMapping(value = UrlConst.EQUIVALENCIA_AELC_CREAR, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatEquivalenciaAelcResponse> crear(@Valid @RequestBody final CatEquivalenciaAelcRequest request) {
        return catEquivalenciaAelcAPIService.crear(request);
    }

    @Operation(operationId = "buscar-equivalencia-aelc", summary = "Busca un registro Equivalencia AELC por id")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
        content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CatEquivalenciaAelcResponse.class))) })
    @GetMapping(value = UrlConst.EQUIVALENCIA_AELC_BUSCAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatEquivalenciaAelcResponse> findById(
            @Parameter(description = "Fecha Inicio Vigencia") @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final LocalDate fecIniVigencia,
            @Parameter(description = "Clave Moneda") @PathVariable final String cveMoneda) {
        return catEquivalenciaAelcAPIService.findById(fecIniVigencia, cveMoneda);
    }

    @Operation(operationId = "actualizar-equivalencia-aelc", summary = "Actualiza un registro Equivalencia AELC")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
        content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CatEquivalenciaAelcResponse.class))) })
    @PutMapping(value = UrlConst.EQUIVALENCIA_AELC_ACTUALIZAR, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatEquivalenciaAelcResponse> actualizar(
            @Parameter(description = "Fecha Inicio Vigencia") @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final LocalDate fecIniVigencia,
            @Parameter(description = "Clave Moneda") @PathVariable final String cveMoneda,
            @RequestBody final CatEquivalenciaAelcRequest request) {
        return catEquivalenciaAelcAPIService.actualizar(fecIniVigencia, cveMoneda, request);
    }
}
