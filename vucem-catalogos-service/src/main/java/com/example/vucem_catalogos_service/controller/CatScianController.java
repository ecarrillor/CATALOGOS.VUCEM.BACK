package com.example.vucem_catalogos_service.controller;

import com.example.vucem_catalogos_service.business.Interface.ICatScianService;
import com.example.vucem_catalogos_service.core.constants.CatalogPaths;
import com.example.vucem_catalogos_service.model.dto.CatScianDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(CatalogPaths.CONTROLLER)
public class CatScianController {

    @Autowired
    private ICatScianService service;

    @GetMapping(CatalogPaths.LIST_SCIAN)
    public ResponseEntity<PageResponseDTO<CatScianDTO>> list(
            @RequestParam(required = false) String search,
            Pageable pageable) {
        return ResponseEntity.ok(service.list(search, pageable));
    }

    @GetMapping(CatalogPaths.FIND_SCIAN)
    public ResponseEntity<CatScianDTO> findById(@PathVariable String cveScian) {
        return ResponseEntity.ok(service.findById(cveScian));
    }

    @PostMapping(CatalogPaths.SAVE_SCIAN)
    public ResponseEntity<CatScianDTO> create(@RequestBody CatScianDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PutMapping(CatalogPaths.UPDATE_SCIAN)
    public ResponseEntity<CatScianDTO> update(
            @PathVariable String cveScian,
            @RequestBody CatScianDTO dto) {
        return ResponseEntity.ok(service.update(cveScian, dto));
    }
}
