package com.example.vucem_catalogos_service.controller;

import com.example.vucem_catalogos_service.business.Interface.ICatDescripcionProdService;
import com.example.vucem_catalogos_service.core.constants.CatalogPaths;
import com.example.vucem_catalogos_service.model.dto.ClasifProductoTraDTO;
import com.example.vucem_catalogos_service.model.dto.DescripcionProd.CatDescripcionProdRequestDTO;
import com.example.vucem_catalogos_service.model.dto.DescripcionProd.CatDescripcionProdResponseDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(CatalogPaths.CONTROLLER)
public class CatDescripcionProdController {

    @Autowired
    private ICatDescripcionProdService service;

    @GetMapping(CatalogPaths.LIST_CATALOGO_DESC_PROD)
    public ResponseEntity<PageResponseDTO<CatDescripcionProdResponseDTO>> listar(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) Long idTipoTramite,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) String sortDir,
            Pageable pageable) {
        return ResponseEntity.ok(service.listAll(search, idTipoTramite, sortBy, sortDir, pageable));
    }

    @PostMapping(CatalogPaths.SAVE_CATALOGO_DESC_PROD)
    public ResponseEntity<CatDescripcionProdResponseDTO> crear(@RequestBody CatDescripcionProdRequestDTO dto, @PathVariable Long idTipoTramite) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.crear(dto, idTipoTramite));
    }

    @GetMapping(CatalogPaths.FIND_CATALOGO_DESC_PROD)
    public ResponseEntity<CatDescripcionProdResponseDTO> findById(@PathVariable Integer id, @PathVariable Long idTipoTramite) {
        return ResponseEntity.ok(service.findById(id, idTipoTramite));
    }

    @PutMapping(CatalogPaths.UPDATE_CATALOGO_DESC_PROD)
    public ResponseEntity<CatDescripcionProdResponseDTO> update(
            @PathVariable Integer id,
            @RequestBody CatDescripcionProdRequestDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @GetMapping(CatalogPaths.TIPO_TRAMITE_DESC_PROD)
    public ResponseEntity<List<ClasifProductoTraDTO>> listadoTipoTramite() {
        return ResponseEntity.ok(service.listadoTipoTramite());
    }

    @GetMapping(CatalogPaths.LAST_DESCRIPCION_PROD)
    public ResponseEntity<ClasifProductoTraDTO> lastDescripcionProd() {
        return ResponseEntity.ok(service.lastDescripcionProd());
    }
}
