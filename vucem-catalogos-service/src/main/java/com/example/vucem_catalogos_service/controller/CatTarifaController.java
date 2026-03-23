package com.example.vucem_catalogos_service.controller;

import com.example.vucem_catalogos_service.business.Interface.ICatTarifaService;
import com.example.vucem_catalogos_service.core.constants.CatalogPaths;
import com.example.vucem_catalogos_service.model.dto.CatTarifaDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDate;

@RestController
@RequestMapping(CatalogPaths.CONTROLLER)
public class CatTarifaController {

    @Autowired
    private ICatTarifaService service;

    @GetMapping(CatalogPaths.LIST_TARIFA)
    public ResponseEntity<PageResponseDTO<CatTarifaDTO>> list(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) String sortDir,
            Pageable pageable) {
        return ResponseEntity.ok(service.list(search, sortBy, sortDir, pageable));
    }

    @GetMapping(CatalogPaths.FIND_TARIFA)
    public ResponseEntity<CatTarifaDTO> findById(
            @PathVariable Long idTipoTramite,
            @PathVariable LocalDate fecIniVigencia,
            @PathVariable String ideTipoTarifa) {
        return ResponseEntity.ok(service.findById(idTipoTramite,fecIniVigencia, ideTipoTarifa));
    }

    @PostMapping(CatalogPaths.SAVE_TARIFA)
    public ResponseEntity<CatTarifaDTO> create(@RequestBody CatTarifaDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PutMapping(CatalogPaths.UPDATE_TARIFA)
    public ResponseEntity<CatTarifaDTO> update(
            @PathVariable Long idTipoTramite,
            @PathVariable LocalDate fecIniVigencia,
            @PathVariable String ideTipoTarifa,
            @RequestBody CatTarifaDTO dto) {
        return ResponseEntity.ok(service.update(idTipoTramite, fecIniVigencia, ideTipoTarifa, dto));
    }
}
