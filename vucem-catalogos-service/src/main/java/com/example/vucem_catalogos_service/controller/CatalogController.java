package com.example.vucem_catalogos_service.controller;

import com.example.vucem_catalogos_service.business.CatalogService;
import com.example.vucem_catalogos_service.core.constants.CatalogPaths;
import com.fasterxml.jackson.databind.JsonMappingException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(CatalogPaths.CONTROLLER)
@Tag(name = "CatÃ¡logos", description = "Operaciones genÃ©ricas de catÃ¡logos")

public class CatalogController {

    private final Map<String, CatalogService<?, ?>> services;

    public CatalogController(List<CatalogService<?, ?>> services) {
        this.services = services.stream()
                .collect(Collectors.toMap(
                        CatalogService::getCatalogKey,
                        s -> s
                ));
    }


    @Operation(
            summary = "Listar catÃ¡logo, busqueda por key, en el excel pongo las keys",
            description = "Devuelve registros paginados con bÃºsqueda y filtros dinÃ¡micos"
    )
    @GetMapping(CatalogPaths.LIST)
    public ResponseEntity<?> list(
            @PathVariable String catalog,
            @RequestParam(required = false) String search,
            @RequestParam(required = false) Map<String, String> filters,
            @RequestParam(defaultValue = "false") boolean includeSubcatalogs,
            Pageable pageable
    ) {
        // Remover parÃ¡metros reservados de paginaciÃ³n
        if (filters != null) {
            filters.remove("page");
            filters.remove("size");
            filters.remove("sort");
            filters.remove("search");
            filters.remove("includeSubcatalogs");
        }

        return ResponseEntity.ok(
                getService(catalog).findAll(search, filters, includeSubcatalogs, pageable)
        );
    }


    @Operation(summary = "Obtener registro por ID")
    @GetMapping(CatalogPaths.FIND_BY_ID)
    public ResponseEntity<?> findById(
            @PathVariable String catalog,
            @PathVariable String id) {

        return (ResponseEntity<?>) getService(catalog)
                .findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }




    @Operation(
            summary = "Crear registro en catÃ¡logo",
            description = "Crea un registro dinÃ¡mico segÃºn el catÃ¡logo indicado"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Registro creado correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos invÃ¡lidos"),
            @ApiResponse(responseCode = "404", description = "CatÃ¡logo no encontrado")
    })
    @PostMapping(CatalogPaths.CREATE)
    public ResponseEntity<?> create(
            @PathVariable String catalog,
            @RequestBody Object body) {

        Object saved = getService(catalog).save( body);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(saved);
    }


    @Operation(summary = "Actualizar registro")
    @PutMapping(CatalogPaths.UPDATE)
    public ResponseEntity<?> update(
            @PathVariable String catalog,
            @PathVariable String id,
            @RequestBody Object body) throws JsonMappingException {
        return ResponseEntity.ok(
                getService(catalog).update(id, body)
        );
    }



    private CatalogService getService(String catalog) {
        CatalogService service = services.get(catalog);
        if (service == null) {
            throw new IllegalArgumentException(
                    "Catalog not found: " + catalog
            );
        }
        return service;
    }
}

