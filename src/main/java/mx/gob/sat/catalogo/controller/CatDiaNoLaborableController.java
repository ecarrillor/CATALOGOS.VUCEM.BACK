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
import mx.gob.sat.catalogo.controller.request.dianolaborable.CatDiaNoLaborableRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.dianolaborable.CatDiaNoLaborableResponse;
import mx.gob.sat.catalogo.service.api.dianolaborable.CatDiaNoLaborableAPIService;
import mx.gob.sat.catalogo.util.consts.UrlConst;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;

@Tag(name = "Cat. Dia No Laborable", description = "Operaciones sobre el catalogo de dias no laborables")
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class CatDiaNoLaborableController extends ApiBaseController {

    private final CatDiaNoLaborableAPIService catDiaNoLaborableAPIService;

    @Operation(operationId = "listar-dia-no-laborable", summary = "Lista los registros Dia No Laborable paginados", description = "Retorna una pagina de registros del catalogo de dias no laborables")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
        content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = PaginaResponse.class))) })
    @GetMapping(value = UrlConst.DIA_NO_LABORABLE_LISTAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<PaginaResponse<CatDiaNoLaborableResponse>> listar(
            @RequestParam(defaultValue = "0") final int pagina,
            @RequestParam(defaultValue = "20") final int tamano,
            @RequestParam(required = false) final String busqueda,
            @RequestParam(required = false) final String sortBy,
            @RequestParam(required = false) final String sortDir) {
        return catDiaNoLaborableAPIService.listar(pagina, tamano, busqueda, sortBy, sortDir);
    }

    @Operation(operationId = "crear-dia-no-laborable", summary = "Crea un nuevo registro Dia No Laborable")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
        content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CatDiaNoLaborableResponse.class))) })
    @PostMapping(value = UrlConst.DIA_NO_LABORABLE_CREAR, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatDiaNoLaborableResponse> crear(@Valid @RequestBody final CatDiaNoLaborableRequest request) {
        return catDiaNoLaborableAPIService.crear(request);
    }

    @Operation(operationId = "buscar-dia-no-laborable", summary = "Busca un registro Dia No Laborable por id")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
        content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CatDiaNoLaborableResponse.class))) })
    @GetMapping(value = UrlConst.DIA_NO_LABORABLE_BUSCAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatDiaNoLaborableResponse> findById(
            @Parameter(description = "Fecha No Laborable") @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final LocalDate fecNoLaborable,
            @Parameter(description = "Clave Calendario") @PathVariable final String cveCalendario) {
        return catDiaNoLaborableAPIService.findById(fecNoLaborable, cveCalendario);
    }

    @Operation(operationId = "actualizar-dia-no-laborable", summary = "Actualiza un registro Dia No Laborable")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
        content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CatDiaNoLaborableResponse.class))) })
    @PutMapping(value = UrlConst.DIA_NO_LABORABLE_ACTUALIZAR, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatDiaNoLaborableResponse> actualizar(
            @Parameter(description = "Fecha No Laborable") @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final LocalDate fecNoLaborable,
            @Parameter(description = "Clave Calendario") @PathVariable final String cveCalendario,
            @RequestBody final CatDiaNoLaborableRequest request) {
        return catDiaNoLaborableAPIService.actualizar(fecNoLaborable, cveCalendario, request);
    }
}
