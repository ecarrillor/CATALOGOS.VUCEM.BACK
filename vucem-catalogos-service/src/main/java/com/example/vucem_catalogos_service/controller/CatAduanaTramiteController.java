package com.example.vucem_catalogos_service.controller;


import com.example.vucem_catalogos_service.business.Interface.ICatAduanaTramiteService;
import com.example.vucem_catalogos_service.core.constants.CatalogPaths;
import com.example.vucem_catalogos_service.model.dto.AduanaClasifProducto.CatAduanaClasifProdRequestDTO;
import com.example.vucem_catalogos_service.model.dto.AduanaClasifProducto.CatAduanaClasifProdResponseDTO;
import com.example.vucem_catalogos_service.model.dto.AduanaTramite.CatAduanaTramiteRequestDTO;
import com.example.vucem_catalogos_service.model.dto.AduanaTramite.CatAduanaTramiteResponseDTO;
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
public class CatAduanaTramiteController {

    @Autowired
    private ICatAduanaTramiteService service;

    @GetMapping(CatalogPaths.LIST_ADUANA_TRAMITE)
    public ResponseEntity<PageResponseDTO<CatAduanaTramiteResponseDTO>> listarAduanaTramite(
            @RequestParam(required = false) String search,
            Pageable pageable) {
        return ResponseEntity.ok(
                service.listarAduanaTramite(search, pageable));
    }

    @PostMapping(CatalogPaths.SAVE_ADUANA_TRAMITE)
    public ResponseEntity<CatAduanaTramiteResponseDTO> crearAduanaTramite(@RequestBody CatAduanaTramiteRequestDTO dto) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.crearAduanaTramite(dto));
    }

    @GetMapping(CatalogPaths.FIND_ADUANA_TRAMITE)
    public ResponseEntity<CatAduanaTramiteResponseDTO> findById(
            @PathVariable Long id) {

        return ResponseEntity.ok(service.findById(id));
    }

    @PutMapping(CatalogPaths.UPDATE_ADUANA_TRAMITE)
    public ResponseEntity<CatAduanaTramiteResponseDTO> update(
            @PathVariable Long id,
            @RequestBody CatAduanaTramiteRequestDTO dto) {

        return ResponseEntity.ok(service.update(id, dto));
    }

    @GetMapping(CatalogPaths.LIST_TIPOS_TRAMITE)
    public ResponseEntity<List<SelectDTO>> listadoTiposTramite(){
        List<SelectDTO> lista = service.listadoTiposTramite();
        return ResponseEntity.ok(lista);
    }
}
