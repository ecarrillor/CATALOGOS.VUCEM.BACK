package com.example.vucem_catalogos_service.model.dto.DescripcionProd;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CatDescripcionProdRequestDTO {

    private Integer id;

    private String descripcionProducto;

    private LocalDate fecCaptura;

    private LocalDate fecIniVigencia;

    private LocalDate fecFinVigencia;

    private Boolean blnActivo;
}
