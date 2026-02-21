package com.example.vucem_catalogos_service.model.dto.LeyendaTexto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Builder
public class CatLeyendaTextoRequestDTO {

    private Long cveLeyenda;
    private String tipoLeyenda;
    private Short numAnio;
    private String leyenda;
    private LocalDate fecIniVigencia;
    private LocalDate fecFinVigencia;
    private Boolean blnActivo;
}
