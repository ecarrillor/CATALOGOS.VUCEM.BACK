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
public class CatTipoRfcDTO {
    private String rfc;
    private String ideTipoRfc;
    private String razonSocial;
    private LocalDate fecFinVigencia;
    private LocalDate fecIniVigencia;
    private String direccion;
    private String telefono;
    private String clave;
    private Boolean blnActivo;
    private String correoElectronico;
    private String fax;
    private Boolean blnLabAcreditado;
    private String cveUnidadAdministrativa;
    private String nombreUnidadAdministrativa;
}
