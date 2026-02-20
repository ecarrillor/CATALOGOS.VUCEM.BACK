package com.example.vucem_catalogos_service.controller;

import com.example.vucem_catalogos_service.business.Interface.ICatClasificacionRegimenService;
import com.example.vucem_catalogos_service.core.constants.CatalogPaths;
import com.example.vucem_catalogos_service.model.dto.CatClasificacionRegimenDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.dto.SelectDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(CatalogPaths.CONTROLLER)
public class CatClasificacionRegimenController {

    @Autowired
    private ICatClasificacionRegimenService service;

    @GetMapping(CatalogPaths.LIST_CLASIFICACION_REGIMEN)
    public ResponseEntity<PageResponseDTO<CatClasificacionRegimenDTO>> list(
            @RequestParam(required = false) String search,
            Pageable pageable) {
        return ResponseEntity.ok(service.list(search, pageable));
    }

    @GetMapping(CatalogPaths.FIND_CLASIFICACION_REGIMEN)
    public ResponseEntity<CatClasificacionRegimenDTO> findById(
            @PathVariable String cveClasificacionRegimen,
            @PathVariable String cveRegimen) {
        return ResponseEntity.ok(service.findById(cveClasificacionRegimen, cveRegimen));
    }

    @PostMapping(CatalogPaths.SAVE_CLASIFICACION_REGIMEN)
    public ResponseEntity<CatClasificacionRegimenDTO> save(
            @RequestBody CatClasificacionRegimenDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PutMapping(CatalogPaths.UPDATE_CLASIFICACION_REGIMEN)
    public ResponseEntity<CatClasificacionRegimenDTO> update(
            @PathVariable String cveClasificacionRegimen,
            @PathVariable String cveRegimen,
            @RequestBody CatClasificacionRegimenDTO dto) {
        return ResponseEntity.ok(service.update(cveClasificacionRegimen, cveRegimen, dto));
    }

    @GetMapping(CatalogPaths.LIST_REGIMENES_SELECT)
    public ResponseEntity<List<SelectDTO>> listadoRegimenes() {
        return ResponseEntity.ok(service.listadoRegimenes());
    }
}
