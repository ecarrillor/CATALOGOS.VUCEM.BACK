package com.example.vucem_catalogos_service.controller;

import com.example.vucem_catalogos_service.business.Interface.ICatPatenteAduanalService;
import com.example.vucem_catalogos_service.core.constants.CatalogPaths;
import com.example.vucem_catalogos_service.model.dto.CatPatenteAduanalDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(CatalogPaths.CONTROLLER)
public class CatPatenteAduanalController {

    @Autowired
    private ICatPatenteAduanalService service;

    @GetMapping(CatalogPaths.LIST_PATENTE_ADUANAL)
    public ResponseEntity<PageResponseDTO<CatPatenteAduanalDTO>> list(
            @RequestParam(required = false) String search,
            Pageable pageable) {
        return ResponseEntity.ok(service.list(search, pageable));
    }

    @GetMapping(CatalogPaths.FIND_PATENTE_ADUANAL)
    public ResponseEntity<CatPatenteAduanalDTO> findById(@PathVariable String cvePatenteAduanal) {
        return ResponseEntity.ok(service.findById(cvePatenteAduanal));
    }

    @PostMapping(CatalogPaths.SAVE_PATENTE_ADUANAL)
    public ResponseEntity<CatPatenteAduanalDTO> create(@RequestBody CatPatenteAduanalDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PutMapping(CatalogPaths.UPDATE_PATENTE_ADUANAL)
    public ResponseEntity<CatPatenteAduanalDTO> update(@PathVariable String cvePatenteAduanal,
                                                       @RequestBody CatPatenteAduanalDTO dto) {
        return ResponseEntity.ok(service.update(cvePatenteAduanal, dto));
    }
}
