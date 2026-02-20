package com.example.vucem_catalogos_service.controller;

import com.example.vucem_catalogos_service.business.Interface.ICatFraccionHtsUsaService;
import com.example.vucem_catalogos_service.core.constants.CatalogPaths;
import com.example.vucem_catalogos_service.model.dto.CatFraccionHtsUsaDTO;
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
public class CatFraccionHtsUsaController {

    @Autowired
    private ICatFraccionHtsUsaService service;

    @GetMapping(CatalogPaths.LIST_FRACCION_HTS_USA)
    public ResponseEntity<PageResponseDTO<CatFraccionHtsUsaDTO>> list(
            @RequestParam(required = false) String search,
            Pageable pageable) {
        return ResponseEntity.ok(service.list(search, pageable));
    }

    @GetMapping(CatalogPaths.FIND_FRACCION_HTS_USA)
    public ResponseEntity<CatFraccionHtsUsaDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping(CatalogPaths.SAVE_FRACCION_HTS_USA)
    public ResponseEntity<CatFraccionHtsUsaDTO> save(@RequestBody CatFraccionHtsUsaDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PutMapping(CatalogPaths.UPDATE_FRACCION_HTS_USA)
    public ResponseEntity<CatFraccionHtsUsaDTO> update(
            @PathVariable Long id,
            @RequestBody CatFraccionHtsUsaDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @GetMapping(CatalogPaths.LIST_UNIDADES_MEDIDA_SELECT)
    public ResponseEntity<List<SelectDTO>> listadoUnidadesMedida() {
        return ResponseEntity.ok(service.listadoUnidadesMedida());
    }
}
