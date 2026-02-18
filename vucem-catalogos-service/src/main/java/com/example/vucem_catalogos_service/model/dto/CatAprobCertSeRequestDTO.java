package com.example.vucem_catalogos_service.model.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CatAprobCertSeRequestDTO {

    private Short idAprobCert;

    private String cveUnidadAdministrativaId;

    private String tipoAprobacion;
    private String numAprobCert;

    private LocalDate fecEmision;

    private LocalDate fecIniVigencia;

    private LocalDate fecFinVigencia;

    private Boolean blnActivo;
}