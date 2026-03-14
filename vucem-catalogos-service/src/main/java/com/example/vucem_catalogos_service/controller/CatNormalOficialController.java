package com.example.vucem_catalogos_service.controller;

import com.example.vucem_catalogos_service.business.Interface.ICatNormalOficialService;
import com.example.vucem_catalogos_service.core.constants.CatalogPaths;
import com.example.vucem_catalogos_service.model.dto.CatNormalOficialDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(CatalogPaths.CONTROLLER)
public class CatNormalOficialController {

    @Autowired
    private ICatNormalOficialService service;

    @GetMapping(CatalogPaths.LIST_NORMAL_OFICIAL)
    public ResponseEntity<PageResponseDTO<CatNormalOficialDTO>> list(
            @RequestParam(required = false) String search,
            Pageable pageable) {
        return ResponseEntity.ok(service.list(search, pageable));
    }

    @GetMapping(CatalogPaths.FIND_NORMAL_OFICIAL)
    public ResponseEntity<CatNormalOficialDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping(CatalogPaths.SAVE_NORMAL_OFICIAL)
    public ResponseEntity<CatNormalOficialDTO> create(@RequestBody CatNormalOficialDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PutMapping(CatalogPaths.UPDATE_NORMAL_OFICIAL)
    public ResponseEntity<CatNormalOficialDTO> update(@PathVariable Integer id,
                                                      @RequestBody CatNormalOficialDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @GetMapping(CatalogPaths.LIST_NORMAL_OFICIAL_ACTIVOS)
    public ResponseEntity<List<CatNormalOficialDTO>> listActivos() {
        return ResponseEntity.ok(service.listActivos());
    }
}
