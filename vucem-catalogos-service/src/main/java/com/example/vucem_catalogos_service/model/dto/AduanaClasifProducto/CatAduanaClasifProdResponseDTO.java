package com.example.vucem_catalogos_service.model.dto.AduanaClasifProducto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Builder
public class CatAduanaClasifProdResponseDTO {

    private Long idAduanaClasifProduct;

    private String cveAduana;
    private String nombreAduana;

    private Long idClasifProducto;
    private String nombreClasifProducto;

    private LocalDate fecIniVigencia;
    private LocalDate fecFinVigencia;
    private Boolean blnActivo;
}
