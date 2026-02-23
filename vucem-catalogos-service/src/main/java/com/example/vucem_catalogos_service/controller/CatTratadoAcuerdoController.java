package com.example.vucem_catalogos_service.controller;

import com.example.vucem_catalogos_service.business.Interface.ICatTratadoAcuerdoService;
import com.example.vucem_catalogos_service.core.constants.CatalogPaths;
import com.example.vucem_catalogos_service.model.dto.CatTratadoAcuerdoDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(CatalogPaths.CONTROLLER)
public class CatTratadoAcuerdoController {

    @Autowired
    private ICatTratadoAcuerdoService service;

    @GetMapping(CatalogPaths.LIST_TRATADO_ACUERDO)
    public ResponseEntity<PageResponseDTO<CatTratadoAcuerdoDTO>> list(
            @RequestParam(required = false) String search,
            Pageable pageable) {
        return ResponseEntity.ok(service.list(search, pageable));
    }

    @GetMapping(CatalogPaths.FIND_TRATADO_ACUERDO)
    public ResponseEntity<CatTratadoAcuerdoDTO> findById(@PathVariable Short id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping(CatalogPaths.SAVE_TRATADO_ACUERDO)
    public ResponseEntity<CatTratadoAcuerdoDTO> create(@RequestBody CatTratadoAcuerdoDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PutMapping(CatalogPaths.UPDATE_TRATADO_ACUERDO)
    public ResponseEntity<CatTratadoAcuerdoDTO> update(
            @PathVariable Short id,
            @RequestBody CatTratadoAcuerdoDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }
}
