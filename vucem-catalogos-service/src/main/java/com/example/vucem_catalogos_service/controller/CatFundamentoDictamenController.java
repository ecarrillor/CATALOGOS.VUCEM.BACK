package com.example.vucem_catalogos_service.controller;

import com.example.vucem_catalogos_service.business.Interface.ICatFundamentoDictamenService;
import com.example.vucem_catalogos_service.core.constants.CatalogPaths;
import com.example.vucem_catalogos_service.model.dto.FundamentoDictamen.CatFundamentoDictamenRequestDTO;
import com.example.vucem_catalogos_service.model.dto.FundamentoDictamen.CatFundamentoDictamenResponseDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(CatalogPaths.CONTROLLER)
public class CatFundamentoDictamenController {

    @Autowired
    private ICatFundamentoDictamenService iCatFundamentoDictamenService;


    @GetMapping(CatalogPaths.LIST_CATALOGO_FUNDAMENTO_DICTAMEN)
    public ResponseEntity<PageResponseDTO<CatFundamentoDictamenResponseDTO>> listarFundamentoDictamen(
            @RequestParam(required = false) String search,
            Pageable pageable) {
        return ResponseEntity.ok(
                iCatFundamentoDictamenService.listarFundamentoDictamen(search, pageable));
    }

    @PostMapping(CatalogPaths.SAVE_CATALOGO_FUNDAMENTO_DICTAMEN)
    public ResponseEntity<CatFundamentoDictamenResponseDTO> crear(@RequestBody CatFundamentoDictamenRequestDTO dto) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(iCatFundamentoDictamenService.crearFundamentoDictamen(dto));
    }

    @GetMapping(CatalogPaths.FIND_CATALOGO_FUNDAMENTO_DICTAMEN)
    public ResponseEntity<CatFundamentoDictamenResponseDTO> findById(
            @PathVariable Long id) {

        return ResponseEntity.ok(iCatFundamentoDictamenService.findById(id));
    }

    @PutMapping(CatalogPaths.UPDATE_CATALOGO_FUNDAMENTO_DICTAMEN)
    public ResponseEntity<CatFundamentoDictamenResponseDTO> update(
            @PathVariable Long id,
            @RequestBody CatFundamentoDictamenRequestDTO dto) {

        return ResponseEntity.ok(iCatFundamentoDictamenService.update(id, dto));
    }
}
