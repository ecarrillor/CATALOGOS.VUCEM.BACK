package com.example.vucem_catalogos_service.model.dto.FraccionTtraDescProd;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CatFraccionTtraDescProdResponseDTO {

    private Long idFraccionTtraDescProd;

    private Integer idDescripcionProd;
    private String descripcionProducto;

    private Long idFraccionGob;
    private String cveFraccion;
    private String descripcionFraccion;

    private LocalDate fecIniVigencia;

    private LocalDate fecFinVigencia;

    private Boolean blnActivo;
}
