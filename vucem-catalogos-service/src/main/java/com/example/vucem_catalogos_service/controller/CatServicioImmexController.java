package com.example.vucem_catalogos_service.controller;

import com.example.vucem_catalogos_service.business.Interface.ICatServicioImmexService;
import com.example.vucem_catalogos_service.core.constants.CatalogPaths;
import com.example.vucem_catalogos_service.model.dto.CatServicioImmexDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(CatalogPaths.CONTROLLER)
public class CatServicioImmexController {

    @Autowired
    private ICatServicioImmexService service;

    @GetMapping(CatalogPaths.LIST_SERVICIO_IMMEX)
    public ResponseEntity<PageResponseDTO<CatServicioImmexDTO>> list(
            @RequestParam(required = false) String search,
            Pageable pageable) {
        return ResponseEntity.ok(service.list(search, pageable));
    }

    @GetMapping(CatalogPaths.FIND_SERVICIO_IMMEX)
    public ResponseEntity<CatServicioImmexDTO> findById(@PathVariable Short id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping(CatalogPaths.SAVE_SERVICIO_IMMEX)
    public ResponseEntity<CatServicioImmexDTO> create(@RequestBody CatServicioImmexDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PutMapping(CatalogPaths.UPDATE_SERVICIO_IMMEX)
    public ResponseEntity<CatServicioImmexDTO> update(
            @PathVariable Short id,
            @RequestBody CatServicioImmexDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }
}
