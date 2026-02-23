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
public class CatTratadoAcuerdoDTO {
    private Short id;
    private String ideTipoTratadoAcuerdo;
    private String cveTratadoAcuerdo;
    private String nombre;
    private Boolean blnPexim;
    private LocalDate fecCaptura;
    private LocalDate fecFinVigencia;
    private String ideTipoCupoSaai;
    private LocalDate fecIniVigencia;
    private Boolean blnActivo;
    private Boolean blnEvaluarIndividual;
}
