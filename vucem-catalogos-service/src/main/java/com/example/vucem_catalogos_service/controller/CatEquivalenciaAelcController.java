package com.example.vucem_catalogos_service.controller;

import com.example.vucem_catalogos_service.business.Interface.ICatEquivalenciaAELCService;
import com.example.vucem_catalogos_service.core.constants.CatalogPaths;
import com.example.vucem_catalogos_service.model.dto.CatEquivalenciaAelcDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDate;

@RestController
@RequestMapping(CatalogPaths.CONTROLLER)
public class CatEquivalenciaAelcController {

    @Autowired
    private ICatEquivalenciaAELCService service;

    @GetMapping(CatalogPaths.LIST_EQUIVALENCIA_AELC)
    public ResponseEntity<PageResponseDTO<CatEquivalenciaAelcDTO>> list(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) String sortDir,
            Pageable pageable) {
        return ResponseEntity.ok(service.list(search, sortBy, sortDir, pageable));
    }

    @GetMapping(CatalogPaths.FIND_EQUIVALENCIA_AELC)
    public ResponseEntity<CatEquivalenciaAelcDTO> findById(
            @PathVariable LocalDate fecIniVigencia,
            @PathVariable String cveMoneda) {
        return ResponseEntity.ok(service.findById(fecIniVigencia, cveMoneda));
    }

    @PostMapping(CatalogPaths.SAVE_EQUIVALENCIA_AELC)
    public ResponseEntity<CatEquivalenciaAelcDTO> create(@RequestBody CatEquivalenciaAelcDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PutMapping(CatalogPaths.UPDATE_EQUIVALENCIA_AELC)
    public ResponseEntity<CatEquivalenciaAelcDTO> update(
            @PathVariable LocalDate fecIniVigencia,
            @PathVariable String cveMoneda,
            @RequestBody CatEquivalenciaAelcDTO dto) {
        return ResponseEntity.ok(service.update(fecIniVigencia, cveMoneda, dto));
    }
}
