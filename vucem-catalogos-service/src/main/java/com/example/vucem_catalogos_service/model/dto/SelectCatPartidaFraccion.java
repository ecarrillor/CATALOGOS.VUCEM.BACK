package com.example.vucem_catalogos_service.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SelectCatPartidaFraccion {

    private String cvePartidaFraccion;
    private String nombre;

    public SelectCatPartidaFraccion(String cvePartidaFraccion, String nombre) {
        this.cvePartidaFraccion = cvePartidaFraccion;
        this.nombre = nombre;
    }
}
