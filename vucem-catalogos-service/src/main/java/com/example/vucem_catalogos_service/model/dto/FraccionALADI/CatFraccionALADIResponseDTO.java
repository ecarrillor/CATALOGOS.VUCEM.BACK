package com.example.vucem_catalogos_service.model.dto.FraccionALADI;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Builder
public class CatFraccionALADIResponseDTO {

    private Long idFraccionAladi;
    private String cveTipoFraccion;
    private String cveFraccionAladi;
    private String descripcion;
    private LocalDate fecIniVigencia;
    private LocalDate fecFinVigencia;
    private Boolean blnActivo;
}
