package com.example.vucem_catalogos_service.model.dto.CategoriaTextil;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Builder
public class CatCategoriaTextilResponseDTO {

    private Long idCategoriaTextil;
    private String descripcion;
    private String codigoCategoriaTextil;
    private Double factorConversion;
    private String unidadMedida;
    private String unidadMedidaEquivalente;
    private LocalDate fecIniVigencia;
    private LocalDate fecFinVigencia;
    private Boolean blnActivo;
}
