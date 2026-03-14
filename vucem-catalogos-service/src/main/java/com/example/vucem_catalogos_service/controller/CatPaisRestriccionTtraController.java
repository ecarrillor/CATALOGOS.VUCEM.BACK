package com.example.vucem_catalogos_service.controller;

import com.example.vucem_catalogos_service.business.Interface.ICatPaisRestriccionTtraService;
import com.example.vucem_catalogos_service.core.constants.CatalogPaths;
import com.example.vucem_catalogos_service.model.dto.CatPaisRestriccionTtraDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(CatalogPaths.CONTROLLER)
public class CatPaisRestriccionTtraController {

    @Autowired
    private ICatPaisRestriccionTtraService service;

    @GetMapping(CatalogPaths.LIST_PAIS_RESTRICCION_TTRA)
    public ResponseEntity<PageResponseDTO<CatPaisRestriccionTtraDTO>> list(
            @RequestParam(required = false) String search,
            Pageable pageable) {
        return ResponseEntity.ok(service.list(search, pageable));
    }

    @GetMapping(CatalogPaths.FIND_PAIS_RESTRICCION_TTRA)
    public ResponseEntity<CatPaisRestriccionTtraDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping(CatalogPaths.SAVE_PAIS_RESTRICCION_TTRA)
    public ResponseEntity<CatPaisRestriccionTtraDTO> create(@RequestBody CatPaisRestriccionTtraDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PutMapping(CatalogPaths.UPDATE_PAIS_RESTRICCION_TTRA)
    public ResponseEntity<CatPaisRestriccionTtraDTO> update(@PathVariable Integer id,
                                                            @RequestBody CatPaisRestriccionTtraDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }
}
