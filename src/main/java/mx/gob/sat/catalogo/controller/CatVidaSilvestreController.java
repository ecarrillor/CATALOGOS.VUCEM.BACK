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
import mx.gob.sat.catalogo.controller.request.vidasilvestre.CatVidaSilvestreRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.vidasilvestre.CatVidaSilvestreResponse;
import mx.gob.sat.catalogo.service.api.vidasilvestre.CatVidaSilvestreAPIService;
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

@Tag(name = "Cat. Vida Silvestre", description = "Servicios de consulta y mantenimiento del catalogo Vida Silvestre")
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class CatVidaSilvestreController extends ApiBaseController {

    private final CatVidaSilvestreAPIService catVidaSilvestreAPIService;

    @Operation(operationId = "listar-vida-silvestre", summary = "Lista los registros Vida Silvestre paginados", description = "Retorna una pagina de registros Vida Silvestre con soporte de busqueda y ordenamiento.")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA, content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = PaginaResponse.class))) })
    @GetMapping(value = UrlConst.VIDA_SILVESTRE_LISTAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<PaginaResponse<CatVidaSilvestreResponse>> listar(
            @RequestParam(defaultValue = "0") final int pagina,
            @RequestParam(defaultValue = "20") final int tamano,
            @RequestParam(required = false) final String busqueda,
            @RequestParam(required = false) final String sortBy,
            @RequestParam(required = false) final String sortDir) {
        return catVidaSilvestreAPIService.listar(pagina, tamano, busqueda, sortBy, sortDir);
    }

    @Operation(operationId = "crear-vida-silvestre", summary = "Crea un nuevo registro Vida Silvestre", description = "Registra un nuevo registro Vida Silvestre en el catalogo.")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA, content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CatVidaSilvestreResponse.class))) })
    @PostMapping(value = UrlConst.VIDA_SILVESTRE_CREAR, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatVidaSilvestreResponse> crear(@Valid @RequestBody final CatVidaSilvestreRequest request) {
        return catVidaSilvestreAPIService.crear(request);
    }

    @Operation(operationId = "buscar-vida-silvestre", summary = "Busca un registro Vida Silvestre por ID", description = "Retorna la informacion de un registro Vida Silvestre a partir de su ID.")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA, content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CatVidaSilvestreResponse.class))) })
    @GetMapping(value = UrlConst.VIDA_SILVESTRE_BUSCAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatVidaSilvestreResponse> findById(@Parameter(description = "ID Vida Silvestre") @PathVariable final Integer idVidaSilvestre) {
        return catVidaSilvestreAPIService.findById(idVidaSilvestre);
    }

    @Operation(operationId = "actualizar-vida-silvestre", summary = "Actualiza un registro Vida Silvestre", description = "Actualiza los campos no nulos de un registro Vida Silvestre existente.")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA, content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CatVidaSilvestreResponse.class))) })
    @PutMapping(value = UrlConst.VIDA_SILVESTRE_ACTUALIZAR, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatVidaSilvestreResponse> actualizar(@Parameter(description = "ID Vida Silvestre") @PathVariable final Integer idVidaSilvestre, @RequestBody final CatVidaSilvestreRequest request) {
        return catVidaSilvestreAPIService.actualizar(idVidaSilvestre, request);
    }
}
