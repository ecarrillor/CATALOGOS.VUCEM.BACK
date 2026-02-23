package com.example.vucem_catalogos_service.controller;

import com.example.vucem_catalogos_service.business.Interface.ICatPaisTratadoAcuerdoService;
import com.example.vucem_catalogos_service.core.constants.CatalogPaths;
import com.example.vucem_catalogos_service.model.dto.CatPaisTratadoAcuerdoRequestDTO;
import com.example.vucem_catalogos_service.model.dto.CatPaisTratadoAcuerdoResponseDTO;
import com.example.vucem_catalogos_service.model.dto.CatPlazoTtraDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(CatalogPaths.CONTROLLER)
public class CatPaisTratadoAcuerdoController {

    @Autowired
    private ICatPaisTratadoAcuerdoService iCatPaisTratadoAcuerdoService;

    @GetMapping(CatalogPaths.LIST_CATALOGO_PAIS_TRATADO_ACUERDO)
    public ResponseEntity<PageResponseDTO<CatPaisTratadoAcuerdoResponseDTO>> list(
            @RequestParam(required = false) String search,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(iCatPaisTratadoAcuerdoService.list(search, PageRequest.of(page, size)));
    }

    @GetMapping(CatalogPaths.FIND_CATALOGO_PAIS_TRATADO_ACUERDO)
    public ResponseEntity<CatPaisTratadoAcuerdoResponseDTO> findById(
            @PathVariable String idPais,
            @PathVariable Short idTratado) {
        return ResponseEntity.ok(iCatPaisTratadoAcuerdoService.findById(idPais, idTratado));
    }

    @PostMapping(CatalogPaths.SAVE_CATALOGO_PAIS_TRATADO_ACUERDO)
    public ResponseEntity<CatPaisTratadoAcuerdoResponseDTO> create(@RequestBody CatPaisTratadoAcuerdoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(iCatPaisTratadoAcuerdoService.create(dto));
    }

    @PutMapping(CatalogPaths.UPDATE_CATALOGO_PAIS_TRATADO_ACUERDO)
    public ResponseEntity<CatPaisTratadoAcuerdoResponseDTO> update(
            @PathVariable String idPais,
            @PathVariable Short idTratado,
            @RequestBody CatPaisTratadoAcuerdoRequestDTO dto) {
        return ResponseEntity.ok(iCatPaisTratadoAcuerdoService.update(idPais, idTratado, dto));
    }
}
