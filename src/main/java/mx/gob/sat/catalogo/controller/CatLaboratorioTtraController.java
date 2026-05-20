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
import mx.gob.sat.catalogo.controller.request.laboratoriottra.CatLaboratorioTtraRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.laboratoriottra.CatLaboratorioTtraResponse;
import mx.gob.sat.catalogo.service.api.laboratoriottra.CatLaboratorioTtraAPIService;
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

@Tag(name = "Cat. Laboratorio Ttra", description = "...")
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class CatLaboratorioTtraController extends ApiBaseController {

    private final CatLaboratorioTtraAPIService catLaboratorioTtraAPIService;

    @Operation(operationId = "listar-laboratorio-ttra", summary = "Lista los registros Laboratorio Ttra paginados", description = "...")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = PaginaResponse.class)))
    })
    @GetMapping(value = UrlConst.LABORATORIO_TTRA_LISTAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<PaginaResponse<CatLaboratorioTtraResponse>> listar(
            @RequestParam(defaultValue = "0") final int pagina,
            @RequestParam(defaultValue = "20") final int tamano,
            @RequestParam(required = false) final String busqueda,
            @RequestParam(required = false) final String sortBy,
            @RequestParam(required = false) final String sortDir) {
        return catLaboratorioTtraAPIService.listar(pagina, tamano, busqueda, sortBy, sortDir);
    }

    @Operation(operationId = "crear-laboratorio-ttra", summary = "Crea un nuevo registro Laboratorio Ttra")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatLaboratorioTtraResponse.class)))
    })
    @PostMapping(value = UrlConst.LABORATORIO_TTRA_CREAR, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatLaboratorioTtraResponse> crear(@Valid @RequestBody final CatLaboratorioTtraRequest request) {
        return catLaboratorioTtraAPIService.crear(request);
    }

    @Operation(operationId = "buscar-laboratorio-ttra", summary = "Busca un registro Laboratorio Ttra por id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatLaboratorioTtraResponse.class)))
    })
    @GetMapping(value = UrlConst.LABORATORIO_TTRA_BUSCAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatLaboratorioTtraResponse> findById(
            @Parameter(description = "Id Laboratorio Ttra") @PathVariable final Long idLaboratorioTtra) {
        return catLaboratorioTtraAPIService.findById(idLaboratorioTtra);
    }

    @Operation(operationId = "actualizar-laboratorio-ttra", summary = "Actualiza un registro Laboratorio Ttra")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatLaboratorioTtraResponse.class)))
    })
    @PutMapping(value = UrlConst.LABORATORIO_TTRA_ACTUALIZAR, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatLaboratorioTtraResponse> actualizar(
            @Parameter(description = "Id Laboratorio Ttra") @PathVariable final Long idLaboratorioTtra,
            @RequestBody final CatLaboratorioTtraRequest request) {
        return catLaboratorioTtraAPIService.actualizar(idLaboratorioTtra, request);
    }
}
