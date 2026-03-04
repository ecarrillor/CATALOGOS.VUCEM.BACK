package com.example.vucem_catalogos_service.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CatPaisTratadoAcuerdoResponseDTO {

    private String cvePais;
    private String nombrePais;
    private Short idTratado;
    private String tratadoAcuerdo;
    private LocalDate fecIniVigencia;
    private LocalDate fecFinVigencia;
    private LocalDate fecCaptura;
    private Boolean blnActivo;
}
