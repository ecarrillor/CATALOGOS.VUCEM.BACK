package com.example.vucem_catalogos_service.controller;

import com.example.vucem_catalogos_service.business.Interface.ICatPaisService;
import com.example.vucem_catalogos_service.core.constants.CatalogPaths;
import com.example.vucem_catalogos_service.model.dto.CatPaisDTO;
import com.example.vucem_catalogos_service.model.dto.CatPaisSaveDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.dto.SelectDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(CatalogPaths.CONTROLLER)
public class CatPaisController {

    @Autowired
    private ICatPaisService service;

    @GetMapping(CatalogPaths.LIST_CATALOGO_PAIS)
    public ResponseEntity<PageResponseDTO<CatPaisDTO>> list(
            @RequestParam(required = false) String search,
            Pageable pageable) {
        return ResponseEntity.ok(service.list(search, pageable));
    }

    @GetMapping(CatalogPaths.FIND_CATALOGO_PAIS)
    public ResponseEntity<CatPaisSaveDTO> findById(@PathVariable String cvePais) {
        return ResponseEntity.ok(service.findById(cvePais));
    }

    @PostMapping(CatalogPaths.SAVE_CATALOGO_PAIS)
    public ResponseEntity<CatPaisSaveDTO> create(@RequestBody CatPaisSaveDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PutMapping(CatalogPaths.UPDATE_CATALOGO_PAIS)
    public ResponseEntity<CatPaisSaveDTO> update(
            @PathVariable String cvePais,
            @RequestBody CatPaisSaveDTO dto) {
        return ResponseEntity.ok(service.update(cvePais, dto));
    }

    @GetMapping(CatalogPaths.LIST_CAT_MONEDA)
    public ResponseEntity<List<SelectDTO>> listMoneda() {
        return ResponseEntity.ok(service.listMoneda());
    }



}
