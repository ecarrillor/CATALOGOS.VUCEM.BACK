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
import mx.gob.sat.catalogo.controller.request.categoriatextil.CatCategoriaTextilRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.categoriatextil.CatCategoriaTextilResponse;
import mx.gob.sat.catalogo.service.api.categoriatextil.CatCategoriaTextilAPIService;
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

@Tag(name = "Cat. Categoria Textil", description = "Servicios de consulta y mantenimiento del catalogo Categoria Textil")
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class CatCategoriaTextilController extends ApiBaseController {

    private final CatCategoriaTextilAPIService catCategoriaTextilAPIService;

    @Operation(operationId = "listar-categoria-textil", summary = "Lista los registros Categoria Textil paginados", description = "Retorna una pagina de registros Categoria Textil con soporte de busqueda y ordenamiento.")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA, content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = PaginaResponse.class))) })
    @GetMapping(value = UrlConst.CATEGORIA_TEXTIL_LISTAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<PaginaResponse<CatCategoriaTextilResponse>> listar(
            @RequestParam(defaultValue = "0") final int pagina,
            @RequestParam(defaultValue = "20") final int tamano,
            @RequestParam(required = false) final String busqueda,
            @RequestParam(required = false) final String sortBy,
            @RequestParam(required = false) final String sortDir) {
        return catCategoriaTextilAPIService.listar(pagina, tamano, busqueda, sortBy, sortDir);
    }

    @Operation(operationId = "crear-categoria-textil", summary = "Crea un nuevo registro Categoria Textil", description = "Registra un nuevo registro Categoria Textil en el catalogo.")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA, content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CatCategoriaTextilResponse.class))) })
    @PostMapping(value = UrlConst.CATEGORIA_TEXTIL_CREAR, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatCategoriaTextilResponse> crear(@Valid @RequestBody final CatCategoriaTextilRequest request) {
        return catCategoriaTextilAPIService.crear(request);
    }

    @Operation(operationId = "buscar-categoria-textil", summary = "Busca un registro Categoria Textil por ID", description = "Retorna la informacion de un registro Categoria Textil a partir de su ID.")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA, content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CatCategoriaTextilResponse.class))) })
    @GetMapping(value = UrlConst.CATEGORIA_TEXTIL_BUSCAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatCategoriaTextilResponse> findById(@Parameter(description = "ID Categoria Textil") @PathVariable final Long idCategoriaTextil) {
        return catCategoriaTextilAPIService.findById(idCategoriaTextil);
    }

    @Operation(operationId = "actualizar-categoria-textil", summary = "Actualiza un registro Categoria Textil", description = "Actualiza los campos no nulos de un registro Categoria Textil existente.")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA, content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CatCategoriaTextilResponse.class))) })
    @PutMapping(value = UrlConst.CATEGORIA_TEXTIL_ACTUALIZAR, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatCategoriaTextilResponse> actualizar(@Parameter(description = "ID Categoria Textil") @PathVariable final Long idCategoriaTextil, @RequestBody final CatCategoriaTextilRequest request) {
        return catCategoriaTextilAPIService.actualizar(idCategoriaTextil, request);
    }
}
