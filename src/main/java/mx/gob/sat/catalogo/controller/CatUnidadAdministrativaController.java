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
import mx.gob.sat.catalogo.controller.request.unidadadministrativa.CatUnidadAdministrativaRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.unidadadministrativa.CatUnidadAdministrativaResponse;
import mx.gob.sat.catalogo.service.api.unidadadministrativa.CatUnidadAdministrativaAPIService;
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

@Tag(name = "Cat. Unidad Administrativa", description = "Servicios de consulta y mantenimiento del catalogo Unidad Administrativa")
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class CatUnidadAdministrativaController extends ApiBaseController {

    private final CatUnidadAdministrativaAPIService catUnidadAdministrativaAPIService;

    @Operation(operationId = "listar-unidad-administrativa", summary = "Lista los registros Unidad Administrativa paginados", description = "Retorna una pagina de registros Unidad Administrativa con soporte de busqueda y ordenamiento.")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA, content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = PaginaResponse.class))) })
    @GetMapping(value = UrlConst.UNIDAD_ADMINISTRATIVA_LISTAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<PaginaResponse<CatUnidadAdministrativaResponse>> listar(
            @RequestParam(defaultValue = "0") final int pagina,
            @RequestParam(defaultValue = "20") final int tamano,
            @RequestParam(required = false) final String busqueda,
            @RequestParam(required = false) final String sortBy,
            @RequestParam(required = false) final String sortDir) {
        return catUnidadAdministrativaAPIService.listar(pagina, tamano, busqueda, sortBy, sortDir);
    }

    @Operation(operationId = "crear-unidad-administrativa", summary = "Crea un nuevo registro Unidad Administrativa", description = "Registra un nuevo registro Unidad Administrativa en el catalogo.")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA, content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CatUnidadAdministrativaResponse.class))) })
    @PostMapping(value = UrlConst.UNIDAD_ADMINISTRATIVA_CREAR, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatUnidadAdministrativaResponse> crear(@Valid @RequestBody final CatUnidadAdministrativaRequest request) {
        return catUnidadAdministrativaAPIService.crear(request);
    }

    @Operation(operationId = "buscar-unidad-administrativa", summary = "Busca un registro Unidad Administrativa por clave", description = "Retorna la informacion de un registro Unidad Administrativa a partir de su clave.")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA, content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CatUnidadAdministrativaResponse.class))) })
    @GetMapping(value = UrlConst.UNIDAD_ADMINISTRATIVA_BUSCAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatUnidadAdministrativaResponse> findById(@Parameter(description = "Clave Unidad Administrativa") @PathVariable final String cveUnidadAdministrativa) {
        return catUnidadAdministrativaAPIService.findById(cveUnidadAdministrativa);
    }

    @Operation(operationId = "actualizar-unidad-administrativa", summary = "Actualiza un registro Unidad Administrativa", description = "Actualiza los campos no nulos de un registro Unidad Administrativa existente.")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA, content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CatUnidadAdministrativaResponse.class))) })
    @PutMapping(value = UrlConst.UNIDAD_ADMINISTRATIVA_ACTUALIZAR, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatUnidadAdministrativaResponse> actualizar(@Parameter(description = "Clave Unidad Administrativa") @PathVariable final String cveUnidadAdministrativa, @RequestBody final CatUnidadAdministrativaRequest request) {
        return catUnidadAdministrativaAPIService.actualizar(cveUnidadAdministrativa, request);
    }
}
