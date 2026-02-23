package com.example.vucem_catalogos_service.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

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
    private Instant fecIniVigencia;
    private Instant fecFinVigencia;
    private Boolean blnActivo;
    private String codCamir;
    private Short blnComRfMf;
    private String correoElectronico;
    private String descUrl;
    private String tipo;
}
