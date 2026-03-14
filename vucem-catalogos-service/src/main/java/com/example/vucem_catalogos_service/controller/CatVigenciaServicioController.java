package com.example.vucem_catalogos_service.controller;

import com.example.vucem_catalogos_service.business.Interface.ICatVigenciaServicioService;
import com.example.vucem_catalogos_service.core.constants.CatalogPaths;
import com.example.vucem_catalogos_service.model.dto.CatVigenciaServicioDTO;
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
public class CatVigenciaServicioController {

    @Autowired
    private ICatVigenciaServicioService service;

    @GetMapping(CatalogPaths.LIST_VIGENCIA_SERVICIO)
    public ResponseEntity<PageResponseDTO<CatVigenciaServicioDTO>> list(
            @RequestParam(required = false) String search,
            Pageable pageable) {
        return ResponseEntity.ok(service.list(search, pageable));
    }

    @GetMapping(CatalogPaths.FIND_VIGENCIA_SERVICIO)
    public ResponseEntity<CatVigenciaServicioDTO> findById(@PathVariable Short id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping(CatalogPaths.SAVE_VIGENCIA_SERVICIO)
    public ResponseEntity<CatVigenciaServicioDTO> create(@RequestBody CatVigenciaServicioDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PutMapping(CatalogPaths.UPDATE_VIGENCIA_SERVICIO)
    public ResponseEntity<CatVigenciaServicioDTO> update(
            @PathVariable Short id,
            @RequestBody CatVigenciaServicioDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @GetMapping(CatalogPaths.SELECT_CRITERIO_ORIGEN)
    public ResponseEntity<List<SelectDTO>> listadoCriterioOrigen() {
        return ResponseEntity.ok(service.listadoCriterioOrigen());
    }
}
