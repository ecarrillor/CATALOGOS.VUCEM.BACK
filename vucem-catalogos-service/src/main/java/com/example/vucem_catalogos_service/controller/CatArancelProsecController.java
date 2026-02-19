package com.example.vucem_catalogos_service.controller;

import com.example.vucem_catalogos_service.business.Interface.ICatArancelProsecService;
import com.example.vucem_catalogos_service.core.constants.CatalogPaths;
import com.example.vucem_catalogos_service.model.dto.CatAprobCertSeRequestDTO;
import com.example.vucem_catalogos_service.model.dto.CatAprobCertSeResponseDTO;
import com.example.vucem_catalogos_service.model.dto.CatArancelProsecDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(CatalogPaths.CONTROLLER)
public class CatArancelProsecController {
    @Autowired
    private ICatArancelProsecService service;


    @GetMapping(CatalogPaths.LIST_ARANCEL_PROSEC)
    public ResponseEntity<PageResponseDTO<CatArancelProsecDTO>> list(
            @RequestParam(required = false) String search,
            Pageable pageable) {

        return ResponseEntity.ok(
                service.list(search, pageable));
    }


    @GetMapping(CatalogPaths.FIND_ARANCEL_PROSEC)
    public ResponseEntity<CatArancelProsecDTO> findById(
            @PathVariable String id,
            @PathVariable String cveSectorProsec) {

        return ResponseEntity.ok(service.findById(id, cveSectorProsec));
    }


    @PostMapping(CatalogPaths.SAVE_ARANCEL_PROSEC)
    public ResponseEntity<CatArancelProsecDTO> create(
            @RequestBody CatArancelProsecDTO dto) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.create(dto));
    }


    @PutMapping(CatalogPaths.UPDATE_ARANCEL_PROSEC)
    public ResponseEntity<CatArancelProsecDTO> update(
            @PathVariable String cveFraccion,
            @PathVariable String cveSectorProsec,
            @RequestBody CatArancelProsecDTO dto) {

        return ResponseEntity.ok(service.update(cveFraccion, cveSectorProsec, dto));
    }


}
