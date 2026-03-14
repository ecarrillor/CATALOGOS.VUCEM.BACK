package com.example.vucem_catalogos_service.controller;

import com.example.vucem_catalogos_service.business.Interface.ICatMontoExportacionService;
import com.example.vucem_catalogos_service.core.constants.CatalogPaths;
import com.example.vucem_catalogos_service.model.dto.CatDiaNoLaborableDTO;
import com.example.vucem_catalogos_service.model.dto.CatMontoExportacionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping(CatalogPaths.CONTROLLER)
public class CatMontoExportacionController {

    @Autowired
    private ICatMontoExportacionService iCatMontoExportacionService;


    @GetMapping(CatalogPaths.FIND_BY_DATE_ID)
    public ResponseEntity<CatMontoExportacionDTO> findMontoExportacion(
            @PathVariable String id,
            @PathVariable LocalDate fecha) {
        return ResponseEntity.ok(iCatMontoExportacionService.findMontoExportacion(id, fecha));
    }

    @PutMapping(CatalogPaths.UPDATE_BY_DATE_ID)
    public ResponseEntity<CatMontoExportacionDTO> update(
            @PathVariable String id,
            @PathVariable LocalDate fecha,
            @RequestBody CatMontoExportacionDTO dto) {
        return ResponseEntity.ok(iCatMontoExportacionService.update(id, fecha, dto));
    }
}
