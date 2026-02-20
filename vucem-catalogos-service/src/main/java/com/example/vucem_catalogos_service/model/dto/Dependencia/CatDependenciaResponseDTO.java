package com.example.vucem_catalogos_service.model.dto.Dependencia;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Builder
public class CatDependenciaResponseDTO {

    private Long cveDependencia;
    private String nombreDependencia;
    private String acronimo;
    private String nombreCalendario;
    private String idCalendario;
    private Boolean tramiteVU;
    private LocalDate fecIniVigencia;
    private LocalDate fecFinVigencia;
    private Boolean blnActivo;
}
