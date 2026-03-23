package com.example.vucem_catalogos_service.controller;

import com.example.vucem_catalogos_service.business.Interface.ICatRecintoFiscalizadoService;
import com.example.vucem_catalogos_service.core.constants.CatalogPaths;
import com.example.vucem_catalogos_service.model.dto.CatRecintoFiscalizadoDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(CatalogPaths.CONTROLLER)
public class CatRecintoFiscalizadoController {

    @Autowired
    private ICatRecintoFiscalizadoService service;

    @GetMapping(CatalogPaths.LIST_RECINTO_FISCALIZADO)
    public ResponseEntity<PageResponseDTO<CatRecintoFiscalizadoDTO>> list(
            @RequestParam(required = false) String search,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) String sortDir) {
        return ResponseEntity.ok(service.list(search, page, size, sortBy, sortDir));
    }

    @GetMapping(CatalogPaths.FIND_RECINTO_FISCALIZADO)
    public ResponseEntity<CatRecintoFiscalizadoDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping(CatalogPaths.SAVE_RECINTO_FISCALIZADO)
    public ResponseEntity<CatRecintoFiscalizadoDTO> create(@RequestBody CatRecintoFiscalizadoDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PutMapping(CatalogPaths.UPDATE_RECINTO_FISCALIZADO)
    public ResponseEntity<CatRecintoFiscalizadoDTO> update(
            @PathVariable Long id,
            @RequestBody CatRecintoFiscalizadoDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }
}
