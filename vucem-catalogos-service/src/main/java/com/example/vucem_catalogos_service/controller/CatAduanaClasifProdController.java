package com.example.vucem_catalogos_service.controller;

import com.example.vucem_catalogos_service.business.Interface.ICatAduanaClasifProdService;
import com.example.vucem_catalogos_service.core.constants.CatalogPaths;
import com.example.vucem_catalogos_service.model.dto.CatAduanaClasifProdDTO;
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
public class CatAduanaClasifProdController {

    @Autowired
    private ICatAduanaClasifProdService service;

    @GetMapping(CatalogPaths.LIST_CATALOGO_ADUANA_CLASIF_PRODUCTO)
    public ResponseEntity<PageResponseDTO<CatAduanaClasifProdDTO>> list(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) Long idClasifProducto,
            Pageable pageable) {
        return ResponseEntity.ok(service.list(search, idClasifProducto, pageable));
    }

    @GetMapping(CatalogPaths.FIND_CATALOGO_ADUANA_CLASIF_PRODUCTO)
    public ResponseEntity<CatAduanaClasifProdDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping(CatalogPaths.SAVE_CATALOGO_ADUANA_CLASIF_PRODUCTO)
    public ResponseEntity<CatAduanaClasifProdDTO> create(@RequestBody CatAduanaClasifProdDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PutMapping(CatalogPaths.UPDATE_CATALOGO_ADUANA_CLASIF_PRODUCTO)
    public ResponseEntity<CatAduanaClasifProdDTO> update(
            @PathVariable Long id,
            @RequestBody CatAduanaClasifProdDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @GetMapping(CatalogPaths.LIST_TIPO_TRAMITE_ADUANA_CLASIF)
    public ResponseEntity<List<ClasifProductoTraDTO>> listadoTipoTramite() {
        return ResponseEntity.ok(service.listadoTipoTramite());
    }

    @GetMapping(CatalogPaths.LIST_ADUANA)
    public ResponseEntity<List<SelectDTO>> listadoAduana() {
        return ResponseEntity.ok(service.listadoAduana());
    }

    @GetMapping(CatalogPaths.LIST_CLASIFICACION_PRODUCTO)
    public ResponseEntity<List<ClasifProductoTraDTO>> listadoClasifProducto(
            @RequestParam Long idTipoTramite) {
        return ResponseEntity.ok(service.listadoClasifProducto(idTipoTramite));
    }
}
