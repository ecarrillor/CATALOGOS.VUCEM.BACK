package com.example.vucem_catalogos_service.controller;

import com.example.vucem_catalogos_service.business.Interface.ICatUsoMercanciaTtraService;
import com.example.vucem_catalogos_service.core.constants.CatalogPaths;
import com.example.vucem_catalogos_service.model.dto.CatUsoMercanciaTtraDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(CatalogPaths.CONTROLLER)
public class CatUsoMercanciaTtraController {

    @Autowired
    private ICatUsoMercanciaTtraService service;

    @GetMapping(CatalogPaths.LIST_USO_MERCANCIA_TTRA)
    public ResponseEntity<PageResponseDTO<CatUsoMercanciaTtraDTO>> list(
            @RequestParam(required = false) String search,
            Pageable pageable) {
        return ResponseEntity.ok(service.list(search, pageable));
    }

    @GetMapping(CatalogPaths.FIND_USO_MERCANCIA_TTRA)
    public ResponseEntity<CatUsoMercanciaTtraDTO> findById(@PathVariable Short id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping(CatalogPaths.SAVE_USO_MERCANCIA_TTRA)
    public ResponseEntity<CatUsoMercanciaTtraDTO> create(@RequestBody CatUsoMercanciaTtraDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PutMapping(CatalogPaths.UPDATE_USO_MERCANCIA_TTRA)
    public ResponseEntity<CatUsoMercanciaTtraDTO> update(
            @PathVariable Short id,
            @RequestBody CatUsoMercanciaTtraDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }
}
