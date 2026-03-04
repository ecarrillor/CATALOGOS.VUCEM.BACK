package com.example.vucem_catalogos_service.model.dto.Especie;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@Builder
public class CatEspecieRequestDTO {

    private Integer numEspecie;
    private String descripcionEspecie;
    private LocalDate fecIniVigencia;
    private LocalDate fecFinVigencia;
    private Boolean blnActivo;
}
