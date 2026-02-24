package com.example.vucem_catalogos_service.controller;

import com.example.vucem_catalogos_service.business.Interface.ICatTipoProductoTtraService;
import com.example.vucem_catalogos_service.core.constants.CatalogPaths;
import com.example.vucem_catalogos_service.model.dto.CatTipoProductoTtraDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(CatalogPaths.CONTROLLER)
public class CatTipoProductoTtraController {

    @Autowired
    private ICatTipoProductoTtraService service;

    @GetMapping(CatalogPaths.LIST_TIPO_PRODUCTO_TTRA)
    public ResponseEntity<PageResponseDTO<CatTipoProductoTtraDTO>> list(
            @RequestParam(required = false) String search,
            Pageable pageable) {
        return ResponseEntity.ok(service.list(search, pageable));
    }

    @GetMapping(CatalogPaths.FIND_TIPO_PRODUCTO_TTRA)
    public ResponseEntity<CatTipoProductoTtraDTO> findById(@PathVariable Short id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping(CatalogPaths.SAVE_TIPO_PRODUCTO_TTRA)
    public ResponseEntity<CatTipoProductoTtraDTO> create(@RequestBody CatTipoProductoTtraDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PutMapping(CatalogPaths.UPDATE_TIPO_PRODUCTO_TTRA)
    public ResponseEntity<CatTipoProductoTtraDTO> update(
            @PathVariable Short id,
            @RequestBody CatTipoProductoTtraDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }
}
