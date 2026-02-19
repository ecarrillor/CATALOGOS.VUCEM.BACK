package com.example.vucem_catalogos_service.controller;


import com.example.vucem_catalogos_service.business.Interface.ICatAduanaService;
import com.example.vucem_catalogos_service.core.constants.CatalogPaths;
import com.example.vucem_catalogos_service.model.entity.CatAduana;
import com.example.vucem_catalogos_service.model.entity.CatEntidad;
import com.example.vucem_catalogos_service.model.entity.CatTipoAduana;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(CatalogPaths.CONTROLLER)
public class CatAduanaController {

    @Autowired
    private ICatAduanaService service;

    @GetMapping(CatalogPaths.LIST_CATALOGO_ADUANA)
    public Page<CatAduana> listarCatAduana(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(required = false) String search) {

        return service.catAduanaListAll(page, size, search);
    }

    @PostMapping(CatalogPaths.SAVE_CATALOGO_ADUANA)
    public ResponseEntity<CatAduana> crear(@RequestBody CatAduana aduana) {
        try {
            CatAduana nueva = service.crearAduana(aduana);
            return ResponseEntity.status(HttpStatus.CREATED).body(nueva);

        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST).body(aduana);
        }
    }

    @GetMapping(CatalogPaths.FIND_CATALOGO_ADUANA)
    public ResponseEntity<CatAduana> findByCveAduana(@PathVariable String cveAduana) {
        CatAduana entidad = service.findByCveAduana(cveAduana);

        return ResponseEntity.ok(entidad);
    }

    @PutMapping(CatalogPaths.UPDATE_CATALOGO_ADUANA)
    public ResponseEntity<CatAduana> updateAduana(
            @PathVariable String cveAduana,
            @RequestBody CatAduana request) {

        CatAduana updated = service.updateAduana(cveAduana, request);
        return ResponseEntity.ok(updated);
    }

    @GetMapping(CatalogPaths.LIST_TIPOS_ADUANA_CLASIF_PRODUCTO)
    public ResponseEntity<List<CatTipoAduana>> getAllTiposAduana() {
        List<CatTipoAduana> tipoAduanas = service.getAllTiposAduana();
        return ResponseEntity.ok(tipoAduanas);
    }

    @GetMapping(CatalogPaths.LIST_ENTIDADES_CLASIF_PRODUCTO)
    public ResponseEntity<List<CatEntidad>> getAllEntidades() {
        List<CatEntidad> entidades = service.getAllEntidades();
        return ResponseEntity.ok(entidades);
    }
}
