package com.example.vucem_catalogos_service.controller;

import com.example.vucem_catalogos_service.business.CatalogService;
import com.example.vucem_catalogos_service.core.constants.CatalogPaths;
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
@Tag(name = "Catálogos", description = "Operaciones genéricas de catálogos")

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
            summary = "Listar catálogo, busqueda por key, en el excel pongo las keys",
            description = "Devuelve registros paginados con búsqueda y filtros dinámicos"
    )
    @GetMapping(CatalogPaths.LIST)
    public ResponseEntity<?> list(
            @PathVariable String catalog,
            @RequestParam(required = false) String search,
            @RequestParam(required = false) Map<String, String> filters,
            @RequestParam(defaultValue = "false") boolean includeSubcatalogs,
            Pageable pageable
    ) {
        // Remover parámetros reservados de paginación
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
    /*@GetMapping(CatalogPaths.FIND_BY_ID)
    public ResponseEntity<?> getById(
            @PathVariable String catalog,
            @PathVariable String id
    ) {
        return getService(catalog)
                .findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }*/

    @Operation(
            summary = "Crear registro en catálogo",
            description = "Crea un registro dinámico según el catálogo indicado"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Registro creado correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos"),
            @ApiResponse(responseCode = "404", description = "Catálogo no encontrado")
    })
    @PostMapping(CatalogPaths.CREATE)
    public ResponseEntity<?> create(
            @PathVariable String catalog,
            @RequestBody Object body) {
        Object saved = getService(catalog).save(body);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(saved);
    }

    @PutMapping(CatalogPaths.UPDATE)
    public ResponseEntity<?> update(
            @PathVariable String catalog,
            @RequestBody Object body
    ) {
        return ResponseEntity.ok(
                getService(catalog).save(body)
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

