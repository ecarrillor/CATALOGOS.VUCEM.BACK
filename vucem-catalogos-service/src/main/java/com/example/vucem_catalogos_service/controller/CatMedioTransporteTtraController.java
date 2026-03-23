package com.example.vucem_catalogos_service.controller;

import com.example.vucem_catalogos_service.business.Interface.ICatMedioTransporteTtraService;
import com.example.vucem_catalogos_service.core.constants.CatalogPaths;
import com.example.vucem_catalogos_service.model.dto.CatMedioTransporteTtraDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(CatalogPaths.CONTROLLER)
public class CatMedioTransporteTtraController {

    @Autowired
    private ICatMedioTransporteTtraService service;

    @GetMapping(CatalogPaths.LIST_MEDIO_TRANSPORTE_TTRA)
    public ResponseEntity<PageResponseDTO<CatMedioTransporteTtraDTO>> list(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) String sortDir,
            Pageable pageable) {
        return ResponseEntity.ok(service.list(search, sortBy, sortDir, pageable));
    }

    @GetMapping(CatalogPaths.FIND_MEDIO_TRANSPORTE_TTRA)
    public ResponseEntity<CatMedioTransporteTtraDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping(CatalogPaths.SAVE_MEDIO_TRANSPORTE_TTRA)
    public ResponseEntity<CatMedioTransporteTtraDTO> create(@RequestBody CatMedioTransporteTtraDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PutMapping(CatalogPaths.UPDATE_MEDIO_TRANSPORTE_TTRA)
    public ResponseEntity<CatMedioTransporteTtraDTO> update(
            @PathVariable Integer id,
            @RequestBody CatMedioTransporteTtraDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }
}
