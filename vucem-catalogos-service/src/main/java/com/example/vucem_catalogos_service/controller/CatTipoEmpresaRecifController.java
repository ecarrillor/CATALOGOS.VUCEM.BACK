package com.example.vucem_catalogos_service.controller;

import com.example.vucem_catalogos_service.business.Interface.ICatTipoEmpresaRecifService;
import com.example.vucem_catalogos_service.core.constants.CatalogPaths;
import com.example.vucem_catalogos_service.model.dto.CatTipoEmpresaRecifDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(CatalogPaths.CONTROLLER)
public class CatTipoEmpresaRecifController {

    @Autowired
    private ICatTipoEmpresaRecifService service;

    @GetMapping(CatalogPaths.LIST_TIPO_EMPRESA_RECIF)
    public ResponseEntity<PageResponseDTO<CatTipoEmpresaRecifDTO>> list(
            @RequestParam(required = false) String search,
            Pageable pageable) {
        return ResponseEntity.ok(service.list(search, pageable));
    }

    @GetMapping(CatalogPaths.FIND_TIPO_EMPRESA_RECIF)
    public ResponseEntity<CatTipoEmpresaRecifDTO> findById(@PathVariable String cveTipoEmpresaRecif) {
        return ResponseEntity.ok(service.findById(cveTipoEmpresaRecif));
    }

    @PostMapping(CatalogPaths.SAVE_TIPO_EMPRESA_RECIF)
    public ResponseEntity<CatTipoEmpresaRecifDTO> create(@RequestBody CatTipoEmpresaRecifDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PutMapping(CatalogPaths.UPDATE_TIPO_EMPRESA_RECIF)
    public ResponseEntity<CatTipoEmpresaRecifDTO> update(
            @PathVariable String cveTipoEmpresaRecif,
            @RequestBody CatTipoEmpresaRecifDTO dto) {
        return ResponseEntity.ok(service.update(cveTipoEmpresaRecif, dto));
    }
}
