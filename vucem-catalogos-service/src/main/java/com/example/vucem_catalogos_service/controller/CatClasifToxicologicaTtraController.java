package com.example.vucem_catalogos_service.controller;

import com.example.vucem_catalogos_service.business.Interface.ICatClasifToxicologicaTtraService;
import com.example.vucem_catalogos_service.core.constants.CatalogPaths;
import com.example.vucem_catalogos_service.model.dto.CatClasifToxicologicaTtraDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(CatalogPaths.CONTROLLER)
public class CatClasifToxicologicaTtraController {

    @Autowired
    private ICatClasifToxicologicaTtraService service;

    @GetMapping(CatalogPaths.LIST_CLASIF_TOXICOLOGICA_TTRA)
    public ResponseEntity<PageResponseDTO<CatClasifToxicologicaTtraDTO>> list(
            @RequestParam(required = false) String search,
            Pageable pageable) {
        return ResponseEntity.ok(service.list(search, pageable));
    }

    @GetMapping(CatalogPaths.FIND_CLASIF_TOXICOLOGICA_TTRA)
    public ResponseEntity<CatClasifToxicologicaTtraDTO> findById(@PathVariable Short id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping(CatalogPaths.SAVE_CLASIF_TOXICOLOGICA_TTRA)
    public ResponseEntity<CatClasifToxicologicaTtraDTO> create(@RequestBody CatClasifToxicologicaTtraDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PutMapping(CatalogPaths.UPDATE_CLASIF_TOXICOLOGICA_TTRA)
    public ResponseEntity<CatClasifToxicologicaTtraDTO> update(
            @PathVariable Short id,
            @RequestBody CatClasifToxicologicaTtraDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }
}
