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
public class CatSuplenciaDTO {
    private Short id;
    private Long idDependencia;
    private String nombreDependencia;
    private String texto;
    private LocalDate fecIniVigencia;
    private LocalDate fecFinVigencia;
    private Boolean blnActivo;
}
