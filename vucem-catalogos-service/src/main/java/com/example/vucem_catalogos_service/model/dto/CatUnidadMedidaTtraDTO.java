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
public class CatUnidadMedidaTtraDTO {
    private Integer id;
    private String cveUnidadMedida;
    private String descripcionUnidadMedida;
    private Long idTipoTramite;
    private String nombreTipoTramite;
    private LocalDate fecIniVigencia;
    private LocalDate fecFinVigencia;
    private Boolean blnActivo;
}
