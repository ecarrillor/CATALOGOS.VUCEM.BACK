package com.example.vucem_catalogos_service.controller;

import com.example.vucem_catalogos_service.business.Interface.ICatDocumentoTramiteService;
import com.example.vucem_catalogos_service.core.constants.CatalogPaths;
import com.example.vucem_catalogos_service.model.dto.CatDocumentoTramiteDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(CatalogPaths.CONTROLLER)
public class CatDocumentoTramiteController {

    @Autowired
    private ICatDocumentoTramiteService service;

    @GetMapping(CatalogPaths.LIST_DOCUMENTO_TRAMITE)
    public ResponseEntity<PageResponseDTO<CatDocumentoTramiteDTO>> list(
            @RequestParam(required = false) String search,
            Pageable pageable) {
        return ResponseEntity.ok(service.list(search, pageable));
    }

    @GetMapping(CatalogPaths.FIND_DOCUMENTO_TRAMITE)
    public ResponseEntity<CatDocumentoTramiteDTO> findById(
            @PathVariable Short idTipoDoc,
            @PathVariable Integer idTipoTramite) {
        return ResponseEntity.ok(service.findById(idTipoDoc, idTipoTramite));
    }

    @PostMapping(CatalogPaths.SAVE_DOCUMENTO_TRAMITE)
    public ResponseEntity<CatDocumentoTramiteDTO> create(@RequestBody CatDocumentoTramiteDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PutMapping(CatalogPaths.UPDATE_DOCUMENTO_TRAMITE)
    public ResponseEntity<CatDocumentoTramiteDTO> update(
            @PathVariable Short idTipoDoc,
            @PathVariable Integer idTipoTramite,
            @RequestBody CatDocumentoTramiteDTO dto) {
        return ResponseEntity.ok(service.update(idTipoDoc, idTipoTramite, dto));
    }
}
