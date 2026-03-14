package com.example.vucem_catalogos_service.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CatPaisRestriccionTtraDTO {
    private Integer id;
    private Long idTipoTramite;
    private String nombreTipoTramite;
    private String cvePais;
    private String nombrePais;
    private String ideTipoRestriccionPaisTtra;
    private LocalDate fecIniVigencia;
    private LocalDate fecFinVigencia;
    private Boolean blnActivo;
}
