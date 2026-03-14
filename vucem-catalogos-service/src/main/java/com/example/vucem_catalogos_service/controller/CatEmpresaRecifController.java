package com.example.vucem_catalogos_service.controller;

import com.example.vucem_catalogos_service.business.Interface.ICatEmpresaRecifService;
import com.example.vucem_catalogos_service.core.constants.CatalogPaths;
import com.example.vucem_catalogos_service.model.dto.CatEmpresaRecifDTO;
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
public class CatEmpresaRecifController {

    @Autowired
    private ICatEmpresaRecifService service;

    @GetMapping(CatalogPaths.LIST_EMPRESA_RECIF)
    public ResponseEntity<PageResponseDTO<CatEmpresaRecifDTO>> list(
            @RequestParam(required = false) String search,
            Pageable pageable) {
        return ResponseEntity.ok(service.list(search, pageable));
    }

    @GetMapping(CatalogPaths.FIND_EMPRESA_RECIF)
    public ResponseEntity<CatEmpresaRecifDTO> findById(@PathVariable String recif) {
        return ResponseEntity.ok(service.findById(recif));
    }

    @PostMapping(CatalogPaths.SAVE_EMPRESA_RECIF)
    public ResponseEntity<CatEmpresaRecifDTO> save(@RequestBody CatEmpresaRecifDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PutMapping(CatalogPaths.UPDATE_EMPRESA_RECIF)
    public ResponseEntity<CatEmpresaRecifDTO> update(
            @PathVariable String recif,
            @RequestBody CatEmpresaRecifDTO dto) {
        return ResponseEntity.ok(service.update(recif, dto));
    }

    @GetMapping(CatalogPaths.LIST_UNIDADES_ADMIN_SELECT)
    public ResponseEntity<List<SelectDTO>> listadoUnidadesAdmin() {
        return ResponseEntity.ok(service.listadoUnidadesAdmin());
    }
}
