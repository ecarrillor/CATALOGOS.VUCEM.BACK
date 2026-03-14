package com.example.vucem_catalogos_service.controller;

import com.example.vucem_catalogos_service.business.Interface.ICatTratadoBloquePaiService;
import com.example.vucem_catalogos_service.core.constants.CatalogPaths;
import com.example.vucem_catalogos_service.model.dto.*;
import com.example.vucem_catalogos_service.persistence.repo.ICatPaisRepository;
import com.example.vucem_catalogos_service.persistence.repo.ICatTratadoAcuerdoRepository;
import com.example.vucem_catalogos_service.persistence.repo.ICatTratadoBloquePaiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(CatalogPaths.CONTROLLER)
public class CatTratadoBloquePaiController {

    @Autowired
    private ICatTratadoBloquePaiService service;

    @GetMapping(CatalogPaths.LIST_TRATADO_BLOQUE_PAIS)
    public ResponseEntity<PageResponseDTO<CatTratadoBloquePaiResponseDTO>> list(
            @RequestParam(required = false) String cvePais,
            @RequestParam(required = false) Short idTratadoAcuerdo,
            @RequestParam(required = false) Boolean blnActivo,
            Pageable pageable) {
        return ResponseEntity.ok(service.list(cvePais, idTratadoAcuerdo, blnActivo, pageable));
    }

    @GetMapping(CatalogPaths.FIND_TRATADO_BLOQUE_PAIS)
    public ResponseEntity<CatTratadoBloquePaiResponseDTO> findById(
            @PathVariable String cvePais,
            @PathVariable Short idTratadoAcuerdo) {
        return ResponseEntity.ok(service.findById(cvePais, idTratadoAcuerdo));
    }

    @PostMapping(CatalogPaths.SAVE_TRATADO_BLOQUE_PAIS)
    public ResponseEntity<List<CatTratadoBloquePaiResponseDTO>> createMasivo(
            @RequestBody CatTratadoBloquePaiMasivoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createMasivo(dto));
    }

    @PutMapping(CatalogPaths.UPDATE_TRATADO_BLOQUE_PAIS)
    public ResponseEntity<CatTratadoBloquePaiResponseDTO> update(
            @PathVariable String cvePais,
            @PathVariable Short idTratadoAcuerdo,
            @RequestBody CatTratadoBloquePaiRequestDTO dto) {
        return ResponseEntity.ok(service.update(cvePais, idTratadoAcuerdo, dto));
    }

    @DeleteMapping(CatalogPaths.DELETE_TRATADO_BLOQUE_PAIS)
    public ResponseEntity<Void> delete(
            @PathVariable String cvePais,
            @PathVariable Short idTratadoAcuerdo) {
        service.delete(cvePais, idTratadoAcuerdo);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(CatalogPaths.COMBO_PAISES_TBP)
    public ResponseEntity<List<ICatPaisRepository.ComboProyeccion>> listadoPaises() {
        return ResponseEntity.ok(service.listadoPaises());
    }

    @GetMapping(CatalogPaths.COMBO_TRATADOS_TBP)
    public ResponseEntity<List<ICatTratadoAcuerdoRepository.ComboProyeccion>> listadoTratados() {
        return ResponseEntity.ok(service.listadoTratados());
    }

    @GetMapping(CatalogPaths.PAISES_GUARDADOS_TBP)
    public ResponseEntity<CatPaisesComboResponseDTO> paisesCombo(
            @RequestParam List<Short> idsTratado) {
        return ResponseEntity.ok(service.paisesComboByTratados(idsTratado));
    }

    @GetMapping(CatalogPaths.TRATADOS_GUARDADOS_TBP)
    public ResponseEntity<CatTratadoAcuerdoComboResponseDTO> tratadosGuardados(
            @RequestParam List<String> clavePaises) {
        return ResponseEntity.ok(service.tratadosGuardadosByPaises(clavePaises));
    }
}