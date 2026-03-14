package com.example.vucem_catalogos_service.controller;

import com.example.vucem_catalogos_service.business.Interface.ICatUnidadAdminAduanaService;
import com.example.vucem_catalogos_service.core.constants.CatalogPaths;
import com.example.vucem_catalogos_service.model.dto.CatUnidadAdminAduanaDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(CatalogPaths.CONTROLLER)
public class CatUnidadAdminAduanaController {

    @Autowired
    private ICatUnidadAdminAduanaService service;

    @GetMapping(CatalogPaths.LIST_UNIDAD_ADMIN_ADUANA)
    public ResponseEntity<PageResponseDTO<CatUnidadAdminAduanaDTO>> list(
            @RequestParam(required = false) String search,
            Pageable pageable) {
        return ResponseEntity.ok(service.list(search, pageable));
    }

    @GetMapping(CatalogPaths.FIND_UNIDAD_ADMIN_ADUANA)
    public ResponseEntity<CatUnidadAdminAduanaDTO> findById(
            @PathVariable String cveUnidadAdministrativa,
            @PathVariable String cveAduana) {
        return ResponseEntity.ok(service.findById(cveUnidadAdministrativa, cveAduana));
    }

    @PostMapping(CatalogPaths.SAVE_UNIDAD_ADMIN_ADUANA)
    public ResponseEntity<CatUnidadAdminAduanaDTO> create(@RequestBody CatUnidadAdminAduanaDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PutMapping(CatalogPaths.UPDATE_UNIDAD_ADMIN_ADUANA)
    public ResponseEntity<CatUnidadAdminAduanaDTO> update(
            @PathVariable String cveUnidadAdministrativa,
            @PathVariable String cveAduana,
            @RequestBody CatUnidadAdminAduanaDTO dto) {
        return ResponseEntity.ok(service.update(cveUnidadAdministrativa, cveAduana, dto));
    }
}
