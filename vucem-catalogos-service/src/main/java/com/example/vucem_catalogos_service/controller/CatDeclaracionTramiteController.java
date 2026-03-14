package com.example.vucem_catalogos_service.controller;

import com.example.vucem_catalogos_service.business.Interface.ICatDeclaracionTramiteService;
import com.example.vucem_catalogos_service.core.constants.CatalogPaths;
import com.example.vucem_catalogos_service.model.dto.CatDeclaracionTramiteDTO;
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
public class CatDeclaracionTramiteController {

    @Autowired
    private ICatDeclaracionTramiteService service;

    @GetMapping(CatalogPaths.LIST_DECLARACION_TRAMITE)
    public ResponseEntity<PageResponseDTO<CatDeclaracionTramiteDTO>> list(
            @RequestParam(required = false) String search,
            Pageable pageable) {
        return ResponseEntity.ok(service.list(search, pageable));
    }

    @GetMapping(CatalogPaths.FIND_DECLARACION_TRAMITE)
    public ResponseEntity<CatDeclaracionTramiteDTO> findById(
            @PathVariable String cveDeclaracion,
            @PathVariable Integer idTipoTramite) {
        return ResponseEntity.ok(service.findById(cveDeclaracion, idTipoTramite));
    }

    @PostMapping(CatalogPaths.SAVE_DECLARACION_TRAMITE)
    public ResponseEntity<CatDeclaracionTramiteDTO> save(
            @RequestBody CatDeclaracionTramiteDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PutMapping(CatalogPaths.UPDATE_DECLARACION_TRAMITE)
    public ResponseEntity<CatDeclaracionTramiteDTO> update(
            @PathVariable String cveDeclaracion,
            @PathVariable Integer idTipoTramite,
            @RequestBody CatDeclaracionTramiteDTO dto) {
        return ResponseEntity.ok(service.update(cveDeclaracion, idTipoTramite, dto));
    }

    @GetMapping(CatalogPaths.LIST_DECLARACIONES_SELECT)
    public ResponseEntity<List<SelectDTO>> listadoDeclaraciones() {
        return ResponseEntity.ok(service.listadoDeclaraciones());
    }

    @GetMapping(CatalogPaths.LIST_TIPOS_TRAMITE_DECL)
    public ResponseEntity<List<SelectDTO>> listadoTiposTramite() {
        return ResponseEntity.ok(service.listadoTiposTramite());
    }
}
