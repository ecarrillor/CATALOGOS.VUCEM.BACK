package com.example.vucem_catalogos_service.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CatActividadEconomicaSatDTO {
    private Long id;
    private Long idActividadEconomicaR;
    private String descripcionPadre;
    private String descripcion;
    private String descScian;
    private String descNotas;
    private Instant fecIniVigencia;
    private Instant fecFinVigencia;
    private Instant fecCaptura;
    private Instant fecActualizacion;
    private String cveTipoIndustriaIdc;
    private Boolean blnActivo;
}
