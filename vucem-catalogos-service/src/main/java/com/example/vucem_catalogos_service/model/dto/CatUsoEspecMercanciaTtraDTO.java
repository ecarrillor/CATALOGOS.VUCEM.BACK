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
public class CatUsoEspecMercanciaTtraDTO {
    private Short id;
    private Long idTipoTramite;
    private String descServicio;
    private String descUsoEspMercancia;
    private LocalDate fecIniVigencia;
    private LocalDate fecFinVigencia;
    private Boolean blnActivo;
    private Boolean blnReqRegistroSanitario;
}
