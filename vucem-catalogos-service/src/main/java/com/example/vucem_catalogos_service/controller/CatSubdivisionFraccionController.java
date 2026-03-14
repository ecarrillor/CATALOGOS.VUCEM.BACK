package com.example.vucem_catalogos_service.controller;

import com.example.vucem_catalogos_service.business.Interface.ICatSubdivisionFraccionService;
import com.example.vucem_catalogos_service.core.constants.CatalogPaths;
import com.example.vucem_catalogos_service.model.dto.CatSubdivisionFraccionDTO;
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
public class CatSubdivisionFraccionController {

    @Autowired
    private ICatSubdivisionFraccionService service;

    @GetMapping(CatalogPaths.LIST_SUBDIVISION_FRACCION)
    public ResponseEntity<PageResponseDTO<CatSubdivisionFraccionDTO>> list(
            @RequestParam(required = false) String search,
            Pageable pageable) {
        return ResponseEntity.ok(service.list(search, pageable));
    }

    @GetMapping(CatalogPaths.FIND_SUBDIVISION_FRACCION)
    public ResponseEntity<CatSubdivisionFraccionDTO> findById(@PathVariable String cveSubdivision) {
        return ResponseEntity.ok(service.findById(cveSubdivision));
    }

    @PostMapping(CatalogPaths.SAVE_SUBDIVISION_FRACCION)
    public ResponseEntity<CatSubdivisionFraccionDTO> create(@RequestBody CatSubdivisionFraccionDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PutMapping(CatalogPaths.UPDATE_SUBDIVISION_FRACCION)
    public ResponseEntity<CatSubdivisionFraccionDTO> update(
            @PathVariable String cveSubdivision,
            @RequestBody CatSubdivisionFraccionDTO dto) {
        return ResponseEntity.ok(service.update(cveSubdivision, dto));
    }

    @GetMapping(CatalogPaths.LIST_FRACCION_ARANCELARIA_BY_ID)
    public ResponseEntity<List<FraccionAranceSearchDTO>> listadoArancelariaById(@RequestParam String term){
        List<FraccionAranceSearchDTO> lista = service.listadoArancelariaById(term);
        return ResponseEntity.ok(lista);
    }
}
