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
public class CatNormalOficialDTO {
    private Integer id;
    private String claveNorma;
    private LocalDate fecIniVigencia;
    private LocalDate fecFinVigencia;
    private Boolean blnActivo;
    private String descNorma;
    private LocalDate fecPublicacion;
    private LocalDate fecEntradaVigor;
    private String ideClasifNorma;
    private String cvePais;
    private String nombrePais;
    private Integer idNormaOficialR;
    private Boolean blnLoteEstructurado;
}
