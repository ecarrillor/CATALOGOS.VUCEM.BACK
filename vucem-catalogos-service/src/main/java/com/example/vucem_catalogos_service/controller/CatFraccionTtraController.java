package com.example.vucem_catalogos_service.controller;

import com.example.vucem_catalogos_service.business.Interface.ICatFraccionTtraService;
import com.example.vucem_catalogos_service.core.constants.CatalogPaths;
import com.example.vucem_catalogos_service.model.dto.ClasifProductoTraDTO;
import com.example.vucem_catalogos_service.model.dto.FraccionTtra.CatFraccionTtraRequestDTO;
import com.example.vucem_catalogos_service.model.dto.FraccionTtra.CatFraccionTtraResponseDTO;
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
public class CatFraccionTtraController {

    @Autowired
    private ICatFraccionTtraService service;

    @GetMapping(CatalogPaths.LIST_FRACCION_TTRA)
    public ResponseEntity<PageResponseDTO<CatFraccionTtraResponseDTO>> listar(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) Long idTipoTramite,
            Pageable pageable) {
        return ResponseEntity.ok(service.listAll(search, idTipoTramite, pageable));
    }

    @PostMapping(CatalogPaths.SAVE_FRACCION_TTRA)
    public ResponseEntity<CatFraccionTtraResponseDTO> crear(@RequestBody CatFraccionTtraRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.crear(dto));
    }

    @GetMapping(CatalogPaths.FIND_FRACCION_TTRA)
    public ResponseEntity<CatFraccionTtraResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PutMapping(CatalogPaths.UPDATE_FRACCION_TTRA)
    public ResponseEntity<CatFraccionTtraResponseDTO> update(
            @PathVariable Long id,
            @RequestBody CatFraccionTtraRequestDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @GetMapping(CatalogPaths.TIPO_TRAMITE_FRACCION_TTRA)
    public ResponseEntity<List<ClasifProductoTraDTO>> listadoTipoTramite() {
        return ResponseEntity.ok(service.listadoTipoTramite());
    }

    @GetMapping(CatalogPaths.CATEGORIA_TEXTIL_FRACCION)
    public ResponseEntity<List<SelectDTO>> listadoCategoriaTextil() {
        return ResponseEntity.ok(service.listadoCategoriaTextil());
    }
}
