package com.example.vucem_catalogos_service.controller;


import com.example.vucem_catalogos_service.business.Interface.ICatMotivoTramiteService;
import com.example.vucem_catalogos_service.core.constants.CatalogPaths;
import com.example.vucem_catalogos_service.model.dto.MotivoTramite.CatMotivoTramiteRequestDTO;
import com.example.vucem_catalogos_service.model.dto.MotivoTramite.CatMotivoTramiteResponseDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(CatalogPaths.CONTROLLER)
public class CatMotivoTramiteController {

    @Autowired
    private ICatMotivoTramiteService iCatMotivoTramiteService;

    @GetMapping(CatalogPaths.LIST_CATALOGO_MOTIVO_TRAMITE)
    public ResponseEntity<PageResponseDTO<CatMotivoTramiteResponseDTO>> listarMotivoTramite(
            @RequestParam(required = false) String search,
            Pageable pageable) {
        return ResponseEntity.ok(
                iCatMotivoTramiteService.listarMotivoTramite(search, pageable));
    }

    @PostMapping(CatalogPaths.SAVE_CATALOGO_MOTIVO_TRAMITE)
    public ResponseEntity<CatMotivoTramiteResponseDTO> crear(@RequestBody CatMotivoTramiteRequestDTO dto) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(iCatMotivoTramiteService.crearCategoriaTextil(dto));
    }

    @GetMapping(CatalogPaths.FIND_CATALOGO_MOTIVO_TRAMITE)
    public ResponseEntity<CatMotivoTramiteResponseDTO> findById(
            @PathVariable Long id) {

        return ResponseEntity.ok(iCatMotivoTramiteService.findById(id));
    }

    @PutMapping(CatalogPaths.UPDATE_CATALOGO_MOTIVO_TRAMITE)
    public ResponseEntity<CatMotivoTramiteResponseDTO> update(
            @PathVariable Long id,
            @RequestBody CatMotivoTramiteRequestDTO dto) {

        return ResponseEntity.ok(iCatMotivoTramiteService.update(id, dto));
    }
}
