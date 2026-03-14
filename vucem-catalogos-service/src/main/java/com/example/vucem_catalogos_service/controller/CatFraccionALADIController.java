package com.example.vucem_catalogos_service.controller;

import com.example.vucem_catalogos_service.business.Interface.ICatFraccionALADIService;
import com.example.vucem_catalogos_service.core.constants.CatalogPaths;
import com.example.vucem_catalogos_service.model.dto.FraccionALADI.CatFraccionALADIRequestDTO;
import com.example.vucem_catalogos_service.model.dto.FraccionALADI.CatFraccionALADIResponseDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(CatalogPaths.CONTROLLER)
public class CatFraccionALADIController {


    @Autowired
    private ICatFraccionALADIService iCatFraccionALADIService;


    @GetMapping(CatalogPaths.LIST_CATALOGO_FRACCION_ALADI)
    public ResponseEntity<PageResponseDTO<CatFraccionALADIResponseDTO>> listarFraccionAladi(
            @RequestParam(required = false) String search,
            Pageable pageable) {
        return ResponseEntity.ok(
                iCatFraccionALADIService.listarFraccionAladi(search, pageable));
    }

    @PostMapping(CatalogPaths.SAVE_CATALOGO_FRACCION_ALADI)
    public ResponseEntity<CatFraccionALADIResponseDTO> crear(@RequestBody CatFraccionALADIRequestDTO dto) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(iCatFraccionALADIService.crearFraacionAladi(dto));
    }

    @GetMapping(CatalogPaths.FIND_CATALOGO_FRACCION_ALADI)
    public ResponseEntity<CatFraccionALADIResponseDTO> findById(
            @PathVariable Long id) {

        return ResponseEntity.ok(iCatFraccionALADIService.findById(id));
    }

    @PutMapping(CatalogPaths.UPDATE_CATALOGO_FRACCION_ALADI)
    public ResponseEntity<CatFraccionALADIResponseDTO> update(
            @PathVariable Long id,
            @RequestBody CatFraccionALADIRequestDTO dto) {

        return ResponseEntity.ok(iCatFraccionALADIService.update(id, dto));
    }

}
