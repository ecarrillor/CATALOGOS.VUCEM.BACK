package com.example.vucem_catalogos_service.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CatSubpartidaFraccionDTO {
    private String cveSubpartidaFraccion;
    private String cveCapituloFraccion;
    private String cvePartidaFraccion;
    private String nombrePartidaFraccion;
    private LocalDate fecIniVigencia;
    private LocalDate fecFinVigencia;
    private Boolean blnActivo;
}
