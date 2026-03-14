package com.example.vucem_catalogos_service.model.dto.AduanaClasifProducto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class CatAduanaClasifProdRequestDTO {

    private Long id;

    private String cveAduana;

    private Long idClasifProducto;

    private LocalDate fecIniVigencia;

    private LocalDate fecFinVigencia;

    private Boolean blnActivo;
}
