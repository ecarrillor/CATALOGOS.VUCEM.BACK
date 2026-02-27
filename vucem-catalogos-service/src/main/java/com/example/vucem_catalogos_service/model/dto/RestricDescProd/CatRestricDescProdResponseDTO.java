package com.example.vucem_catalogos_service.model.dto.RestricDescProd;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CatRestricDescProdResponseDTO {

    private Long idRestricDescProd;

    private Short idRestriccionTtra;
    private String descRestriccion;

    private Integer idDescripcionProd;
    private String descripcionProducto;

    private Instant fecIniVigencia;
    private Instant fecFinVigencia;
    private Boolean blnActivo;
}
