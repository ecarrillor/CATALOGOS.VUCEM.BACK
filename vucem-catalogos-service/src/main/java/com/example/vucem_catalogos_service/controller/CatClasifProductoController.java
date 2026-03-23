package com.example.vucem_catalogos_service.controller;

import com.example.vucem_catalogos_service.business.Interface.ICatClasifProductoService;
import com.example.vucem_catalogos_service.core.constants.CatalogPaths;
import com.example.vucem_catalogos_service.model.dto.CatClasifProductoDTO;
import com.example.vucem_catalogos_service.model.dto.ClasifProductoTraDTO;
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
public class CatClasifProductoController {

    @Autowired
    private ICatClasifProductoService service;

    @GetMapping(CatalogPaths.LIST_CLASIF_PRODUCTO)
    public ResponseEntity<PageResponseDTO<CatClasifProductoDTO>> list(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) Long idTipoTramite,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) String sortDir,
            Pageable pageable) {
        return ResponseEntity.ok(service.list(search, idTipoTramite, sortBy, sortDir, pageable));
    }

    @GetMapping(CatalogPaths.FIND_CLASIF_PRODUCTO)
    public ResponseEntity<CatClasifProductoDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping(CatalogPaths.SAVE_CLASIF_PRODUCTO)
    public ResponseEntity<CatClasifProductoDTO> create(@RequestBody CatClasifProductoDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PutMapping(CatalogPaths.UPDATE_CLASIF_PRODUCTO)
    public ResponseEntity<CatClasifProductoDTO> update(
            @PathVariable Long id,
            @RequestBody CatClasifProductoDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @GetMapping(CatalogPaths.LIST_TIPO_TRAMITE_CLASIF_PRODUCTO)
    public ResponseEntity<List<ClasifProductoTraDTO>> listadoTipoTramite() {
        return ResponseEntity.ok(service.listadoTipoTramite());
    }

    @GetMapping(CatalogPaths.LIST_TIPO_CLASIF_PRODUCTO_R)
    public ResponseEntity<List<ClasifProductoTraDTO>> listadoClasifPrR(
            @RequestParam Long idTipoTramite) {

        return ResponseEntity.ok(service.listadoClasifPrR(idTipoTramite));
    }

    @GetMapping(CatalogPaths.LAST_CLASIF_PRODUCTO)
    public ResponseEntity<ClasifProductoTraDTO> lastClasifProducto() {
        return ResponseEntity.ok(service.lastClasifProducto());
    }

}
