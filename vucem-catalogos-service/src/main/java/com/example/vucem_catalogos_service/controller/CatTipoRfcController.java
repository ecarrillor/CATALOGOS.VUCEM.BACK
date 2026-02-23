package com.example.vucem_catalogos_service.controller;

import com.example.vucem_catalogos_service.business.Interface.ICatTipoRfcService;
import com.example.vucem_catalogos_service.core.constants.CatalogPaths;
import com.example.vucem_catalogos_service.model.dto.CatTipoRfcDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(CatalogPaths.CONTROLLER)
public class CatTipoRfcController {

    @Autowired
    private ICatTipoRfcService service;

    @GetMapping(CatalogPaths.LIST_TIPO_RFC)
    public ResponseEntity<PageResponseDTO<CatTipoRfcDTO>> list(
            @RequestParam(required = false) String search,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(service.list(search, PageRequest.of(page, size)));
    }

    @GetMapping(CatalogPaths.FIND_TIPO_RFC)
    public ResponseEntity<CatTipoRfcDTO> findById(
            @PathVariable String rfc,
            @PathVariable String ideTipoRfc) {
        return ResponseEntity.ok(service.findById(rfc, ideTipoRfc));
    }

    @PostMapping(CatalogPaths.SAVE_TIPO_RFC)
    public ResponseEntity<CatTipoRfcDTO> create(@RequestBody CatTipoRfcDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PutMapping(CatalogPaths.UPDATE_TIPO_RFC)
    public ResponseEntity<CatTipoRfcDTO> update(
            @PathVariable String rfc,
            @PathVariable String ideTipoRfc,
            @RequestBody CatTipoRfcDTO dto) {
        return ResponseEntity.ok(service.update(rfc, ideTipoRfc, dto));
    }
}
