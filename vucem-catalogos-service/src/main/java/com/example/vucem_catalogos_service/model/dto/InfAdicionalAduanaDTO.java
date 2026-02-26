package com.example.vucem_catalogos_service.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InfAdicionalAduanaDTO {
    private String cveAduana;
    private String nombreAduana;
    private String correoNotificacion;
    private Short blnCuentaRni;
    private String tagAduana;
}
