package com.example.vucem_catalogos_service.model.dto.DescripcionProd;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CatDescripcionProdResponseDTO {

    private Integer idDescripcionProd;

    private String descripcionProducto;

    private LocalDate fecCaptura;

    private LocalDate fecIniVigencia;

    private LocalDate fecFinVigencia;

    private Boolean blnActivo;

    private Long idTipoTramite;

    private String nombreTramite;

    public CatDescripcionProdResponseDTO(Integer idDescripcionProd, String descripcionProducto, LocalDate fecCaptura, LocalDate fecIniVigencia, LocalDate fecFinVigencia, Boolean blnActivo) {
        this.idDescripcionProd = idDescripcionProd;
        this.descripcionProducto = descripcionProducto;
        this.fecCaptura = fecCaptura;
        this.fecIniVigencia = fecIniVigencia;
        this.fecFinVigencia = fecFinVigencia;
        this.blnActivo = blnActivo;
    }
}
