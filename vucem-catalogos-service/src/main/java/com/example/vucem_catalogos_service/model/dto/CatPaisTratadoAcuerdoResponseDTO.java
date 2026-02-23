package com.example.vucem_catalogos_service.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
public class CatPaisTratadoAcuerdoResponseDTO {

    private String cvePais;
    private String NombrePais;
    private Short idTratado;
    private String tratadoAcuerdo;
    private LocalDate fecIniVigencia;
    private LocalDate fecFinVigencia;
    private Boolean blnActivo;
}
