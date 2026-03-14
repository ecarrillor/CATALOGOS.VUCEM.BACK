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
public class CatServicioImmexDTO {
    private Short id;
    private String nombre;
    private LocalDate fecIniVigencia;
    private String ideTipoServicioImmex;
    private LocalDate fecFinVigencia;
    private Boolean blnActivo;
}
