package com.example.vucem_catalogos_service.controller;

import com.example.vucem_catalogos_service.business.Interface.ICatCategoriaTextilService;
import com.example.vucem_catalogos_service.core.constants.CatalogPaths;
import com.example.vucem_catalogos_service.model.dto.CategoriaTextil.CatCategoriaTextilRequestDTO;
import com.example.vucem_catalogos_service.model.dto.CategoriaTextil.CatCategoriaTextilResponseDTO;
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
public class CatCategoriaTextilController {

    @Autowired
    private ICatCategoriaTextilService iCatCategoriaTextilService;


    @GetMapping(CatalogPaths.LIST_CATALOGO_CATEGORIA_TEXTIL)
    public ResponseEntity<PageResponseDTO<CatCategoriaTextilResponseDTO>> listarCategoriaTextil(
            @RequestParam(required = false) String search,
            Pageable pageable) {
        return ResponseEntity.ok(
                iCatCategoriaTextilService.listarCategoriaTextil(search, pageable));
    }

    @PostMapping(CatalogPaths.SAVE_CATALOGO_CATEGORIA_TEXTIL)
    public ResponseEntity<CatCategoriaTextilResponseDTO> crear(@RequestBody CatCategoriaTextilRequestDTO dto) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(iCatCategoriaTextilService.crearCategoriaTextil(dto));
    }

    @GetMapping(CatalogPaths.FIND_CATALOGO_CATEGORIA_TEXTIL)
    public ResponseEntity<CatCategoriaTextilResponseDTO> findById(
            @PathVariable Long id) {

        return ResponseEntity.ok(iCatCategoriaTextilService.findById(id));
    }

    @PutMapping(CatalogPaths.UPDATE_CATALOGO_CATEGORIA_TEXTIL)
    public ResponseEntity<CatCategoriaTextilResponseDTO> update(
            @PathVariable Long id,
            @RequestBody CatCategoriaTextilRequestDTO dto) {

        return ResponseEntity.ok(iCatCategoriaTextilService.update(id, dto));
    }

    @GetMapping(CatalogPaths.UNIDAD_MEDIDA_CATALOGO_CATEGORIA_TEXTIL)
    public ResponseEntity<List<SelectDTO>> listadoTiposTramite(){
        List<SelectDTO> lista = iCatCategoriaTextilService.listadoUnidadMedida();
        return ResponseEntity.ok(lista);
    }

}
