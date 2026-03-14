package com.example.vucem_catalogos_service.model.dto.VucRepoFirmaGralSe;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VucRepoFirmaGralSeResponseDTO {

    private Integer id;

    private String ideTipoFirma;

    private Long idTipoTramite;

    private String descTipoTramite;

    private String rfc;

    private String puesto;

    private LocalDate fecIniVigenia;

    private LocalDate fecFinVigenia;

    private Boolean blnActivo;

    private Boolean tieneCert;

    private Boolean tieneKey;

    private Boolean tieneFacsimil;

    private Boolean tieneSello;
}
