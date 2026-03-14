package com.example.vucem_catalogos_service.model.dto.CategoriaTextil;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Builder
public class CatCategoriaTextilRequestDTO {

    private Long idCategoriaTextil;
    private String descripcion;
    private String codigoCategoria;
    private Boolean blnPr;
    private Double factorConversion;
    private String cveUnidadMedida;
    private String cveUnidadMedidaEquivalente;
    private LocalDate fecIniVigencia;
    private LocalDate fecFinVigencia;
    private Boolean blnActivo;
}
