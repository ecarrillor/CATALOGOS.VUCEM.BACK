package com.example.vucem_catalogos_service.controller;

import com.example.vucem_catalogos_service.business.Interface.ICatEntidadService;
import com.example.vucem_catalogos_service.core.constants.CatalogPaths;
import com.example.vucem_catalogos_service.model.entity.CatEntidad;
import com.example.vucem_catalogos_service.model.entity.CatPais;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(CatalogPaths.CONTROLLER)
public class CatEntidadController {

    @Autowired
    private ICatEntidadService service;



    @GetMapping(CatalogPaths.LIST_CATALOGO_ENTIDAD)
    public Page<CatEntidad> listarCatEntidad(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(required = false) String search) {


        return service.catEntidadListAll(page, size, search);
    }

    @PostMapping(CatalogPaths.SAVE_CATALOGO_ENTIDAD)
    public ResponseEntity<CatEntidad> crear(@RequestBody CatEntidad entidad) {
        CatEntidad nueva = service.crearEntidad(entidad);
        return ResponseEntity.status(HttpStatus.CREATED).body(nueva);
    }

    @GetMapping(CatalogPaths.FIND_CATALOGO_ENTIDAD)
    public ResponseEntity<CatEntidad> findByCveEntidad(@PathVariable String cveEntidad) {
        CatEntidad entidad = service.findByCveEntidad(cveEntidad);

        return ResponseEntity.ok(entidad);
    }

    @PutMapping(CatalogPaths.UPDATE_CATALOGO_ENTIDAD)
    public ResponseEntity<CatEntidad> updateActivo(
            @PathVariable String cveEntidad,
            @RequestBody CatEntidad request) {

        CatEntidad updated = service.updateEntidad(cveEntidad, request);
        return ResponseEntity.ok(updated);
    }

    @GetMapping(CatalogPaths.PAISES_CATALOGO_ENTIDAD)
    public ResponseEntity<List<CatPais>> getAllPaises() {
        List<CatPais> paises = service.findAllPaises();
        return ResponseEntity.ok(paises);
}
}
