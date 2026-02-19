package com.example.vucem_catalogos_service.controller;

import com.example.vucem_catalogos_service.business.Interface.ICatDelegMunService;
import com.example.vucem_catalogos_service.core.constants.CatalogPaths;
import com.example.vucem_catalogos_service.model.dto.CatDelegMunDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(CatalogPaths.CONTROLLER)
public class CatDelegMunController {

    @Autowired
    private ICatDelegMunService service;

    @GetMapping(CatalogPaths.LIST_CATALOGO_DELEG_MUN)
    public ResponseEntity<PageResponseDTO<CatDelegMunDTO>> list(
            @RequestParam(required = false) String search,
            Pageable pageable) {
        return ResponseEntity.ok(service.list(search, pageable));
    }

    @GetMapping(CatalogPaths.FIND_CATALOGO_DELEG_MUN)
    public ResponseEntity<CatDelegMunDTO> findById(@PathVariable String cveDelegMun) {
        return ResponseEntity.ok(service.findById(cveDelegMun));
    }

    @PostMapping(CatalogPaths.SAVE_CATALOGO_DELEG_MUN)
    public ResponseEntity<CatDelegMunDTO> create(@RequestBody CatDelegMunDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PutMapping(CatalogPaths.UPDATE_CATALOGO_DELEG_MUN)
    public ResponseEntity<CatDelegMunDTO> update(
            @PathVariable String cveDelegMun,
            @RequestBody CatDelegMunDTO dto) {
        return ResponseEntity.ok(service.update(cveDelegMun, dto));
    }
}
