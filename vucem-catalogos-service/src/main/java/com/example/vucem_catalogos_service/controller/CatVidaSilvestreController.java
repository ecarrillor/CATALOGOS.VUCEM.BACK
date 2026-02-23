package com.example.vucem_catalogos_service.controller;

import com.example.vucem_catalogos_service.business.Interface.ICatVidaSilvestreService;
import com.example.vucem_catalogos_service.core.constants.CatalogPaths;
import com.example.vucem_catalogos_service.model.dto.CatVidaSilvestreDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.entity.CatEspecie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(CatalogPaths.CONTROLLER)
public class CatVidaSilvestreController {

    @Autowired
    private ICatVidaSilvestreService service;

    @GetMapping(CatalogPaths.LIST_VIDA_SILVESTRE)
    public ResponseEntity<PageResponseDTO<CatVidaSilvestreDTO>> list(
            @RequestParam(required = false) String search,
            Pageable pageable) {
        return ResponseEntity.ok(service.list(search, pageable));
    }

    @GetMapping(CatalogPaths.FIND_VIDA_SILVESTRE)
    public ResponseEntity<CatVidaSilvestreDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping(CatalogPaths.SAVE_VIDA_SILVESTRE)
    public ResponseEntity<CatVidaSilvestreDTO> create(@RequestBody CatVidaSilvestreDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PutMapping(CatalogPaths.UPDATE_VIDA_SILVESTRE)
    public ResponseEntity<CatVidaSilvestreDTO> update(
            @PathVariable Integer id,
            @RequestBody CatVidaSilvestreDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @GetMapping(CatalogPaths.LIST_ESPECIES_ACTIVAS)
    public ResponseEntity<List<CatEspecie>> listEspeciesActivas() {
        return ResponseEntity.ok(service.listEspeciesActivas());
    }
}
