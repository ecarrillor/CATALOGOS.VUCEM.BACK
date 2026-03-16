package com.example.vucem_catalogos_service.controller;

import com.example.vucem_catalogos_service.business.Interface.ICatRestriccionTtraService;
import com.example.vucem_catalogos_service.core.constants.CatalogPaths;
import com.example.vucem_catalogos_service.model.dto.ClasifProductoTraDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.dto.RestriccionTtra.CatRestriccionTtraRequestDTO;
import com.example.vucem_catalogos_service.model.dto.RestriccionTtra.CatRestriccionTtraResponseDTO;
import com.example.vucem_catalogos_service.model.dto.SelectDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(CatalogPaths.CONTROLLER)
public class CatRestriccionTtraController {

    @Autowired
    private ICatRestriccionTtraService service;

    @GetMapping(CatalogPaths.LIST_CATALOGO_RESTRICCION_TTRA)
    public ResponseEntity<PageResponseDTO<CatRestriccionTtraResponseDTO>> listar(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) Long idTipoTramite,
            Pageable pageable) {
        return ResponseEntity.ok(service.listAll(search, idTipoTramite, pageable));
    }

    @PostMapping(CatalogPaths.SAVE_CATALOGO_RESTRICCION_TTRA)
    public ResponseEntity<CatRestriccionTtraResponseDTO> crear(@RequestBody CatRestriccionTtraRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.crear(dto));
    }

    @GetMapping(CatalogPaths.FIND_CATALOGO_RESTRICCION_TTRA)
    public ResponseEntity<CatRestriccionTtraResponseDTO> findById(@PathVariable Short id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PutMapping(CatalogPaths.UPDATE_CATALOGO_RESTRICCION_TTRA)
    public ResponseEntity<CatRestriccionTtraResponseDTO> update(
            @PathVariable Short id,
            @RequestBody CatRestriccionTtraRequestDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @GetMapping(CatalogPaths.LIST_TIPO_TRAMITE_RESTRICCION_TTRA)
    public ResponseEntity<List<ClasifProductoTraDTO>> listadoTipoTramite() {
        return ResponseEntity.ok(service.listadoTipoTramite());
    }

    @GetMapping(CatalogPaths.LAST_RESTRICCION_TTRA)
    public ResponseEntity<ClasifProductoTraDTO> lastRestriccionTtra() {
        return ResponseEntity.ok(service.lastRestriccionTtra());
    }
}
