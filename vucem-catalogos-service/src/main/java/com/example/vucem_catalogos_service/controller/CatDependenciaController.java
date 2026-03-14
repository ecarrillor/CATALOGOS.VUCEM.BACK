package com.example.vucem_catalogos_service.controller;

import com.example.vucem_catalogos_service.business.Interface.ICatDependenciaService;
import com.example.vucem_catalogos_service.core.constants.CatalogPaths;
import com.example.vucem_catalogos_service.model.dto.Dependencia.CatDependenciaRequestDTO;
import com.example.vucem_catalogos_service.model.dto.Dependencia.CatDependenciaResponseDTO;
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
public class CatDependenciaController {

    @Autowired
    private ICatDependenciaService iCatDependenciaService;


    @GetMapping(CatalogPaths.LIST_CATALOGO_DEPENDENCIA)
    public ResponseEntity<PageResponseDTO<CatDependenciaResponseDTO>> listarDependencia(
            @RequestParam(required = false) String search,
            Pageable pageable) {
        return ResponseEntity.ok(
                iCatDependenciaService.listarDependencia(search, pageable));
    }

    @PostMapping(CatalogPaths.SAVE_CATALOGO_DEPENDENCIA)
    public ResponseEntity<CatDependenciaResponseDTO> crear(@RequestBody CatDependenciaRequestDTO dto) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(iCatDependenciaService.crearDependencia(dto));
    }

    @GetMapping(CatalogPaths.FIND_CATALOGO_DEPENDENCIA)
    public ResponseEntity<CatDependenciaResponseDTO> findById(
            @PathVariable Long id) {

        return ResponseEntity.ok(iCatDependenciaService.findById(id));
    }

    @PutMapping(CatalogPaths.UPDATE_CATALOGO_DEPENDENCIA)
    public ResponseEntity<CatDependenciaResponseDTO> update(
            @PathVariable Long id,
            @RequestBody CatDependenciaRequestDTO dto) {

        return ResponseEntity.ok(iCatDependenciaService.update(id, dto));
    }

    @GetMapping(CatalogPaths.CALENDARIO_CATALOGO_DEPENDENCIA)
    public ResponseEntity<List<SelectDTO>> listadoCalendarios(){
        List<SelectDTO> lista = iCatDependenciaService.listadoCalendarios();
        return ResponseEntity.ok(lista);
    }
}
