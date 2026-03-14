package com.example.vucem_catalogos_service.controller;

import com.example.vucem_catalogos_service.business.Interface.ICatEquivMonedaService;
import com.example.vucem_catalogos_service.core.constants.CatalogPaths;
import com.example.vucem_catalogos_service.model.dto.CatEquivMonedaDTO;
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
public class CatEquivMonedaController {

    @Autowired
    private ICatEquivMonedaService service;

    @GetMapping(CatalogPaths.LIST_EQUIV_MONEDA)
    public ResponseEntity<PageResponseDTO<CatEquivMonedaDTO>> list(
            @RequestParam(required = false) String search,
            Pageable pageable) {
        return ResponseEntity.ok(service.list(search, pageable));
    }

    @GetMapping(CatalogPaths.FIND_EQUIV_MONEDA)
    public ResponseEntity<CatEquivMonedaDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping(CatalogPaths.SAVE_EQUIV_MONEDA)
    public ResponseEntity<CatEquivMonedaDTO> save(@RequestBody CatEquivMonedaDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PutMapping(CatalogPaths.UPDATE_EQUIV_MONEDA)
    public ResponseEntity<CatEquivMonedaDTO> update(
            @PathVariable Integer id,
            @RequestBody CatEquivMonedaDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }


    @GetMapping(CatalogPaths.LIST_MONEDAS_SELECT_MONDEST)
    public ResponseEntity<List<SelectDTO>> buscarMonedasDest(
            @RequestParam String term) {

        return ResponseEntity.ok(service.buscarMonedasDest(term));
    }
}
