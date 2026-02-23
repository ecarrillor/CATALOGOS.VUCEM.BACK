package com.example.vucem_catalogos_service.controller;

import com.example.vucem_catalogos_service.business.Interface.ICatActividadEconomicaSatService;
import com.example.vucem_catalogos_service.core.constants.CatalogPaths;
import com.example.vucem_catalogos_service.model.dto.CatActividadEconomicaSatDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(CatalogPaths.CONTROLLER)
public class CatActividadEconomicaSatController {

    @Autowired
    private ICatActividadEconomicaSatService service;

    @GetMapping(CatalogPaths.LIST_ACTIVIDAD_ECONOMICA_SAT)
    public ResponseEntity<PageResponseDTO<CatActividadEconomicaSatDTO>> list(
            @RequestParam(required = false) String search,
            Pageable pageable) {
        return ResponseEntity.ok(service.list(search, pageable));
    }

    @GetMapping(CatalogPaths.FIND_ACTIVIDAD_ECONOMICA_SAT)
    public ResponseEntity<CatActividadEconomicaSatDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping(CatalogPaths.SAVE_ACTIVIDAD_ECONOMICA_SAT)
    public ResponseEntity<CatActividadEconomicaSatDTO> create(@RequestBody CatActividadEconomicaSatDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PutMapping(CatalogPaths.UPDATE_ACTIVIDAD_ECONOMICA_SAT)
    public ResponseEntity<CatActividadEconomicaSatDTO> update(
            @PathVariable Long id,
            @RequestBody CatActividadEconomicaSatDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }
}
