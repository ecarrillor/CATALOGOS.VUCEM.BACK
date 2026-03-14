package com.example.vucem_catalogos_service.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
public class CatAprobCertSeResponseDTO {

    private Short cveUnidadAdministrativaId;

    private String laboratorioName;

    private String tipoAprobacion;

    private String numAprobacion;

    private LocalDate fecEmision;

    private LocalDate fecIniVigencia;

    private LocalDate fecFinVigencia;

    private Boolean blnActivo;
}