package com.example.vucem_catalogos_service.controller;

import com.example.vucem_catalogos_service.business.Interface.ICatPlazoMaximoAutTramiteService;
import com.example.vucem_catalogos_service.core.constants.CatalogPaths;
import com.example.vucem_catalogos_service.model.dto.CatPlazoMaximoAutTramiteDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDate;

@RestController
@RequestMapping(CatalogPaths.CONTROLLER)
public class CatPlazoMaximoAutTramiteController {

    @Autowired
    private ICatPlazoMaximoAutTramiteService service;

    @GetMapping(CatalogPaths.LIST_PLAZO_MAX_TTRA)
    public ResponseEntity<PageResponseDTO<CatPlazoMaximoAutTramiteDTO>> list(
            @RequestParam(required = false) String search,
            Pageable pageable) {
        return ResponseEntity.ok(service.list(search, pageable));
    }

    @GetMapping(CatalogPaths.FIND_PLAZO_MAX_TTRA)
    public ResponseEntity<CatPlazoMaximoAutTramiteDTO> findById(
            @PathVariable Long idTipoTramite,
            @PathVariable
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate fecIniVigencia) {
        return ResponseEntity.ok(service.findById(idTipoTramite, fecIniVigencia));
    }

    @PostMapping(CatalogPaths.SAVE_PLAZO_MAX_TTRA)
    public ResponseEntity<CatPlazoMaximoAutTramiteDTO> create(@RequestBody CatPlazoMaximoAutTramiteDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PutMapping(CatalogPaths.UPDATE_PLAZO_MAX_TTRA)
    public ResponseEntity<CatPlazoMaximoAutTramiteDTO> update(
            @PathVariable Long idTipoTramite,
            @PathVariable LocalDate fecIniVigencia,
            @RequestBody CatPlazoMaximoAutTramiteDTO dto) {
        return ResponseEntity.ok(service.update(idTipoTramite, fecIniVigencia, dto));
    }
}
