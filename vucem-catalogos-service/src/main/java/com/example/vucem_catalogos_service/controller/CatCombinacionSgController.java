package com.example.vucem_catalogos_service.controller;

import com.example.vucem_catalogos_service.business.Interface.ICatCombinacionSgService;
import com.example.vucem_catalogos_service.core.constants.CatalogPaths;
import com.example.vucem_catalogos_service.model.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(CatalogPaths.CONTROLLER)
public class CatCombinacionSgController {

    @Autowired
    private ICatCombinacionSgService service;

    @GetMapping(CatalogPaths.LIST_COMBINACION_SG)
    public ResponseEntity<PageResponseDTO<CatCombinacionSgDTO>> list(
            @RequestParam(required = false) String search,
            Pageable pageable) {
        return ResponseEntity.ok(service.list(search, pageable));
    }

    @GetMapping(CatalogPaths.FIND_COMBINACION_SG)
    public ResponseEntity<CatCombinacionSgDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping(CatalogPaths.SAVE_COMBINACION_SG)
    public ResponseEntity<CatCombinacionSgDTO> create(@RequestBody CatCombinacionSgDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PutMapping(CatalogPaths.UPDATE_COMBINACION_SG)
    public ResponseEntity<CatCombinacionSgDTO> update(
            @PathVariable Long id,
            @RequestBody CatCombinacionSgDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @GetMapping(CatalogPaths.LIST_TIPO_CERTIFICADO)
    public ResponseEntity<CombinacionReqResponseDTO> listTipoCertificado (@PathVariable String id) {
        return ResponseEntity.ok(service.listTipoCertificado(id));
    }

    @GetMapping(CatalogPaths.LIST_UPDATE_TIPO_CERTIFICADO)
    public ResponseEntity<CombinacionReqUpdateDTO> listUpdateTipoCertificado (@PathVariable String id, @PathVariable String catalogo) {
        return ResponseEntity.ok(service.listUpdateTipoCertificado(id, catalogo));
    }
}
