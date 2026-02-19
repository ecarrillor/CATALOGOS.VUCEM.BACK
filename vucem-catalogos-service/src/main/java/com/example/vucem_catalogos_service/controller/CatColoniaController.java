package com.example.vucem_catalogos_service.controller;

import com.example.vucem_catalogos_service.business.Interface.ICatColoniaService;
import com.example.vucem_catalogos_service.core.constants.CatalogPaths;
import com.example.vucem_catalogos_service.model.dto.CatColoniaDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(CatalogPaths.CONTROLLER)
public class CatColoniaController {

    @Autowired
    private ICatColoniaService service;

    @GetMapping(CatalogPaths.LIST_CATALOGO_COLONIA)
    public ResponseEntity<PageResponseDTO<CatColoniaDTO>> list(
            @RequestParam(required = false) String search,
            Pageable pageable) {
        return ResponseEntity.ok(service.list(search, pageable));
    }

    @GetMapping(CatalogPaths.FIND_CATALOGO_COLONIA)
    public ResponseEntity<CatColoniaDTO> findById(@PathVariable String cveColonia) {
        return ResponseEntity.ok(service.findById(cveColonia));
    }

    @PostMapping(CatalogPaths.SAVE_CATALOGO_COLONIA)
    public ResponseEntity<CatColoniaDTO> create(@RequestBody CatColoniaDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PutMapping(CatalogPaths.UPDATE_CATALOGO_COLONIA)
    public ResponseEntity<CatColoniaDTO> update(
            @PathVariable String cveColonia,
            @RequestBody CatColoniaDTO dto) {
        return ResponseEntity.ok(service.update(cveColonia, dto));
    }

    @GetMapping(CatalogPaths.FIND_COLONIA_BY_CP)
    public ResponseEntity<List<CatColoniaDTO>> findByCp(@PathVariable String cp) {
        return ResponseEntity.ok(service.findByCp(cp));
    }
}
