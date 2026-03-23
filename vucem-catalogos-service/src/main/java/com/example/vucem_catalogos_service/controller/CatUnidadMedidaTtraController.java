package com.example.vucem_catalogos_service.controller;

import com.example.vucem_catalogos_service.business.Interface.ICatUnidadMedidaTtraService;
import com.example.vucem_catalogos_service.core.constants.CatalogPaths;
import com.example.vucem_catalogos_service.model.dto.CatUnidadMedidaTtraDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(CatalogPaths.CONTROLLER)
public class CatUnidadMedidaTtraController {

    @Autowired
    private ICatUnidadMedidaTtraService service;

    @GetMapping(CatalogPaths.LIST_UNIDAD_MEDIDA_TTRA)
    public ResponseEntity<PageResponseDTO<CatUnidadMedidaTtraDTO>> list(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) String sortDir,
            Pageable pageable) {
        return ResponseEntity.ok(service.list(search, sortBy, sortDir, pageable));
    }

    @GetMapping(CatalogPaths.FIND_UNIDAD_MEDIDA_TTRA)
    public ResponseEntity<CatUnidadMedidaTtraDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping(CatalogPaths.SAVE_UNIDAD_MEDIDA_TTRA)
    public ResponseEntity<CatUnidadMedidaTtraDTO> create(@RequestBody CatUnidadMedidaTtraDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PutMapping(CatalogPaths.UPDATE_UNIDAD_MEDIDA_TTRA)
    public ResponseEntity<CatUnidadMedidaTtraDTO> update(
            @PathVariable Integer id,
            @RequestBody CatUnidadMedidaTtraDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }
}
