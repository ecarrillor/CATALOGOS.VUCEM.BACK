package com.example.vucem_catalogos_service.controller;

import com.example.vucem_catalogos_service.business.Interface.ICatSubpartidaFraccionService;
import com.example.vucem_catalogos_service.core.constants.CatalogPaths;
import com.example.vucem_catalogos_service.model.dto.CatSubpartidaFraccionDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(CatalogPaths.CONTROLLER)
public class CatSubpartidaFraccionController {

    @Autowired
    private ICatSubpartidaFraccionService service;

    @GetMapping(CatalogPaths.LIST_SUBPARTIDA_FRACCION)
    public ResponseEntity<PageResponseDTO<CatSubpartidaFraccionDTO>> list(
            @RequestParam(required = false) String search,
            Pageable pageable) {
        return ResponseEntity.ok(service.list(search, pageable));
    }

    @GetMapping(CatalogPaths.FIND_SUBPARTIDA_FRACCION)
    public ResponseEntity<CatSubpartidaFraccionDTO> findById(
            @PathVariable String cveSubpartidaFraccion,
            @PathVariable String cveCapituloFraccion,
            @PathVariable String cvePartidaFraccion) {
        return ResponseEntity.ok(service.findById(cveSubpartidaFraccion, cveCapituloFraccion, cvePartidaFraccion));
    }

    @PostMapping(CatalogPaths.SAVE_SUBPARTIDA_FRACCION)
    public ResponseEntity<CatSubpartidaFraccionDTO> create(@RequestBody CatSubpartidaFraccionDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PutMapping(CatalogPaths.UPDATE_SUBPARTIDA_FRACCION)
    public ResponseEntity<CatSubpartidaFraccionDTO> update(
            @PathVariable String cveSubpartidaFraccion,
            @PathVariable String cveCapituloFraccion,
            @PathVariable String cvePartidaFraccion,
            @RequestBody CatSubpartidaFraccionDTO dto) {
        return ResponseEntity.ok(service.update(cveSubpartidaFraccion, cveCapituloFraccion, cvePartidaFraccion, dto));
    }
}
