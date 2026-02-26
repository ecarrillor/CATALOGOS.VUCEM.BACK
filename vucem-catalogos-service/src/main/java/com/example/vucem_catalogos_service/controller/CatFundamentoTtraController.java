package com.example.vucem_catalogos_service.controller;

import com.example.vucem_catalogos_service.business.Interface.ICatFundamentoTtraService;
import com.example.vucem_catalogos_service.core.constants.CatalogPaths;
import com.example.vucem_catalogos_service.model.dto.CatFundamentoTtraDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(CatalogPaths.CONTROLLER)
public class CatFundamentoTtraController {

    @Autowired
    private ICatFundamentoTtraService service;

    @GetMapping(CatalogPaths.LIST_FUNDAMENTO_TTRA)
    public ResponseEntity<PageResponseDTO<CatFundamentoTtraDTO>> list(
            @RequestParam(required = false) String search,
            Pageable pageable) {
        return ResponseEntity.ok(service.list(search, pageable));
    }

    @GetMapping(CatalogPaths.FIND_FUNDAMENTO_TTRA)
    public ResponseEntity<CatFundamentoTtraDTO> findById(@PathVariable Short id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping(CatalogPaths.SAVE_FUNDAMENTO_TTRA)
    public ResponseEntity<CatFundamentoTtraDTO> create(@RequestBody CatFundamentoTtraDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PutMapping(CatalogPaths.UPDATE_FUNDAMENTO_TTRA)
    public ResponseEntity<CatFundamentoTtraDTO> update(
            @PathVariable Short id,
            @RequestBody CatFundamentoTtraDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }
}
