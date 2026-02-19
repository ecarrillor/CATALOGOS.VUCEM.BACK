package com.example.vucem_catalogos_service.model.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class CatPaisSaveDTO {
    private String cvePais;
    private String nombre;
    private String cveMoneda;
    private String cvePaisWco;
    private LocalDate fecFinVigencia;
    private LocalDate fecIniVigencia;
    private Boolean blnActivo;

}
