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
import mx.gob.sat.catalogo.controller.request.recintofiscalizado.CatRecintoFiscalizadoRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.recintofiscalizado.CatRecintoFiscalizadoResponse;
import mx.gob.sat.catalogo.service.api.recintofiscalizado.CatRecintoFiscalizadoAPIService;
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

@Tag(name = "Cat. Recinto Fiscalizado", description = "Servicios de consulta y mantenimiento del catalogo Recinto Fiscalizado")
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class CatRecintoFiscalizadoController extends ApiBaseController {

    private final CatRecintoFiscalizadoAPIService catRecintoFiscalizadoAPIService;

    @Operation(operationId = "listar-recinto-fiscalizado", summary = "Lista los registros Recinto Fiscalizado paginados", description = "Retorna una pagina de registros Recinto Fiscalizado con soporte de busqueda y ordenamiento.")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA, content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = PaginaResponse.class))) })
    @GetMapping(value = UrlConst.RECINTO_FISCALIZADO_LISTAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<PaginaResponse<CatRecintoFiscalizadoResponse>> listar(
            @RequestParam(defaultValue = "0") final int pagina,
            @RequestParam(defaultValue = "20") final int tamano,
            @RequestParam(required = false) final String busqueda,
            @RequestParam(required = false) final String sortBy,
            @RequestParam(required = false) final String sortDir) {
        return catRecintoFiscalizadoAPIService.listar(pagina, tamano, busqueda, sortBy, sortDir);
    }

    @Operation(operationId = "crear-recinto-fiscalizado", summary = "Crea un nuevo registro Recinto Fiscalizado", description = "Registra un nuevo registro Recinto Fiscalizado en el catalogo.")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA, content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CatRecintoFiscalizadoResponse.class))) })
    @PostMapping(value = UrlConst.RECINTO_FISCALIZADO_CREAR, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatRecintoFiscalizadoResponse> crear(@Valid @RequestBody final CatRecintoFiscalizadoRequest request) {
        return catRecintoFiscalizadoAPIService.crear(request);
    }

    @Operation(operationId = "buscar-recinto-fiscalizado", summary = "Busca un registro Recinto Fiscalizado por ID", description = "Retorna la informacion de un registro Recinto Fiscalizado a partir de su ID.")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA, content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CatRecintoFiscalizadoResponse.class))) })
    @GetMapping(value = UrlConst.RECINTO_FISCALIZADO_BUSCAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatRecintoFiscalizadoResponse> findById(@Parameter(description = "ID Recinto Fiscalizado") @PathVariable final Long idRecintoFiscalizado) {
        return catRecintoFiscalizadoAPIService.findById(idRecintoFiscalizado);
    }

    @Operation(operationId = "actualizar-recinto-fiscalizado", summary = "Actualiza un registro Recinto Fiscalizado", description = "Actualiza los campos no nulos de un registro Recinto Fiscalizado existente.")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA, content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CatRecintoFiscalizadoResponse.class))) })
    @PutMapping(value = UrlConst.RECINTO_FISCALIZADO_ACTUALIZAR, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatRecintoFiscalizadoResponse> actualizar(@Parameter(description = "ID Recinto Fiscalizado") @PathVariable final Long idRecintoFiscalizado, @RequestBody final CatRecintoFiscalizadoRequest request) {
        return catRecintoFiscalizadoAPIService.actualizar(idRecintoFiscalizado, request);
    }
}
