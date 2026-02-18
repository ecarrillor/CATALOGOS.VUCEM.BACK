package com.example.vucem_catalogos_service.controller;


import com.example.vucem_catalogos_service.business.Interface.ICatAduanaService;
import com.example.vucem_catalogos_service.business.Interface.ICatEntidadService;
import com.example.vucem_catalogos_service.core.constants.CatalogPaths;
import com.example.vucem_catalogos_service.model.entity.CatAduana;
import com.example.vucem_catalogos_service.model.entity.CatEntidad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(CatalogPaths.CONTROLLER)
public class CatAduanaController {


    @Autowired
    private ICatAduanaService service;



    @GetMapping(CatalogPaths.LIST_CATALOGO_ADUANA)
    public Page<CatAduana> listarCatAduana(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(required = false) String search) {


        return service.catAduanaListAll(page, size, search);
    }

}
