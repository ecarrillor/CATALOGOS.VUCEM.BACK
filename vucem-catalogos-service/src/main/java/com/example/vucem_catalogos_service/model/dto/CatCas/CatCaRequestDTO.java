package com.example.vucem_catalogos_service.model.dto.CatCas;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CatCaRequestDTO {

    private Short id;

    private String descCas;

    private LocalDate fecIniVigencia;

    private LocalDate fecFinVigencia;

    private Boolean blActivo;
}
