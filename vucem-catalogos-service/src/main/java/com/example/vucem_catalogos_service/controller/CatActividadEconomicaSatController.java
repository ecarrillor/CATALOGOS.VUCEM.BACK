package com.example.vucem_catalogos_service.controller;

import com.example.vucem_catalogos_service.business.Interface.ICatActividadEconomicaSatService;
import com.example.vucem_catalogos_service.core.constants.CatalogPaths;
import com.example.vucem_catalogos_service.model.dto.CatActividadEconomicaSatDTO;
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
public class CatActividadEconomicaSatController {

    @Autowired
    private ICatActividadEconomicaSatService service;

    @GetMapping(CatalogPaths.LIST_ACTIVIDAD_ECONOMICA_SAT)
    public ResponseEntity<PageResponseDTO<CatActividadEconomicaSatDTO>> list(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) String sortDir,
            Pageable pageable) {
        return ResponseEntity.ok(service.list(search, sortBy, sortDir, pageable));
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

    @GetMapping(CatalogPaths.LIST_ACTIVIDAD_ECONOMICA_SAT_DESC)
    public ResponseEntity<List<SelectDTO>> listadoActRel(){
        List<SelectDTO> lista = service.listadoActRel();
        return ResponseEntity.ok(lista);
    }

    @GetMapping(CatalogPaths.LIST_ACTIVIDAD_ECONOMICA_SAT_REL)
    public ResponseEntity<List<SelectDTO>> listadoAcDesc(){
        List<SelectDTO> lista = service.listadoAcDesc();
        return ResponseEntity.ok(lista);
    }
}
