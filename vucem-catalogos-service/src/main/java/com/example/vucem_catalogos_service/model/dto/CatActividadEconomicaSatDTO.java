package com.example.vucem_catalogos_service.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CatActividadEconomicaSatDTO {
    private Long id;
    private Long idActividadEconomicaR;
    private String descActividadEconomicaR;
    private String descripcion;
    private String descScian;
    private String descNotas;
    private String descEmpresaRecif;
    private String cveTipoEmpresaRecif;
    private LocalDate fecIniVigencia;
    private LocalDate fecFinVigencia;
    private LocalDate fecCaptura;
    private LocalDate fecActualizacion;
    private String cveTipoIndustriaIdc;
    private Boolean blnActivo;
}
