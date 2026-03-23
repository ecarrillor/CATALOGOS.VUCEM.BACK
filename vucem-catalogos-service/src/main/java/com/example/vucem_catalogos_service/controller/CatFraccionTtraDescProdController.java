package com.example.vucem_catalogos_service.controller;

import com.example.vucem_catalogos_service.business.Interface.ICatFraccionTtraDescProdService;
import com.example.vucem_catalogos_service.core.constants.CatalogPaths;
import com.example.vucem_catalogos_service.model.dto.ClasifProductoTraDTO;
import com.example.vucem_catalogos_service.model.dto.FraccionTtraDescProd.CatFraccionTtraDescProdRequestDTO;
import com.example.vucem_catalogos_service.model.dto.FraccionTtraDescProd.CatFraccionTtraDescProdResponseDTO;
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
public class CatFraccionTtraDescProdController {

    @Autowired
    private ICatFraccionTtraDescProdService service;

    @GetMapping(CatalogPaths.LIST_FRACCION_TTRA_DESC_PROD)
    public ResponseEntity<PageResponseDTO<CatFraccionTtraDescProdResponseDTO>> listar(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) Long idTipoTramite,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) String sortDir,
            Pageable pageable) {
        return ResponseEntity.ok(service.listAll(search, idTipoTramite, sortBy, sortDir, pageable));
    }

    @PostMapping(CatalogPaths.SAVE_FRACCION_TTRA_DESC_PROD)
    public ResponseEntity<CatFraccionTtraDescProdResponseDTO> crear(@RequestBody CatFraccionTtraDescProdRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.crear(dto));
    }

    @GetMapping(CatalogPaths.FIND_FRACCION_TTRA_DESC_PROD)
    public ResponseEntity<CatFraccionTtraDescProdResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PutMapping(CatalogPaths.UPDATE_FRACCION_TTRA_DESC_PROD)
    public ResponseEntity<CatFraccionTtraDescProdResponseDTO> update(
            @PathVariable Long id,
            @RequestBody CatFraccionTtraDescProdRequestDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @GetMapping(CatalogPaths.TIPO_TRAMITE_FRAC_DESC_PROD)
    public ResponseEntity<List<ClasifProductoTraDTO>> listadoTipoTramite() {
        return ResponseEntity.ok(service.listadoTipoTramite());
    }

    @GetMapping(CatalogPaths.FRACCIONES_FRAC_DESC_PROD)
    public ResponseEntity<List<ClasifProductoTraDTO>> listadoFraccionTtra(@RequestParam Long idTipoTramite) {
        return ResponseEntity.ok(service.listadoFraccionTtra(idTipoTramite));
    }

    @GetMapping(CatalogPaths.DESC_PROD_FRAC_DESC_PROD)
    public ResponseEntity<List<SelectDTO>> listadoDescripcionProd() {
        return ResponseEntity.ok(service.listadoDescripcionProd());
    }

    @GetMapping(CatalogPaths.LAST_FRACCION_TTRA_DESC_PROD)
    public ResponseEntity<ClasifProductoTraDTO> lastFraccionTtraDescProd() {
        return ResponseEntity.ok(service.lastFraccionTtraDescProd());
    }
}
