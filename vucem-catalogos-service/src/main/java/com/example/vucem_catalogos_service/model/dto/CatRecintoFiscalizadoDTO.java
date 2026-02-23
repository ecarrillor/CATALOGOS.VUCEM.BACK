package com.example.vucem_catalogos_service.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CatRecintoFiscalizadoDTO {
    private Long id;
    private String cveAduana;
    private String nombreAduana;
    private String ideTipoRecintoFiscalizado;
    private String nombre;
    private String rfc;
    private String numAutorizacion;
    private LocalDate fecIniVigencia;
    private LocalDate fecFinVigencia;
    private Boolean blnActivo;
    private String codCamir;
    private Boolean blnComRfMf;
    private String correoElectronico;
    private String descUrl;
    private String tipo;
}
