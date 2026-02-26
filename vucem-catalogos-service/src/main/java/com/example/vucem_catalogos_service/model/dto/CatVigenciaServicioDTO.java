package com.example.vucem_catalogos_service.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CatVigenciaServicioDTO {
    private Short id;
    private String numVigencia;
    private String ideTipoVigencia;
    private String ideTipoServicioCeror;
    private String cvePais;
    private String nombrePais;
    private Short idTratadoAcuerdo;
    private Short idBloque;
    private String nombreBloque;
    private String criterio;
    private LocalDate fecIniVigencia;
    private Boolean blnActivo;
}
