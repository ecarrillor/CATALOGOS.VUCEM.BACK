package com.example.vucem_catalogos_service.controller;

import com.example.vucem_catalogos_service.business.Interface.ICatCasService;
import com.example.vucem_catalogos_service.core.constants.CatalogPaths;
import com.example.vucem_catalogos_service.model.dto.CatCas.CatCaRequestDTO;
import com.example.vucem_catalogos_service.model.dto.CatCas.CatCaResponseDTO;
import com.example.vucem_catalogos_service.model.dto.ClasifProductoTraDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(CatalogPaths.CONTROLLER)
public class CatCasController {

    @Autowired
    private ICatCasService service;

    @GetMapping(CatalogPaths.LIST_CAT_CAS)
    public ResponseEntity<PageResponseDTO<CatCaResponseDTO>> listar(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) Long idTipoTramite,
            Pageable pageable) {
        return ResponseEntity.ok(service.listAll(search, idTipoTramite, pageable));
    }

    @PostMapping(CatalogPaths.SAVE_CAT_CAS)
    public ResponseEntity<CatCaResponseDTO> crear(@RequestBody CatCaRequestDTO dto, @PathVariable Long idTipoTramite) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.crear(dto, idTipoTramite));
    }

    @GetMapping(CatalogPaths.FIND_CAT_CAS)
    public ResponseEntity<CatCaResponseDTO> findById(@PathVariable Short id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PutMapping(CatalogPaths.UPDATE_CAT_CAS)
    public ResponseEntity<CatCaResponseDTO> update(
            @PathVariable Short id,
            @RequestBody CatCaRequestDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @GetMapping(CatalogPaths.LIST_TIPO_TRAMITE_CAT_CAS)
    public ResponseEntity<List<ClasifProductoTraDTO>> listadoTipoTramite() {
        return ResponseEntity.ok(service.listadoTipoTramite());
    }

    @GetMapping(CatalogPaths.LAST_CAT_CAS)
    public ResponseEntity<ClasifProductoTraDTO> lastCas() {
        return ResponseEntity.ok(service.lastCas());
    }
}
