package com.example.vucem_catalogos_service.controller;


import com.example.vucem_catalogos_service.business.Interface.ICatDependenciaService;
import com.example.vucem_catalogos_service.business.Interface.ICatParametroService;
import com.example.vucem_catalogos_service.core.constants.CatalogPaths;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.dto.Parametro.CatParametroRequestDTO;
import com.example.vucem_catalogos_service.model.dto.Parametro.CatParametroResponseDTO;
import com.example.vucem_catalogos_service.model.dto.SelectDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(CatalogPaths.CONTROLLER)
public class CatParametroController {

    @Autowired
    private ICatParametroService iCatParametroService;

    @Autowired
    private ICatDependenciaService iCatDependenciaService;

    @GetMapping(CatalogPaths.LIST_CATALOGO_PARAMETRO)
    public ResponseEntity<PageResponseDTO<CatParametroResponseDTO>> listarParametro(
            @RequestParam(required = false) String search,
            Pageable pageable) {
        return ResponseEntity.ok(
                iCatParametroService.listarParametro(search, pageable));
    }

    @PostMapping(CatalogPaths.SAVE_CATALOGO_PARAMETRO)
    public ResponseEntity<CatParametroResponseDTO> crear(@RequestBody CatParametroRequestDTO dto) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(iCatParametroService.crearParametro(dto));
    }

    @GetMapping(CatalogPaths.FIND_CATALOGO_PARAMETRO)
    public ResponseEntity<CatParametroResponseDTO> findById(
            @PathVariable String id) {

        return ResponseEntity.ok(iCatParametroService.findById(id));
    }

    @PutMapping(CatalogPaths.UPDATE_CATALOGO_PARAMETRO)
    public ResponseEntity<CatParametroResponseDTO> update(
            @PathVariable String id,
            @RequestBody CatParametroRequestDTO dto) {

        return ResponseEntity.ok(iCatParametroService.update(id, dto));
    }

    @GetMapping(CatalogPaths.LIST_DEPENDENCIAS)
    public ResponseEntity<List<SelectDTO>> listadoDependencias(){
        List<SelectDTO> lista = iCatDependenciaService.listadoDependencias();
        return ResponseEntity.ok(lista);
    }
}
