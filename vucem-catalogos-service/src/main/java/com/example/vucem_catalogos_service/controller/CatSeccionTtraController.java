package com.example.vucem_catalogos_service.controller;

import com.example.vucem_catalogos_service.business.Interface.ICatSeccionTtraService;
import com.example.vucem_catalogos_service.core.constants.CatalogPaths;
import com.example.vucem_catalogos_service.model.dto.CatSeccionTtraDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(CatalogPaths.CONTROLLER)
public class CatSeccionTtraController {

    @Autowired
    private ICatSeccionTtraService service;

    @GetMapping(CatalogPaths.LIST_SECCION_TTRA)
    public ResponseEntity<PageResponseDTO<CatSeccionTtraDTO>> list(
            @RequestParam(required = false) String search,
            Pageable pageable) {
        return ResponseEntity.ok(service.list(search, pageable));
    }

    @GetMapping(CatalogPaths.FIND_SECCION_TTRA)
    public ResponseEntity<CatSeccionTtraDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping(CatalogPaths.SAVE_SECCION_TTRA)
    public ResponseEntity<CatSeccionTtraDTO> create(@RequestBody CatSeccionTtraDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PutMapping(CatalogPaths.UPDATE_SECCION_TTRA)
    public ResponseEntity<CatSeccionTtraDTO> update(
            @PathVariable Integer id,
            @RequestBody CatSeccionTtraDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }
}
