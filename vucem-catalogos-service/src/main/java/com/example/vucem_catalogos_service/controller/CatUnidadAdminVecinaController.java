package com.example.vucem_catalogos_service.controller;

import com.example.vucem_catalogos_service.business.Interface.ICatUnidadAdminVecinaService;
import com.example.vucem_catalogos_service.core.constants.CatalogPaths;
import com.example.vucem_catalogos_service.model.dto.CatUnidadAdminVecinaDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(CatalogPaths.CONTROLLER)
public class CatUnidadAdminVecinaController {

    @Autowired
    private ICatUnidadAdminVecinaService service;

    @GetMapping(CatalogPaths.LIST_UNIDAD_ADMIN_VECINA)
    public ResponseEntity<PageResponseDTO<CatUnidadAdminVecinaDTO>> list(
            @RequestParam(required = false) String search,
            Pageable pageable) {
        return ResponseEntity.ok(service.list(search, pageable));
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
}
