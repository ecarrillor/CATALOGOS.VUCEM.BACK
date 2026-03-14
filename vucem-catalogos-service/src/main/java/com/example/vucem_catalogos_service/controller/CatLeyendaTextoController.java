package com.example.vucem_catalogos_service.controller;

import com.example.vucem_catalogos_service.business.Interface.ICatLeyendaTextoService;
import com.example.vucem_catalogos_service.core.constants.CatalogPaths;
import com.example.vucem_catalogos_service.model.dto.LeyendaTexto.CatLeyendaTextoRequestDTO;
import com.example.vucem_catalogos_service.model.dto.LeyendaTexto.CatLeyendaTextoResponseDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(CatalogPaths.CONTROLLER)
public class CatLeyendaTextoController {

    @Autowired
    private ICatLeyendaTextoService iCatLeyendaTextoService;

    @GetMapping(CatalogPaths.LIST_CATALOGO_LEYENDA_TEXTO)
    public ResponseEntity<PageResponseDTO<CatLeyendaTextoResponseDTO>> listarLeyendaTexto(
            @RequestParam(required = false) String search,
            Pageable pageable) {
        return ResponseEntity.ok(
                iCatLeyendaTextoService.listarLeyendaTexto(search, pageable));
    }

    @PostMapping(CatalogPaths.SAVE_CATALOGO_LEYENDA_TEXTO)
    public ResponseEntity<CatLeyendaTextoResponseDTO> crear(@RequestBody CatLeyendaTextoRequestDTO dto) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(iCatLeyendaTextoService.crearLeyendaTexto(dto));
    }

    @GetMapping(CatalogPaths.FIND_CATALOGO_LEYENDA_TEXTO)
    public ResponseEntity<CatLeyendaTextoResponseDTO> findById(
            @PathVariable Long id) {

        return ResponseEntity.ok(iCatLeyendaTextoService.findById(id));
    }

    @PutMapping(CatalogPaths.UPDATE_CATALOGO_LEYENDA_TEXTO)
    public ResponseEntity<CatLeyendaTextoResponseDTO> update(
            @PathVariable Long id,
            @RequestBody CatLeyendaTextoRequestDTO dto) {

        return ResponseEntity.ok(iCatLeyendaTextoService.update(id, dto));
    }

}
