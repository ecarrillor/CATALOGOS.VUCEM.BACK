package com.example.vucem_catalogos_service.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CatPaisTratadoAcuerdoRequestDTO {

    private String cvePais;
    private Short idTratadoAcuerdo;
    private LocalDate fecIniVigencia;
    private LocalDate fecFinVigencia;
    private LocalDate fecCaptura;
    private Boolean blnActivo;
}
