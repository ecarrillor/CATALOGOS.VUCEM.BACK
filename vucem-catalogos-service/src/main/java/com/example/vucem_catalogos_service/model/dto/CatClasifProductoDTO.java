package com.example.vucem_catalogos_service.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CatClasifProductoDTO {
    private Long idClasifProduct;
    private Long idTipoTramite;
    private String nombreTipoTramite;
    private Long idClasifProductoR;
    private String nombreClasifProductoR;
    private String nombre;
    private String ideTipoClasifProducto;
    private LocalDate fecIniVigencia;
    private LocalDate fecFinVigencia;
    private Boolean blnActivo;
}
