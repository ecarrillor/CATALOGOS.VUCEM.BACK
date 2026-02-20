package com.example.vucem_catalogos_service.controller;

import com.example.vucem_catalogos_service.business.Interface.ICatLocalidadService;
import com.example.vucem_catalogos_service.core.constants.CatalogPaths;
import com.example.vucem_catalogos_service.model.dto.CatLocalidadDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(CatalogPaths.CONTROLLER)
public class CatLocalidadController {
    @Autowired
    private ICatLocalidadService service;

    @GetMapping(CatalogPaths.LIST_LOCALIDAD)
    public ResponseEntity<PageResponseDTO<CatLocalidadDTO>> list(
            @RequestParam(required = false) String search,
            Pageable pageable) {
        return ResponseEntity.ok(service.list(search, pageable));
    }

    @GetMapping(CatalogPaths.FIND_LOCALIDAD)
    public ResponseEntity<CatLocalidadDTO> findById(@PathVariable String cveLocalidad) {
        return ResponseEntity.ok(service.findById(cveLocalidad));
    }

    @PostMapping(CatalogPaths.SAVE_LOCALIDAD)
    public ResponseEntity<CatLocalidadDTO> create(@RequestBody CatLocalidadDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PutMapping(CatalogPaths.UPDATE_LOCALIDAD)
    public ResponseEntity<CatLocalidadDTO> update(
            @PathVariable String cveLocalidad,
            @RequestBody CatLocalidadDTO dto) {
        return ResponseEntity.ok(service.update(cveLocalidad, dto));
    }
}
