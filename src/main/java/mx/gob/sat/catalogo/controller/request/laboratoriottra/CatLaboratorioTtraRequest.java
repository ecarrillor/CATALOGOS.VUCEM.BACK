package mx.gob.sat.catalogo.controller.request.laboratoriottra;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CatLaboratorioTtraRequest {

    @JsonProperty("idLaboratorioTtra")
    private Long idLaboratorioTtra;

    @NotNull
    @JsonProperty("idTipoTramite")
    private Long idTipoTramite;

    @NotNull
    @JsonProperty("descLaboratorio")
    private String descLaboratorio;

    @JsonProperty("ideTipoLaboratorio")
    private String ideTipoLaboratorio;

    @NotNull
    @JsonProperty("fecIniVigencia")
    private LocalDate fecIniVigencia;

    @JsonProperty("fecFinVigencia")
    private LocalDate fecFinVigencia;

    @NotNull
    @JsonProperty("blnActivo")
    private Boolean blnActivo;
}
