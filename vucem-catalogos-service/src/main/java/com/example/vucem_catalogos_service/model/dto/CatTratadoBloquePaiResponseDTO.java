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
public class CatTratadoBloquePaiResponseDTO {

    private String cvePais;
    private String nombrePais;
    private Short idTratadoAcuerdo;
    private String cveTratadoAcuerdo;
    private LocalDate fecCaptura;
    private LocalDate fecIniVigencia;
    private LocalDate fecFinVigencia;
    private Boolean blnActivo;
    private Boolean blnEnvioElectronico;
    private Boolean blnMuestraCertificado;
}
