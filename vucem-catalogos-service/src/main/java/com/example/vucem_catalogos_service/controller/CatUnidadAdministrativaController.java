package com.example.vucem_catalogos_service.controller;

import com.example.vucem_catalogos_service.business.Interface.ICatUnidadAdministrativaService;
import com.example.vucem_catalogos_service.core.constants.CatalogPaths;
import com.example.vucem_catalogos_service.model.dto.CatUnidadAdministrativaDTO;
import com.example.vucem_catalogos_service.model.dto.CatUnidadAdministrativaNameDTO;
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
public class CatUnidadAdministrativaController {

    @Autowired
    private ICatUnidadAdministrativaService service;

    @GetMapping(CatalogPaths.LIST_UNIDAD_ADMIN_LIST_LAB)
    public ResponseEntity<List<CatUnidadAdministrativaNameDTO>> findByAll() {
        return ResponseEntity.ok(service.findByAll());
    }

    @GetMapping(CatalogPaths.LIST_UNIDAD_ADMIN)
    public ResponseEntity<PageResponseDTO<CatUnidadAdministrativaDTO>> list(
            @RequestParam(required = false) String search,
            Pageable pageable) {
        return ResponseEntity.ok(service.list(search, pageable));
    }

    @GetMapping(CatalogPaths.FIND_UNIDAD_ADMIN)
    public ResponseEntity<CatUnidadAdministrativaDTO> findById(
            @PathVariable String cveUnidadAdministrativa) {
        return ResponseEntity.ok(service.findById(cveUnidadAdministrativa));
    }

    @PostMapping(CatalogPaths.SAVE_UNIDAD_ADMIN)
    public ResponseEntity<CatUnidadAdministrativaDTO> save(
            @RequestBody CatUnidadAdministrativaDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PutMapping(CatalogPaths.UPDATE_UNIDAD_ADMIN)
    public ResponseEntity<CatUnidadAdministrativaDTO> update(
            @PathVariable String cveUnidadAdministrativa,
            @RequestBody CatUnidadAdministrativaDTO dto) {
        return ResponseEntity.ok(service.update(cveUnidadAdministrativa, dto));
    }

    @GetMapping(CatalogPaths.LIST_ENTIDADES_UNIDAD_ADMIN)
    public ResponseEntity<List<SelectDTO>> listadoEntidades() {
        return ResponseEntity.ok(service.listadoEntidades());
    }

    @GetMapping(CatalogPaths.LIST_DEPENDENCIAS_UNIDAD_ADMIN)
    public ResponseEntity<List<SelectDTO>> listadoDependencias() {
        return ResponseEntity.ok(service.listadoDependencias());
    }
}
