package com.example.vucem_catalogos_service.controller;

import com.example.vucem_catalogos_service.business.Interface.ICatPartidaFraccionService;
import com.example.vucem_catalogos_service.core.constants.CatalogPaths;
import com.example.vucem_catalogos_service.model.dto.CatPartidaFraccionDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.entity.CatCapituloFraccion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(CatalogPaths.CONTROLLER)
public class CatPartidaFraccionController {

    @Autowired
    private ICatPartidaFraccionService service;

    @GetMapping(CatalogPaths.LIST_PARTIDA_FRACCION)
    public ResponseEntity<PageResponseDTO<CatPartidaFraccionDTO>> list(
            @RequestParam(required = false) String search,
            Pageable pageable) {
        return ResponseEntity.ok(service.list(search, pageable));
    }

    @GetMapping(CatalogPaths.FIND_PARTIDA_FRACCION)
    public ResponseEntity<CatPartidaFraccionDTO> findById(
            @PathVariable String cveCapituloFraccion,
            @PathVariable String cvePartidaFraccion) {
        return ResponseEntity.ok(service.findById(cveCapituloFraccion, cvePartidaFraccion));
    }

    @PostMapping(CatalogPaths.SAVE_PARTIDA_FRACCION)
    public ResponseEntity<CatPartidaFraccionDTO> create(@RequestBody CatPartidaFraccionDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PutMapping(CatalogPaths.UPDATE_PARTIDA_FRACCION)
    public ResponseEntity<CatPartidaFraccionDTO> update(
            @PathVariable String cveCapituloFraccion,
            @PathVariable String cvePartidaFraccion,
            @RequestBody CatPartidaFraccionDTO dto) {
        return ResponseEntity.ok(service.update(cveCapituloFraccion, cvePartidaFraccion, dto));
    }

    @GetMapping(CatalogPaths.LIST_CAPITULOS_FRACCION_ACTIVOS)
    public ResponseEntity<List<CatCapituloFraccion>> listCapitulosFraccionActivos() {
        return ResponseEntity.ok(service.listCapitulosFraccionActivos());
    }
}
