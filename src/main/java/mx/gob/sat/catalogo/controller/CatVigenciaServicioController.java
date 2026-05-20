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
import mx.gob.sat.catalogo.controller.request.vigenciaservicio.CatVigenciaServicioRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.vigenciaservicio.CatVigenciaServicioResponse;
import mx.gob.sat.catalogo.service.api.vigenciaservicio.CatVigenciaServicioAPIService;
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

@Tag(name = "Cat. Vigencia Servicio", description = "...")
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class CatVigenciaServicioController extends ApiBaseController {

    private final CatVigenciaServicioAPIService catVigenciaServicioAPIService;

    @Operation(operationId = "listar-vigencia-servicio", summary = "Lista los registros Vigencia Servicio paginados")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = PaginaResponse.class)))
    })
    @GetMapping(value = UrlConst.VIGENCIA_SERVICIO_LISTAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<PaginaResponse<CatVigenciaServicioResponse>> listar(
            @RequestParam(defaultValue = "0") final int pagina,
            @RequestParam(defaultValue = "20") final int tamano,
            @RequestParam(required = false) final String busqueda,
            @RequestParam(required = false) final String sortBy,
            @RequestParam(required = false) final String sortDir) {
        return catVigenciaServicioAPIService.listar(pagina, tamano, busqueda, sortBy, sortDir);
    }

    @Operation(operationId = "crear-vigencia-servicio", summary = "Crea un nuevo registro Vigencia Servicio")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatVigenciaServicioResponse.class)))
    })
    @PostMapping(value = UrlConst.VIGENCIA_SERVICIO_CREAR, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatVigenciaServicioResponse> crear(@Valid @RequestBody final CatVigenciaServicioRequest request) {
        return catVigenciaServicioAPIService.crear(request);
    }

    @Operation(operationId = "buscar-vigencia-servicio", summary = "Busca un registro Vigencia Servicio por id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatVigenciaServicioResponse.class)))
    })
    @GetMapping(value = UrlConst.VIGENCIA_SERVICIO_BUSCAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatVigenciaServicioResponse> findById(
            @Parameter(description = "Id Vigencia Servicio") @PathVariable final Short idVigenciaServicio) {
        return catVigenciaServicioAPIService.findById(idVigenciaServicio);
    }

    @Operation(operationId = "actualizar-vigencia-servicio", summary = "Actualiza un registro Vigencia Servicio")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatVigenciaServicioResponse.class)))
    })
    @PutMapping(value = UrlConst.VIGENCIA_SERVICIO_ACTUALIZAR, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatVigenciaServicioResponse> actualizar(
            @Parameter(description = "Id Vigencia Servicio") @PathVariable final Short idVigenciaServicio,
            @RequestBody final CatVigenciaServicioRequest request) {
        return catVigenciaServicioAPIService.actualizar(idVigenciaServicio, request);
    }
}
