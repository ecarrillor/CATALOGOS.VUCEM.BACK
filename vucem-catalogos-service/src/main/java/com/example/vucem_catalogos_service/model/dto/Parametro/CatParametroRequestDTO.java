package com.example.vucem_catalogos_service.model.dto.Parametro;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@Builder
public class CatParametroRequestDTO {

    private String cveParametro;
    private String descripcion;
    private String valor;
    private Long idDependencia;
    private LocalDate fecIniVigencia;
    private LocalDate fecFinVigencia;
    private Boolean blnActivo;

}
