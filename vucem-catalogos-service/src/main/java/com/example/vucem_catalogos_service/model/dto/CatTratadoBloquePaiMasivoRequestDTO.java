package com.example.vucem_catalogos_service.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CatTratadoBloquePaiMasivoRequestDTO {

    private List<String> paises;
    private List<Short> tratados;
    private LocalDate fecIniVigencia;
    private LocalDate fecFinVigencia;
    private Boolean blnActivo;
    private Boolean blnEnvioElectronico;
    private Boolean blnMuestraCertificado;
}
