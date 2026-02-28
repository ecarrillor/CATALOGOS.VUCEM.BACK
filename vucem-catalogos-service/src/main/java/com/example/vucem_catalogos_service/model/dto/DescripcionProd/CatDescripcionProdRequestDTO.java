package com.example.vucem_catalogos_service.model.dto.DescripcionProd;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CatDescripcionProdRequestDTO {

    private Integer id;

    private String descripcionProducto;

    private Instant fecCaptura;

    private Instant fecIniVigencia;

    private Instant fecFinVigencia;

    private Boolean blnActivo;
}
