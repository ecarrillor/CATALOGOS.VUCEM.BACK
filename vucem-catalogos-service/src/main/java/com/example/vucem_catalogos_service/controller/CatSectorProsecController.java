package com.example.vucem_catalogos_service.controller;

import com.example.vucem_catalogos_service.business.Interface.ICatSectorProsecService;
import com.example.vucem_catalogos_service.core.constants.CatalogPaths;
import com.example.vucem_catalogos_service.model.dto.CatSectorProsecDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(CatalogPaths.CONTROLLER)
public class CatSectorProsecController {

    @Autowired
    private ICatSectorProsecService service;

    @GetMapping(CatalogPaths.LIST_SECTOR_PROSEC)
    public ResponseEntity<PageResponseDTO<CatSectorProsecDTO>> list(
            @RequestParam(required = false) String search,
            Pageable pageable) {
        return ResponseEntity.ok(service.list(search, pageable));
    }

    @GetMapping(CatalogPaths.FIND_SECTOR_PROSEC)
    public ResponseEntity<CatSectorProsecDTO> findById(@PathVariable String cveSectorProsec) {
        return ResponseEntity.ok(service.findById(cveSectorProsec));
    }

    @PostMapping(CatalogPaths.SAVE_SECTOR_PROSEC)
    public ResponseEntity<CatSectorProsecDTO> create(@RequestBody CatSectorProsecDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PutMapping(CatalogPaths.UPDATE_SECTOR_PROSEC)
    public ResponseEntity<CatSectorProsecDTO> update(
            @PathVariable String cveSectorProsec,
            @RequestBody CatSectorProsecDTO dto) {
        return ResponseEntity.ok(service.update(cveSectorProsec, dto));
    }
}
