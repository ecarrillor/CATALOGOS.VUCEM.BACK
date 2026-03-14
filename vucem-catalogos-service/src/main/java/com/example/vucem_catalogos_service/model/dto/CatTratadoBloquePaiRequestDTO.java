package com.example.vucem_catalogos_service.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CatTratadoBloquePaiRequestDTO {

    private String cvePais;
    private Short idTratadoAcuerdo;
    private LocalDate fecCaptura;
    private LocalDate fecIniVigencia;
    private LocalDate fecFinVigencia;
    private Boolean blnActivo;
    private Boolean blnEnvioElectronico;
    private Boolean blnMuestraCertificado;
}
