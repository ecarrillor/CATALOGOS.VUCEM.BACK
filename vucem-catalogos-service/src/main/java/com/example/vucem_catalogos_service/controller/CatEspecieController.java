package com.example.vucem_catalogos_service.controller;


import com.example.vucem_catalogos_service.business.Interface.ICatEspecieService;
import com.example.vucem_catalogos_service.core.constants.CatalogPaths;
import com.example.vucem_catalogos_service.model.dto.ClasifProductoTraDTO;
import com.example.vucem_catalogos_service.model.dto.Especie.CatEspecieRequestDTO;
import com.example.vucem_catalogos_service.model.dto.Especie.CatEspecieResponseDTO;
import com.example.vucem_catalogos_service.model.dto.LeyendaTexto.CatLeyendaTextoRequestDTO;
import com.example.vucem_catalogos_service.model.dto.LeyendaTexto.CatLeyendaTextoResponseDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(CatalogPaths.CONTROLLER)
public class CatEspecieController {

    @Autowired
    private ICatEspecieService catEspecieService;


    @GetMapping(CatalogPaths.LIST_CAT_ESPECIE)
    public ResponseEntity<PageResponseDTO<CatEspecieResponseDTO>> listarEspecie(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) Long tipo,
            Pageable pageable) {
        return ResponseEntity.ok(
                catEspecieService.listarEspecie(search, tipo, pageable));
    }

    @PostMapping(CatalogPaths.SAVE_CAT_ESPECIE)
    public ResponseEntity<CatEspecieResponseDTO> crear(@RequestBody CatEspecieRequestDTO dto, @PathVariable Long tipo) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(catEspecieService.crearEspecie(dto, tipo));
    }

    @GetMapping(CatalogPaths.FIND_CAT_ESPECIE)
    public ResponseEntity<CatEspecieResponseDTO> findById(
            @PathVariable Integer id) {

        return ResponseEntity.ok(catEspecieService.findById(id));
    }

    @PutMapping(CatalogPaths.UPDATE_CAT_ESPECIE)
    public ResponseEntity<CatEspecieResponseDTO> update(
            @PathVariable Integer id,
            @RequestBody CatEspecieRequestDTO dto) {

        return ResponseEntity.ok(catEspecieService.update(id, dto));
    }


    @GetMapping(CatalogPaths.LIST_TIPO_TRAMITE_ESPECIE)
    public ResponseEntity<List<ClasifProductoTraDTO>> listadoTipoTramite() {
        return ResponseEntity.ok(catEspecieService.listadoTipoTramite());
    }

    @GetMapping(CatalogPaths.LAST_ESPECIE)
    public ResponseEntity<ClasifProductoTraDTO> lastEspecie() {
        return ResponseEntity.ok(catEspecieService.lastEspecie());
    }
}
