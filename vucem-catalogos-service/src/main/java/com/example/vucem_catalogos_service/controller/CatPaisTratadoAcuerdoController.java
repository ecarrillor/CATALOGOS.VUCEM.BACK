package com.example.vucem_catalogos_service.controller;

import com.example.vucem_catalogos_service.business.Interface.ICatPaisTratadoAcuerdoService;
import com.example.vucem_catalogos_service.core.constants.CatalogPaths;
import com.example.vucem_catalogos_service.model.dto.CatPaisTratadoAcuerdoRequestDTO;
import com.example.vucem_catalogos_service.model.dto.CatPaisTratadoAcuerdoResponseDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.persistence.repo.ICatPaisRepository;
import com.example.vucem_catalogos_service.persistence.repo.ICatTratadoAcuerdoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(CatalogPaths.CONTROLLER)
public class CatPaisTratadoAcuerdoController {

    @Autowired
    private ICatPaisTratadoAcuerdoService service;

    @GetMapping(CatalogPaths.LIST_CATALOGO_PAIS_TRATADO_ACUERDO)
    public ResponseEntity<PageResponseDTO<CatPaisTratadoAcuerdoResponseDTO>> list(
            @RequestParam(required = false) String cvePais,
            @RequestParam(required = false) Short idTratadoAcuerdo,
            @RequestParam(required = false) Boolean blnActivo,
            Pageable pageable) {
        return ResponseEntity.ok(service.list(cvePais, idTratadoAcuerdo, blnActivo, pageable));
    }

    @GetMapping(CatalogPaths.FIND_CATALOGO_PAIS_TRATADO_ACUERDO)
    public ResponseEntity<CatPaisTratadoAcuerdoResponseDTO> findById(
            @PathVariable String idPais,
            @PathVariable Short idTratado) {
        return ResponseEntity.ok(service.findById(idPais, idTratado));
    }

    @PostMapping(CatalogPaths.SAVE_CATALOGO_PAIS_TRATADO_ACUERDO)
    public ResponseEntity<CatPaisTratadoAcuerdoResponseDTO> create(
            @RequestBody CatPaisTratadoAcuerdoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PutMapping(CatalogPaths.UPDATE_CATALOGO_PAIS_TRATADO_ACUERDO)
    public ResponseEntity<CatPaisTratadoAcuerdoResponseDTO> update(
            @PathVariable String idPais,
            @PathVariable Short idTratado,
            @RequestBody CatPaisTratadoAcuerdoRequestDTO dto) {
        return ResponseEntity.ok(service.update(idPais, idTratado, dto));
    }



    @GetMapping(CatalogPaths.COMBO_PAISES_PTA)
    public ResponseEntity<List<ICatPaisRepository.ComboProyeccion>> listadoPaises() {
        return ResponseEntity.ok(service.listadoPaises());
    }


}
