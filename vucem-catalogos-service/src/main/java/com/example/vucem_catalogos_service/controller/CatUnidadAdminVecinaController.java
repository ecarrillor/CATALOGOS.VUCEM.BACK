package com.example.vucem_catalogos_service.controller;

import com.example.vucem_catalogos_service.business.Interface.ICatUnidadAdminVecinaService;
import com.example.vucem_catalogos_service.core.constants.CatalogPaths;
import com.example.vucem_catalogos_service.model.dto.CatUnidadAdminVecinaDTO;
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
public class CatUnidadAdminVecinaController {

    @Autowired
    private ICatUnidadAdminVecinaService service;

    @GetMapping(CatalogPaths.LIST_UNIDAD_ADMIN_VECINA)
    public ResponseEntity<PageResponseDTO<CatUnidadAdminVecinaDTO>> list(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) String sortDir,
            Pageable pageable) {
        return ResponseEntity.ok(service.list(search, sortBy, sortDir, pageable));
    }

    @GetMapping(CatalogPaths.FIND_UNIDAD_ADMIN_VECINA)
    public ResponseEntity<CatUnidadAdminVecinaDTO> findById(
            @PathVariable String cveUnidadAdministrativa,
            @PathVariable String cveEntidad) {
        return ResponseEntity.ok(service.findById(cveUnidadAdministrativa, cveEntidad));
    }

    @PostMapping(CatalogPaths.SAVE_UNIDAD_ADMIN_VECINA)
    public ResponseEntity<CatUnidadAdminVecinaDTO> create(@RequestBody CatUnidadAdminVecinaDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PutMapping(CatalogPaths.UPDATE_UNIDAD_ADMIN_VECINA)
    public ResponseEntity<CatUnidadAdminVecinaDTO> update(
            @PathVariable String cveUnidadAdministrativa,
            @PathVariable String cveEntidad,
            @RequestBody CatUnidadAdminVecinaDTO dto) {
        return ResponseEntity.ok(service.update(cveUnidadAdministrativa, cveEntidad, dto));
    }
    @GetMapping(CatalogPaths.LIST_UNIDAD_ADMINISTRATIVA)
    public ResponseEntity<List<SelectDTO>> listUnidadAdministrativa () {
        return ResponseEntity.ok(service.listUnidadAdministrativa());
    }
}
