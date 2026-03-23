package com.example.vucem_catalogos_service.controller;

import com.example.vucem_catalogos_service.business.Interface.IVucRepoFirmaGralSeService;
import com.example.vucem_catalogos_service.core.constants.CatalogPaths;
import com.example.vucem_catalogos_service.model.dto.ClasifProductoTraDTO;
import com.example.vucem_catalogos_service.model.dto.EnumeracionDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.dto.VucRepoFirmaGralSe.VucRepoFirmaGralSeRequestDTO;
import com.example.vucem_catalogos_service.model.dto.VucRepoFirmaGralSe.VucRepoFirmaGralSeResponseDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(CatalogPaths.CONTROLLER)
public class VucRepoFirmaGralSeController {

    @Autowired
    private IVucRepoFirmaGralSeService service;

    /**
     * Lista paginada con filtros opcionales.
     *
     * @param search    texto libre (RFC) o "activo"/"inactivo" para filtrar por estado
     * @param tipoFirma clave de tipo firma (ej. TIFR.TFMSV, TIFR.TFFCS) — opcional
     */
    @GetMapping(CatalogPaths.LIST_VUC_REPO_FIRMA)
    public ResponseEntity<PageResponseDTO<VucRepoFirmaGralSeResponseDTO>> listar(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String tipoFirma,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) String sortDir,
            Pageable pageable) {
        return ResponseEntity.ok(service.listAll(search, tipoFirma, sortBy, sortDir, pageable));
    }

    @GetMapping(CatalogPaths.FIND_VUC_REPO_FIRMA)
    public ResponseEntity<VucRepoFirmaGralSeResponseDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping(value = CatalogPaths.SAVE_VUC_REPO_FIRMA, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<VucRepoFirmaGralSeResponseDTO> create(
            @Valid @ModelAttribute VucRepoFirmaGralSeRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PutMapping(value = CatalogPaths.UPDATE_VUC_REPO_FIRMA, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<VucRepoFirmaGralSeResponseDTO> update(
            @PathVariable Integer id,
            @Valid @ModelAttribute VucRepoFirmaGralSeRequestDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }


    @GetMapping(CatalogPaths.LIST_TIPOS_FIRMA_VUC_REPO)
    public ResponseEntity<List<EnumeracionDTO>> listadoTiposFirma() {
        return ResponseEntity.ok(service.listadoTiposFirma());
    }

    @GetMapping(CatalogPaths.LIST_TIPOS_TRAMITE_VUC_REPO)
    public ResponseEntity<List<ClasifProductoTraDTO>> listadoTiposTramite() {
        return ResponseEntity.ok(service.listadoTiposTramite());
    }
}
