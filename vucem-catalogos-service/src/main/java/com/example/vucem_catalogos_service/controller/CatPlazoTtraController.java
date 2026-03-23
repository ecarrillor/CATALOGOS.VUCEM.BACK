package com.example.vucem_catalogos_service.controller;

import com.example.vucem_catalogos_service.business.Interface.ICatPlazoTtraService;
import com.example.vucem_catalogos_service.core.constants.CatalogPaths;
import com.example.vucem_catalogos_service.model.dto.CatPlazoTtraDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(CatalogPaths.CONTROLLER)
public class CatPlazoTtraController {

    @Autowired
    private ICatPlazoTtraService service;

    @GetMapping(CatalogPaths.LIST_PLAZO_TTRA)
    public ResponseEntity<PageResponseDTO<CatPlazoTtraDTO>> list(
            @RequestParam(required = false) String search,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) String sortDir) {
        return ResponseEntity.ok(service.list(search, page, size, sortBy, sortDir));
    }

    @GetMapping(CatalogPaths.FIND_PLAZO_TTRA)
    public ResponseEntity<CatPlazoTtraDTO> findById(
            @PathVariable Long idTipoTramite,
            @PathVariable String idePlazoVigencia) {
        return ResponseEntity.ok(service.findById(idTipoTramite, idePlazoVigencia));
    }

    @PostMapping(CatalogPaths.SAVE_PLAZO_TTRA)
    public ResponseEntity<CatPlazoTtraDTO> create(@RequestBody CatPlazoTtraDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PutMapping(CatalogPaths.UPDATE_PLAZO_TTRA)
    public ResponseEntity<CatPlazoTtraDTO> update(
            @PathVariable Long idTipoTramite,
            @PathVariable String idePlazoVigencia,
            @RequestBody CatPlazoTtraDTO dto) {
        return ResponseEntity.ok(service.update(idTipoTramite, idePlazoVigencia, dto));
    }
}
