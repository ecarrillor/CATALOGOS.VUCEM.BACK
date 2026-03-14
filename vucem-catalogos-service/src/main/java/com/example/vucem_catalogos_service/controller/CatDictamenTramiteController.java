package com.example.vucem_catalogos_service.controller;

import com.example.vucem_catalogos_service.business.Interface.ICaTDictamenTramiteService;
import com.example.vucem_catalogos_service.core.constants.CatalogPaths;
import com.example.vucem_catalogos_service.model.dto.DictamenTramite.CatDictamenTramiteRequestDTO;
import com.example.vucem_catalogos_service.model.dto.DictamenTramite.CatDictamenTramiteResponseDTO;
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
public class CatDictamenTramiteController {

    @Autowired
    private ICaTDictamenTramiteService iCaTDictamenTramiteService;


    @GetMapping(CatalogPaths.LIST_CATALOGO_DICTAMEN_TRAMITE)
    public ResponseEntity<PageResponseDTO<CatDictamenTramiteResponseDTO>> listarDictamenTramite(
            @RequestParam(required = false) String search,
            Pageable pageable) {
        return ResponseEntity.ok(
                iCaTDictamenTramiteService.listarDictamenTramite(search, pageable));
    }

    @PostMapping(CatalogPaths.SAVE_CATALOGO_DICTAMEN_TRAMITE)
    public ResponseEntity<CatDictamenTramiteResponseDTO> crear(@RequestBody CatDictamenTramiteRequestDTO dto) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(iCaTDictamenTramiteService.crearDictamenTramite(dto));
    }

    @GetMapping(CatalogPaths.FIND_CATALOGO_DICTAMEN_TRAMITE)
    public ResponseEntity<CatDictamenTramiteResponseDTO> findById(
            @PathVariable Long tt, @PathVariable Long dc) {

        return ResponseEntity.ok(iCaTDictamenTramiteService.findById(tt, dc));
    }

    @PutMapping(CatalogPaths.UPDATE_CATALOGO_DICTAMEN_TRAMITE)
    public ResponseEntity<CatDictamenTramiteResponseDTO> update(
            @PathVariable Long tt, @PathVariable Long dc,
            @RequestBody CatDictamenTramiteRequestDTO dto) {

        return ResponseEntity.ok(iCaTDictamenTramiteService.update(tt, dc, dto));
    }

    @GetMapping(CatalogPaths.TIPO_DICTAMEN_CATALOGO_DICTAMEN_TRAMITE)
    public ResponseEntity<List<SelectDTO>> listadoTipoDictamen(){
        List<SelectDTO> lista = iCaTDictamenTramiteService.listadoTipoDictamen();
        return ResponseEntity.ok(lista);
    }
}
