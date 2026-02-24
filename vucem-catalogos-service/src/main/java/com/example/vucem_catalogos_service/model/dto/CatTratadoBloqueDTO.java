package com.example.vucem_catalogos_service.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CatTratadoBloqueDTO {
    private Short idTratadoAcuerdo;
    private Short idBloque;
    private String nombreTratadoAcuerdo;
    private String nombreBloque;
    private Instant fecIniVigencia;
    private Instant fecFinVigencia;
    private Boolean blnActivo;
}
