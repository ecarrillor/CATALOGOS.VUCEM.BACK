package com.example.vucem_catalogos_service.model.dto.FraccionTtraDescProd;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CatFraccionTtraDescProdRequestDTO {

    private Long id;

    private Integer idDescripcionProd;

    private Long idFraccionGob;

    private LocalDate fecIniVigencia;

    private LocalDate fecFinVigencia;

    private Boolean blnActivo;
}
