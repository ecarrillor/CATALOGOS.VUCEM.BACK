package com.example.vucem_catalogos_service.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
@Data
@AllArgsConstructor
public class CatPaisTratadoAcuerdoRequestDTO {

    private String cvePais;
    private Short idTratadoAcuerdo;
    private LocalDate fecIniVigencia;
    private LocalDate fecFinVigencia;
    private Boolean blnActivo;
}
