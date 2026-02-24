package com.example.vucem_catalogos_service.controller;

import com.example.vucem_catalogos_service.business.Interface.ICatTipoTramiteService;
import com.example.vucem_catalogos_service.core.constants.CatalogPaths;
import com.example.vucem_catalogos_service.model.dto.CatTipoTramiteDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(CatalogPaths.CONTROLLER)
public class CatTipoTramiteController {

    @Autowired
    private ICatTipoTramiteService service;

    @GetMapping(CatalogPaths.LIST_TIPO_TRAMITE)
    public ResponseEntity<PageResponseDTO<CatTipoTramiteDTO>> list(
            @RequestParam(required = false) String search,
            Pageable pageable) {
        return ResponseEntity.ok(service.list(search, pageable));
    }

    @GetMapping(CatalogPaths.FIND_TIPO_TRAMITE)
    public ResponseEntity<CatTipoTramiteDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping(CatalogPaths.SAVE_TIPO_TRAMITE)
    public ResponseEntity<CatTipoTramiteDTO> create(@RequestBody CatTipoTramiteDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PutMapping(CatalogPaths.UPDATE_TIPO_TRAMITE)
    public ResponseEntity<CatTipoTramiteDTO> update(
            @PathVariable Long id,
            @RequestBody CatTipoTramiteDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }
}
