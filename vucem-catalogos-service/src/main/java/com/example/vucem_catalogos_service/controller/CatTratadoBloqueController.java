package com.example.vucem_catalogos_service.controller;

import com.example.vucem_catalogos_service.business.Interface.ICatTratadoBloqueService;
import com.example.vucem_catalogos_service.core.constants.CatalogPaths;
import com.example.vucem_catalogos_service.model.dto.CatTratadoBloqueDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(CatalogPaths.CONTROLLER)
public class CatTratadoBloqueController {

    @Autowired
    private ICatTratadoBloqueService service;

    @GetMapping(CatalogPaths.LIST_TRATADO_BLOQUE)
    public ResponseEntity<PageResponseDTO<CatTratadoBloqueDTO>> list(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) String sortDir,
            Pageable pageable) {
        return ResponseEntity.ok(service.list(search, sortBy, sortDir, pageable));
    }

    @GetMapping(CatalogPaths.FIND_TRATADO_BLOQUE)
    public ResponseEntity<CatTratadoBloqueDTO> findById(
            @PathVariable Short idTratadoAcuerdo,
            @PathVariable Short idBloque) {
        return ResponseEntity.ok(service.findById(idTratadoAcuerdo, idBloque));
    }

    @PostMapping(CatalogPaths.SAVE_TRATADO_BLOQUE)
    public ResponseEntity<CatTratadoBloqueDTO> create(@RequestBody CatTratadoBloqueDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PutMapping(CatalogPaths.UPDATE_TRATADO_BLOQUE)
    public ResponseEntity<CatTratadoBloqueDTO> update(
            @PathVariable Short idTratadoAcuerdo,
            @PathVariable Short idBloque,
            @RequestBody CatTratadoBloqueDTO dto) {
        return ResponseEntity.ok(service.update(idTratadoAcuerdo, idBloque, dto));
    }
}
