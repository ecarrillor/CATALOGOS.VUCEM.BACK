package com.example.vucem_catalogos_service.controller;

import com.example.vucem_catalogos_service.business.Interface.ICatUnidadAdministrativaService;
import com.example.vucem_catalogos_service.core.constants.CatalogPaths;
import com.example.vucem_catalogos_service.model.dto.CatUnidadAdministrativaNameDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(CatalogPaths.CONTROLLER)
public class CatUnidadAdministrativaController {
    @Autowired
    private ICatUnidadAdministrativaService service;



    @GetMapping(CatalogPaths.LIST_UNIDAD_ADMIN_LIST_LAB)
    public ResponseEntity<List<CatUnidadAdministrativaNameDTO>> findByAll() {
        return ResponseEntity.ok(service.findByAll());
    }
}
