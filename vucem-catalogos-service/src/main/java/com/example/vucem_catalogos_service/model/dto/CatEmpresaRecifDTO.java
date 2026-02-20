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
public class CatEmpresaRecifDTO {
    private String recif;
    private String rfc;
    private String razonSocial;
    private Boolean blnActivo;
    private LocalDate fecIniVigencia;
    private LocalDate fecFinVigencia;
    private String cveUnidadAdministrativa;
    private String nombreUnidadAdministrativa;
}
