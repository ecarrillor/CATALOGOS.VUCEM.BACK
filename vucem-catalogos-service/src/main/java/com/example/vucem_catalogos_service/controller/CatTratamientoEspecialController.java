package com.example.vucem_catalogos_service.controller;

import com.example.vucem_catalogos_service.business.Interface.ICatTratamientoEspecialService;
import com.example.vucem_catalogos_service.core.constants.CatalogPaths;
import com.example.vucem_catalogos_service.model.dto.CatTratamientoEspecialDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(CatalogPaths.CONTROLLER)
public class CatTratamientoEspecialController {

    @Autowired
    private ICatTratamientoEspecialService service;

    @GetMapping(CatalogPaths.LIST_TRATAMIENTO_ESPECIAL)
    public ResponseEntity<PageResponseDTO<CatTratamientoEspecialDTO>> list(
            @RequestParam(required = false) String search,
            Pageable pageable) {
        return ResponseEntity.ok(service.list(search, pageable));
    }

    @GetMapping(CatalogPaths.FIND_TRATAMIENTO_ESPECIAL)
    public ResponseEntity<CatTratamientoEspecialDTO> findById(@PathVariable Short id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping(CatalogPaths.SAVE_TRATAMIENTO_ESPECIAL)
    public ResponseEntity<CatTratamientoEspecialDTO> create(@RequestBody CatTratamientoEspecialDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PutMapping(CatalogPaths.UPDATE_TRATAMIENTO_ESPECIAL)
    public ResponseEntity<CatTratamientoEspecialDTO> update(
            @PathVariable Short id,
            @RequestBody CatTratamientoEspecialDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }
}
