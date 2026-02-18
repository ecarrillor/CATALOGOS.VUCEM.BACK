package com.example.vucem_catalogos_service.controller;


import com.example.vucem_catalogos_service.business.Interface.ICatAduanaClasifProductoService;
import com.example.vucem_catalogos_service.core.constants.CatalogPaths;
import com.example.vucem_catalogos_service.model.entity.CatAduanaClasifProd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(CatalogPaths.CONTROLLER)
public class CatAduanaClasifProducto {

    @Autowired
    private ICatAduanaClasifProductoService service;

    @GetMapping(CatalogPaths.LIST_CATALOGO_ADUANA_CLASIF_PRODUCTO)
    public Page<CatAduanaClasifProd> listarCatAduana(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(required = false) String search) {

        return service.catAduanaListAll(page, size, search);
    }

    @PostMapping(CatalogPaths.SAVE_CATALOGO_ADUANA_CLASIF_PRODUCTO)
    public ResponseEntity<CatAduanaClasifProd> crear(@RequestBody CatAduanaClasifProd aduanaClasifProd) {

            CatAduanaClasifProd nueva = service.crearAduanaClasifProducto(aduanaClasifProd);
            return ResponseEntity.status(HttpStatus.CREATED).body(nueva);


    }
}
