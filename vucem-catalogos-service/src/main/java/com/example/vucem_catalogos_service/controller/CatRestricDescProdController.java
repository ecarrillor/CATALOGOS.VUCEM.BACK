package com.example.vucem_catalogos_service.controller;

import com.example.vucem_catalogos_service.business.Interface.ICatRestricDescProdService;
import com.example.vucem_catalogos_service.core.constants.CatalogPaths;
import com.example.vucem_catalogos_service.model.dto.ClasifProductoTraDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.dto.RestricDescProd.CatRestricDescProdRequestDTO;
import com.example.vucem_catalogos_service.model.dto.RestricDescProd.CatRestricDescProdResponseDTO;
import com.example.vucem_catalogos_service.model.dto.SelectDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(CatalogPaths.CONTROLLER)
public class CatRestricDescProdController {

    @Autowired
    private ICatRestricDescProdService service;

    @GetMapping(CatalogPaths.LIST_RESTRIC_DESC_PROD)
    public ResponseEntity<PageResponseDTO<CatRestricDescProdResponseDTO>> listar(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) Long idTipoTramite,
            Pageable pageable) {
        return ResponseEntity.ok(service.listAll(search, idTipoTramite, pageable));
    }

    @PostMapping(CatalogPaths.SAVE_RESTRIC_DESC_PROD)
    public ResponseEntity<CatRestricDescProdResponseDTO> crear(@RequestBody CatRestricDescProdRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.crear(dto));
    }

    @GetMapping(CatalogPaths.FIND_RESTRIC_DESC_PROD)
    public ResponseEntity<CatRestricDescProdResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PutMapping(CatalogPaths.UPDATE_RESTRIC_DESC_PROD)
    public ResponseEntity<CatRestricDescProdResponseDTO> update(
            @PathVariable Long id,
            @RequestBody CatRestricDescProdRequestDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @GetMapping(CatalogPaths.LIST_TIPO_TRAMITE_RESTRIC_DESC)
    public ResponseEntity<List<ClasifProductoTraDTO>> listadoTipoTramite() {
        return ResponseEntity.ok(service.listadoTipoTramite());
    }

    @GetMapping(CatalogPaths.LIST_RESTRICCION_TTRA)
    public ResponseEntity<List<ClasifProductoTraDTO>> listadoRestriccionTtra(@RequestParam Long idTipoTramite) {
        return ResponseEntity.ok(service.listadoRestriccionTtra(idTipoTramite));
    }

    @GetMapping(CatalogPaths.LIST_DESCRIPCION_PROD)
    public ResponseEntity<List<SelectDTO>> listadoDescripcionProd() {
        return ResponseEntity.ok(service.listadoDescripcionProd());
    }

    @GetMapping(CatalogPaths.LAST_RESTRIC_DESC_PROD)
    public ResponseEntity<ClasifProductoTraDTO> lastRestricDescProd() {
        return ResponseEntity.ok(service.lastRestricDescProd());
    }
}
