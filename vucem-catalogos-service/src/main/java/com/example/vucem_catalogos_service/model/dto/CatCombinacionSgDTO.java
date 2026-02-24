package com.example.vucem_catalogos_service.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CatCombinacionSgDTO {
    private Long id;
    private String cvcEspecie;
    private String descEspecie;
    private String cvcFuncionZootecnica;
    private String descFuncionZootecnica;
    private String cvcNombreComun;
    private String descNombreComun;
    private String cvcTipoProducto;
    private String descTipoProducto;
    private String cvePais;
    private String nombrePais;
    private Instant fecIniVigencia;
    private Instant fecFinVigencia;
    private Short blnActivo;
    private String ideTipoCertificadoMerc;
}
