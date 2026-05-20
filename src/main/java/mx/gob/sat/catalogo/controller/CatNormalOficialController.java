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
import mx.gob.sat.catalogo.controller.request.normaloficial.CatNormalOficialRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.normaloficial.CatNormalOficialResponse;
import mx.gob.sat.catalogo.service.api.normaloficial.CatNormalOficialAPIService;
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

@Tag(name = "Cat. Normal Oficial", description = "Servicios de consulta y mantenimiento del catalogo Normal Oficial")
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class CatNormalOficialController extends ApiBaseController {

    private final CatNormalOficialAPIService catNormalOficialAPIService;

    @Operation(operationId = "listar-normal-oficial", summary = "Lista los registros Normal Oficial paginados", description = "Retorna una pagina de registros Normal Oficial con soporte de busqueda y ordenamiento.")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA, content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = PaginaResponse.class))) })
    @GetMapping(value = UrlConst.NORMAL_OFICIAL_LISTAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<PaginaResponse<CatNormalOficialResponse>> listar(
            @RequestParam(defaultValue = "0") final int pagina,
            @RequestParam(defaultValue = "20") final int tamano,
            @RequestParam(required = false) final String busqueda,
            @RequestParam(required = false) final String sortBy,
            @RequestParam(required = false) final String sortDir) {
        return catNormalOficialAPIService.listar(pagina, tamano, busqueda, sortBy, sortDir);
    }

    @Operation(operationId = "crear-normal-oficial", summary = "Crea un nuevo registro Normal Oficial", description = "Registra un nuevo registro Normal Oficial en el catalogo.")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA, content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CatNormalOficialResponse.class))) })
    @PostMapping(value = UrlConst.NORMAL_OFICIAL_CREAR, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatNormalOficialResponse> crear(@Valid @RequestBody final CatNormalOficialRequest request) {
        return catNormalOficialAPIService.crear(request);
    }

    @Operation(operationId = "buscar-normal-oficial", summary = "Busca un registro Normal Oficial por ID", description = "Retorna la informacion de un registro Normal Oficial a partir de su ID.")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA, content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CatNormalOficialResponse.class))) })
    @GetMapping(value = UrlConst.NORMAL_OFICIAL_BUSCAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatNormalOficialResponse> findById(@Parameter(description = "ID Normal Oficial") @PathVariable final Integer idNormalOficial) {
        return catNormalOficialAPIService.findById(idNormalOficial);
    }

    @Operation(operationId = "actualizar-normal-oficial", summary = "Actualiza un registro Normal Oficial", description = "Actualiza los campos no nulos de un registro Normal Oficial existente.")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA, content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CatNormalOficialResponse.class))) })
    @PutMapping(value = UrlConst.NORMAL_OFICIAL_ACTUALIZAR, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatNormalOficialResponse> actualizar(@Parameter(description = "ID Normal Oficial") @PathVariable final Integer idNormalOficial, @RequestBody final CatNormalOficialRequest request) {
        return catNormalOficialAPIService.actualizar(idNormalOficial, request);
    }
}
