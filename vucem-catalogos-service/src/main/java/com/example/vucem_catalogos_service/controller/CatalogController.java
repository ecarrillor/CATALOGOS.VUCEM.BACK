package com.example.vucem_catalogos_service.controller;

import com.example.vucem_catalogos_service.business.CatalogService;
import com.example.vucem_catalogos_service.core.constants.CatalogPaths;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Pageable;
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
            summary = "Listar catálogo",
            description = "Devuelve los registros paginados de un catálogo por key"
    )
    @GetMapping(CatalogPaths.LIST)
    public ResponseEntity<?> list(
            @PathVariable String catalog,
            Pageable pageable
    ) {
        return ResponseEntity.ok(
                getService(catalog).findAll(pageable)
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

    @PostMapping(CatalogPaths.CREATE)
    public ResponseEntity<?> create(
            @PathVariable String catalog,
            @RequestBody Object body
    ) {
        return ResponseEntity.ok(
                getService(catalog).save(body)
        );
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

    @DeleteMapping(CatalogPaths.DELETE)
    public ResponseEntity<Void> delete(
            @PathVariable String catalog,
            @PathVariable String id
    ) {
        getService(catalog).deleteById(id);
        return ResponseEntity.noContent().build();
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

