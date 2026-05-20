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
import mx.gob.sat.catalogo.controller.request.sectorprosec.CatSectorProsecRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.sectorprosec.CatSectorProsecResponse;
import mx.gob.sat.catalogo.service.api.sectorprosec.CatSectorProsecAPIService;
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
 * <b>Class:</b> CatSectorProsecController.java <br>
 * <b>Description:</b>
 * <p>Controlador REST para el catalogo de sector prosec.
 * Expone endpoints bajo la ruta base {@code api/catalogo-admin/sector-prosec}.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Controlador
 */
@Tag(name = "Cat. Sector Prosec",
        description = "Servicios de consulta y mantenimiento del catalogo de sector prosec")
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class CatSectorProsecController extends ApiBaseController {

    /** Servicio de negocio para el catalogo de sector prosec. */
    private final CatSectorProsecAPIService catSectorProsecAPIService;

    @Operation(operationId = "listar-sector-prosec", summary = "Lista los registros sector prosec paginados",
        description = "Retorna una pagina de registros sector prosec con soporte de busqueda y ordenamiento.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = PaginaResponse.class)))
    })
    @GetMapping(value = UrlConst.SECTOR_PROSEC_LISTAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<PaginaResponse<CatSectorProsecResponse>> listar(
            @Parameter(description = "Numero de pagina (base 0)")
            @RequestParam(defaultValue = "0") final int pagina,
            @Parameter(description = "Tamano de pagina")
            @RequestParam(defaultValue = "20") final int tamano,
            @Parameter(description = "Texto de busqueda")
            @RequestParam(required = false) final String busqueda,
            @Parameter(description = "Columna de ordenamiento")
            @RequestParam(required = false) final String sortBy,
            @Parameter(description = "Direccion: asc o desc")
            @RequestParam(required = false) final String sortDir) {
        return catSectorProsecAPIService.listar(pagina, tamano, busqueda, sortBy, sortDir);
    }

    @Operation(operationId = "crear-sector-prosec", summary = "Crea un nuevo registro sector prosec",
        description = "Registra un nuevo registro sector prosec en el catalogo.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatSectorProsecResponse.class)))
    })
    @PostMapping(value = UrlConst.SECTOR_PROSEC_CREAR,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatSectorProsecResponse> crear(@Valid @RequestBody final CatSectorProsecRequest request) {
        return catSectorProsecAPIService.crear(request);
    }

    @Operation(operationId = "buscar-sector-prosec", summary = "Busca un registro sector prosec por clave",
        description = "Retorna la informacion de un registro sector prosec a partir de su clave.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatSectorProsecResponse.class)))
    })
    @GetMapping(value = UrlConst.SECTOR_PROSEC_BUSCAR, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatSectorProsecResponse> findById(
            @Parameter(description = "Clave del sector prosec") @PathVariable final String cveSectorProsec) {
        return catSectorProsecAPIService.findById(cveSectorProsec);
    }

    @Operation(operationId = "actualizar-sector-prosec", summary = "Actualiza un registro sector prosec",
        description = "Actualiza los campos no nulos de un registro sector prosec existente.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = GeneralConst.MENSAJE_OPERACION_EXITOSA,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CatSectorProsecResponse.class)))
    })
    @PutMapping(value = UrlConst.SECTOR_PROSEC_ACTUALIZAR,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CatSectorProsecResponse> actualizar(
            @Parameter(description = "Clave del sector prosec") @PathVariable final String cveSectorProsec,
            @RequestBody final CatSectorProsecRequest request) {
        return catSectorProsecAPIService.actualizar(cveSectorProsec, request);
    }
}
