package com.example.vucem_catalogos_service.model.dto.RestricDescProd;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CatRestricDescProdRequestDTO {

    private Long id;

    private Short idRestriccionTtra;

    private Integer idDescripcionProd;

    private Instant fecIniVigencia;

    private Instant fecFinVigencia;

    private Boolean blnActivo;
}
