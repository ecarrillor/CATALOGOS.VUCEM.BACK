package mx.gob.sat.catalogo.controller.response.casfraccionttra;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CatCasFraccionTtraResponse {
    @JsonProperty("idCasFraccionTtra") private Short idCasFraccionTtra;
    @JsonProperty("idCas") private Short idCas;
    @JsonProperty("cveFraccion") private String cveFraccion;
    @JsonProperty("idTipoTramite") private Long idTipoTramite;
    @JsonProperty("blnRotterdam") private Boolean blnRotterdam;
    @JsonProperty("fecIniVigencia") private LocalDate fecIniVigencia;
    @JsonProperty("fecFinVigencia") private LocalDate fecFinVigencia;
    @JsonProperty("blActivo") private Boolean blActivo;
    @JsonProperty("descFraccionAlt") private String descFraccionAlt;
    @JsonProperty("cvnWasser") private Short cvnWasser;
    @JsonProperty("cvnArmas") private Short cvnArmas;
    @JsonProperty("cvnMontreal") private Short cvnMontreal;
    @JsonProperty("cvnEstocolmo") private Short cvnEstocolmo;
    @JsonProperty("formaDesc") private Short formaDesc;
    @JsonProperty("ideIdentificadorRegla") private String ideIdentificadorRegla;
}
