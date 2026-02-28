package com.example.vucem_catalogos_service.model.dto.DescripcionProd;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CatDescripcionProdResponseDTO {

    private Integer idDescripcionProd;

    private String descripcionProducto;

    private Instant fecCaptura;

    private Instant fecIniVigencia;

    private Instant fecFinVigencia;

    private Boolean blnActivo;
}
