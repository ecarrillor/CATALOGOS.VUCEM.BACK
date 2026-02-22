package com.example.vucem_catalogos_service.controller;

import com.example.vucem_catalogos_service.business.Interface.ICatObservacionTramiteService;
import com.example.vucem_catalogos_service.core.constants.CatalogPaths;
import com.example.vucem_catalogos_service.model.dto.ObservacionTramite.CatObservacionTramiteRequestDTO;
import com.example.vucem_catalogos_service.model.dto.ObservacionTramite.CatObservacionTramiteResponseDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(CatalogPaths.CONTROLLER)
public class CatObservacionTramiteController {


    @Autowired
    private ICatObservacionTramiteService iCatObservacionTramiteService;

    @GetMapping(CatalogPaths.LIST_CATALOGO_OBSERVACION_TRAMITE)
    public ResponseEntity<PageResponseDTO<CatObservacionTramiteResponseDTO>> listarObservacionTramite(
            @RequestParam(required = false) String search,
            Pageable pageable) {
        return ResponseEntity.ok(
                iCatObservacionTramiteService.listarObservacionTramite(search, pageable));
    }

    @PostMapping(CatalogPaths.SAVE_CATALOGO_OBSERVACION_TRAMITE)
    public ResponseEntity<CatObservacionTramiteResponseDTO> crear(@RequestBody CatObservacionTramiteRequestDTO dto) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(iCatObservacionTramiteService.crearObservacionTramite(dto));
    }

    @GetMapping(CatalogPaths.FIND_CATALOGO_OBSERVACION_TRAMITE)
    public ResponseEntity<CatObservacionTramiteResponseDTO> findById(
            @PathVariable Long id) {

        return ResponseEntity.ok(iCatObservacionTramiteService.findById(id));
    }

    @PutMapping(CatalogPaths.UPDATE_CATALOGO_OBSERVACION_TRAMITE)
    public ResponseEntity<CatObservacionTramiteResponseDTO> update(
            @PathVariable Long id,
            @RequestBody CatObservacionTramiteRequestDTO dto) {

        return ResponseEntity.ok(iCatObservacionTramiteService.update(id, dto));
    }
}
