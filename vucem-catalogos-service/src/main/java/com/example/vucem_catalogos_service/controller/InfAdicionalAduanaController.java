package com.example.vucem_catalogos_service.controller;

import com.example.vucem_catalogos_service.business.Interface.IInfAdicionalAduanaService;
import com.example.vucem_catalogos_service.core.constants.CatalogPaths;
import com.example.vucem_catalogos_service.model.dto.InfAdicionalAduanaDTO;
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
public class InfAdicionalAduanaController {

    @Autowired
    private IInfAdicionalAduanaService service;

    @GetMapping(CatalogPaths.LIST_INF_ADICIONAL_ADUANA)
    public ResponseEntity<PageResponseDTO<InfAdicionalAduanaDTO>> list(
            @RequestParam(required = false) String search,
            Pageable pageable) {
        return ResponseEntity.ok(service.list(search, pageable));
    }

    @GetMapping(CatalogPaths.FIND_INF_ADICIONAL_ADUANA)
    public ResponseEntity<InfAdicionalAduanaDTO> findById(@PathVariable String cveAduana) {
        return ResponseEntity.ok(service.findById(cveAduana));
    }

    @PostMapping(CatalogPaths.SAVE_INF_ADICIONAL_ADUANA)
    public ResponseEntity<InfAdicionalAduanaDTO> create(@RequestBody InfAdicionalAduanaDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PutMapping(CatalogPaths.UPDATE_INF_ADICIONAL_ADUANA)
    public ResponseEntity<InfAdicionalAduanaDTO> update(
            @PathVariable String cveAduana,
            @RequestBody InfAdicionalAduanaDTO dto) {
        return ResponseEntity.ok(service.update(cveAduana, dto));
    }

    @GetMapping(CatalogPaths.LIST_ADUANA_INF_ADICIONAL)
    public ResponseEntity<List<SelectDTO>> listadoAduana() {
        return ResponseEntity.ok(service.listadoAduana());
    }
}
