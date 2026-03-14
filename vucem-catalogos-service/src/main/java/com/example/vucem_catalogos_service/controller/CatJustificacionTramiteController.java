package com.example.vucem_catalogos_service.controller;

import com.example.vucem_catalogos_service.business.Interface.ICatJustificacionTramiteService;
import com.example.vucem_catalogos_service.core.constants.CatalogPaths;
import com.example.vucem_catalogos_service.model.dto.JustificacionTramite.CatJustificacionTramiteRequestDTO;
import com.example.vucem_catalogos_service.model.dto.JustificacionTramite.CatJustificacionTramiteResponseDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(CatalogPaths.CONTROLLER)
public class CatJustificacionTramiteController {

    @Autowired
    private ICatJustificacionTramiteService iCatJustificacionTramiteService;


    @GetMapping(CatalogPaths.LIST_CATALOGO_JUSTIFICACION_TRAMITE)
    public ResponseEntity<PageResponseDTO<CatJustificacionTramiteResponseDTO>> listarJustificacionTramite(
            @RequestParam(required = false) String search,
            Pageable pageable) {
        return ResponseEntity.ok(
                iCatJustificacionTramiteService.listarJustificacionTramite(search, pageable));
    }

    @PostMapping(CatalogPaths.SAVE_CATALOGO_JUSTIFICACION_TRAMITE)
    public ResponseEntity<CatJustificacionTramiteResponseDTO> crear(@RequestBody CatJustificacionTramiteRequestDTO dto) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(iCatJustificacionTramiteService.crearJustificacionTramite(dto));
    }

    @GetMapping(CatalogPaths.FIND_CATALOGO_JUSTIFICACION_TRAMITE)
    public ResponseEntity<CatJustificacionTramiteResponseDTO> findById(
            @PathVariable Long id) {

        return ResponseEntity.ok(iCatJustificacionTramiteService.findById(id));
    }

    @PutMapping(CatalogPaths.UPDATE_CATALOGO_JUSTIFICACION_TRAMITE)
    public ResponseEntity<CatJustificacionTramiteResponseDTO> update(
            @PathVariable Long id,
            @RequestBody CatJustificacionTramiteRequestDTO dto) {

        return ResponseEntity.ok(iCatJustificacionTramiteService.update(id, dto));
    }
}
