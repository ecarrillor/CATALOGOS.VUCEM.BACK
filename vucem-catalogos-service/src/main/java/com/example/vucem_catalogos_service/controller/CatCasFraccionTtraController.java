package com.example.vucem_catalogos_service.controller;

import com.example.vucem_catalogos_service.business.Interface.ICatCasFraccionTtraService;
import com.example.vucem_catalogos_service.core.constants.CatalogPaths;
import com.example.vucem_catalogos_service.model.dto.CasFraccionTtra.CatCasFraccionTtraRequestDTO;
import com.example.vucem_catalogos_service.model.dto.CasFraccionTtra.CatCasFraccionTtraResponseDTO;
import com.example.vucem_catalogos_service.model.dto.ClasifProductoTraDTO;
import com.example.vucem_catalogos_service.model.dto.FraccionAranceSearchDTO;
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
public class CatCasFraccionTtraController {

    @Autowired
    private ICatCasFraccionTtraService service;

    @GetMapping(CatalogPaths.LIST_CAS_FRACCION_TTRA)
    public ResponseEntity<PageResponseDTO<CatCasFraccionTtraResponseDTO>> listar(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) Long idTipoTramite,
            Pageable pageable) {
        return ResponseEntity.ok(service.listAll(search, idTipoTramite, pageable));
    }

    @PostMapping(CatalogPaths.SAVE_CAS_FRACCION_TTRA)
    public ResponseEntity<CatCasFraccionTtraResponseDTO> crear(@RequestBody CatCasFraccionTtraRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.crear(dto));
    }

    @GetMapping(CatalogPaths.FIND_CAS_FRACCION_TTRA)
    public ResponseEntity<CatCasFraccionTtraResponseDTO> findById(@PathVariable Short id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PutMapping(CatalogPaths.UPDATE_CAS_FRACCION_TTRA)
    public ResponseEntity<CatCasFraccionTtraResponseDTO> update(
            @PathVariable Short id,
            @RequestBody CatCasFraccionTtraRequestDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @GetMapping(CatalogPaths.LIST_TIPO_TRAMITE_CAS_FRACC)
    public ResponseEntity<List<ClasifProductoTraDTO>> listadoTipoTramite() {
        return ResponseEntity.ok(service.listadoTipoTramite());
    }

    @GetMapping(CatalogPaths.LIST_CAS_SELECT)
    public ResponseEntity<List<SelectDTO>> listadoCas() {
        return ResponseEntity.ok(service.listadoCas());
    }

    @GetMapping(CatalogPaths.LIST_CAS_SELECT_FRACCION_AR)
    public ResponseEntity<List<FraccionAranceSearchDTO>> listadoFraccionAr() {
        return ResponseEntity.ok(service.listadoFraccionAr());
    }
}
