package com.example.vucem_catalogos_service.controller;

import com.example.vucem_catalogos_service.business.Interface.ICatAprobCertService;
import com.example.vucem_catalogos_service.core.constants.CatalogPaths;
import com.example.vucem_catalogos_service.model.dto.CatAprobCertSeRequestDTO;
import com.example.vucem_catalogos_service.model.dto.CatAprobCertSeResponseDTO;
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
public class CatAprobCertSeController {
    @Autowired
    private ICatAprobCertService service;



    @GetMapping(CatalogPaths.LIST_CATALOGO_APROB_CERT)
    public ResponseEntity<PageResponseDTO<CatAprobCertSeResponseDTO>> list(
            @RequestParam(required = false) String search,
            Pageable pageable) {

        return ResponseEntity.ok(
                service.list(search, pageable));
    }


    @GetMapping(CatalogPaths.FIND_CATALOGO_APROB_CERT)
    public ResponseEntity<CatAprobCertSeResponseDTO> findById(
            @PathVariable Short id) {

        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping(CatalogPaths.SAVE_CATALOGO_APROB_CERT)
    public ResponseEntity<CatAprobCertSeResponseDTO> create(
            @RequestBody CatAprobCertSeRequestDTO dto) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.create(dto));
    }

    @PutMapping(CatalogPaths.UPDATE_CATALOGO_APROB_CERT)
    public ResponseEntity<CatAprobCertSeResponseDTO> update(
            @PathVariable Short id,
            @RequestBody CatAprobCertSeRequestDTO dto) {

        return ResponseEntity.ok(service.update(id, dto));
    }
    @GetMapping(CatalogPaths.LIST_LABORATORIOS)
    public ResponseEntity<List<SelectDTO>> listadoLaboratorio(){
        List<SelectDTO> lista = service.listadoLaboratorio();
        return ResponseEntity.ok(lista);
    }

}
