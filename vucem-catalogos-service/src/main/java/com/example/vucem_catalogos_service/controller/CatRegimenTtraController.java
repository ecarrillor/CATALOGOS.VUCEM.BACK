package com.example.vucem_catalogos_service.controller;

import com.example.vucem_catalogos_service.business.Interface.ICatRegimenTtraService;
import com.example.vucem_catalogos_service.core.constants.CatalogPaths;
import com.example.vucem_catalogos_service.model.dto.CatRegimenTtraDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(CatalogPaths.CONTROLLER)
public class CatRegimenTtraController {

    @Autowired
    private ICatRegimenTtraService service;

    @GetMapping(CatalogPaths.LIST_REGIMEN_TTRA)
    public ResponseEntity<PageResponseDTO<CatRegimenTtraDTO>> list(
            @RequestParam(required = false) String search,
            Pageable pageable) {
        return ResponseEntity.ok(service.list(search, pageable));
    }

    @GetMapping(CatalogPaths.FIND_REGIMEN_TTRA)
    public ResponseEntity<CatRegimenTtraDTO> findById(@PathVariable Short id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping(CatalogPaths.SAVE_REGIMEN_TTRA)
    public ResponseEntity<CatRegimenTtraDTO> create(@RequestBody CatRegimenTtraDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PutMapping(CatalogPaths.UPDATE_REGIMEN_TTRA)
    public ResponseEntity<CatRegimenTtraDTO> update(
            @PathVariable Short id,
            @RequestBody CatRegimenTtraDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }
}
