package com.example.vucem_catalogos_service.controller;

import com.example.vucem_catalogos_service.business.Interface.ICatDiaNoLaborableService;
import com.example.vucem_catalogos_service.core.constants.CatalogPaths;
import com.example.vucem_catalogos_service.model.dto.CatDiaNoLaborableDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.dto.SelectDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(CatalogPaths.CONTROLLER)
public class CatDiaNoLaborableController {

    @Autowired
    private ICatDiaNoLaborableService service;

    @GetMapping(CatalogPaths.LIST_DIA_NO_LABORABLE)
    public ResponseEntity<PageResponseDTO<CatDiaNoLaborableDTO>> list(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) String sortDir,
            Pageable pageable) {
        return ResponseEntity.ok(service.list(search, sortBy, sortDir, pageable));
    }

    @GetMapping(CatalogPaths.FIND_DIA_NO_LABORABLE)
    public ResponseEntity<CatDiaNoLaborableDTO> findById(
            @PathVariable LocalDate fecNoLaborable,
            @PathVariable String cveCalendario) {
        return ResponseEntity.ok(service.findById(fecNoLaborable, cveCalendario));
    }

    @PostMapping(CatalogPaths.SAVE_DIA_NO_LABORABLE)
    public ResponseEntity<CatDiaNoLaborableDTO> create(@RequestBody CatDiaNoLaborableDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PutMapping(CatalogPaths.UPDATE_DIA_NO_LABORABLE)
    public ResponseEntity<CatDiaNoLaborableDTO> update(
            @PathVariable LocalDate fecNoLaborable,
            @PathVariable String cveCalendario,
            @RequestBody CatDiaNoLaborableDTO dto) {
        return ResponseEntity.ok(service.update(fecNoLaborable, cveCalendario, dto));
    }

    @GetMapping(CatalogPaths.LIST_SELECT_CALENDARIO)
    public ResponseEntity<List<SelectDTO>> listCalendario() {
        return ResponseEntity.ok(service.listCalendario());
    }
}
