package mx.gob.sat.catalogo.controller.response.laboratoriottra;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CatLaboratorioTtraResponse {

    @JsonProperty("idLaboratorioTtra")
    private Long idLaboratorioTtra;

    @JsonProperty("idTipoTramite")
    private Long idTipoTramite;

    @JsonProperty("descLaboratorio")
    private String descLaboratorio;

    @JsonProperty("ideTipoLaboratorio")
    private String ideTipoLaboratorio;

    @JsonProperty("fecIniVigencia")
    private LocalDate fecIniVigencia;

    @JsonProperty("fecFinVigencia")
    private LocalDate fecFinVigencia;

    @JsonProperty("blnActivo")
    private Boolean blnActivo;
}
