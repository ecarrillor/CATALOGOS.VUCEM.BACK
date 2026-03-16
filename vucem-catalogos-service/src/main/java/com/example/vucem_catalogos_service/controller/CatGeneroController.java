package com.example.vucem_catalogos_service.controller;

import com.example.vucem_catalogos_service.business.Interface.ICatGeneroService;
import com.example.vucem_catalogos_service.core.constants.CatalogPaths;
import com.example.vucem_catalogos_service.model.dto.ClasifProductoTraDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(CatalogPaths.CONTROLLER)
public class CatGeneroController {

    @Autowired
    private ICatGeneroService service;

    @GetMapping(CatalogPaths.LAST_GENERO)
    public ResponseEntity<ClasifProductoTraDTO> lastGenero() {
        return ResponseEntity.ok(service.lastGenero());
    }
}
