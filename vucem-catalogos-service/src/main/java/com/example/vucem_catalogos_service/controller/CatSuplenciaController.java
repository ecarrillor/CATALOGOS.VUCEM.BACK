package com.example.vucem_catalogos_service.controller;

import com.example.vucem_catalogos_service.business.Interface.ICatSuplenciaService;
import com.example.vucem_catalogos_service.core.constants.CatalogPaths;
import com.example.vucem_catalogos_service.model.dto.CatSuplenciaDTO;
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
public class CatSuplenciaController {

    @Autowired
    private ICatSuplenciaService service;

    @GetMapping(CatalogPaths.LIST_SUPLENCIA)
    public ResponseEntity<PageResponseDTO<CatSuplenciaDTO>> list(
            @RequestParam(required = false) String search,
            Pageable pageable) {
        return ResponseEntity.ok(service.list(search, pageable));
    }

    @GetMapping(CatalogPaths.FIND_SUPLENCIA)
    public ResponseEntity<CatSuplenciaDTO> findById(@PathVariable Short id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping(CatalogPaths.SAVE_SUPLENCIA)
    public ResponseEntity<CatSuplenciaDTO> create(@RequestBody CatSuplenciaDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PutMapping(CatalogPaths.UPDATE_SUPLENCIA)
    public ResponseEntity<CatSuplenciaDTO> update(
            @PathVariable Short id,
            @RequestBody CatSuplenciaDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @GetMapping(CatalogPaths.LIST_DEPENDENCIAS_SUPLENCIA)
    public ResponseEntity<List<SelectDTO>> listadoDependencias() {
        return ResponseEntity.ok(service.listadoDependencias());
    }
}
