package com.example.vucem_catalogos_service.controller;


import com.example.vucem_catalogos_service.business.Interface.ICatAduanaClasifProductoService;
import com.example.vucem_catalogos_service.core.constants.CatalogPaths;
import com.example.vucem_catalogos_service.model.dto.AduanaClasifProducto.CatAduanaClasifProdRequestDTO;
import com.example.vucem_catalogos_service.model.dto.AduanaClasifProducto.CatAduanaClasifProdResponseDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.dto.SelectDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(CatalogPaths.CONTROLLER)
public class CatAduanaClasifProductoController {

    @Autowired
    private ICatAduanaClasifProductoService service;

    @GetMapping(CatalogPaths.LIST_CATALOGO_ADUANA_CLASIF_PRODUCTO)
    public ResponseEntity<PageResponseDTO<CatAduanaClasifProdResponseDTO>> listarCatAduanaClasif(
            @RequestParam(required = false) String search,
            Pageable pageable) {
        return ResponseEntity.ok(
                service.catAduanaListAll(search, pageable));
    }

    @PostMapping(CatalogPaths.SAVE_CATALOGO_ADUANA_CLASIF_PRODUCTO)
    public ResponseEntity<CatAduanaClasifProdResponseDTO> crear(@RequestBody CatAduanaClasifProdRequestDTO dto) {

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(service.crearAduanaClasifProducto(dto));
    }

    @GetMapping(CatalogPaths.FIND_CATALOGO_ADUANA_CLASIF_PRODUCTO)
    public ResponseEntity<CatAduanaClasifProdResponseDTO> findById(
            @PathVariable Long id) {

        return ResponseEntity.ok(service.findById(id));
    }

    @PutMapping(CatalogPaths.UPDATE_CATALOGO_ADUANA_CLASIF_PRODUCTO)
    public ResponseEntity<CatAduanaClasifProdResponseDTO> update(
            @PathVariable Long id,
            @RequestBody CatAduanaClasifProdRequestDTO dto) {

        return ResponseEntity.ok(service.update(id, dto));
    }

    @GetMapping(CatalogPaths.LIST_ADUANA)
    public ResponseEntity<List<SelectDTO>> listadoAduana(){
        List<SelectDTO> lista = service.listadoAduana();
        return ResponseEntity.ok(lista);
    }

    @GetMapping(CatalogPaths.LIST_CLASIFICACION_PRODUCTO)
    public ResponseEntity<List<SelectDTO>> listadoClasificacionProducto(){
        List<SelectDTO> lista = service.listadoClasificacionProducto();
        return ResponseEntity.ok(lista);
    }
}
