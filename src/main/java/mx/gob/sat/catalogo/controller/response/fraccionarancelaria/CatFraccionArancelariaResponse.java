package mx.gob.sat.catalogo.controller.response.fraccionarancelaria;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CatFraccionArancelariaResponse {
    @JsonProperty("cveFraccion") private String cveFraccion;
    @JsonProperty("descripcion") private String descripcion;
    @JsonProperty("cveSubpartidaFraccion") private String cveSubpartidaFraccion;
    @JsonProperty("cveCapituloFraccion") private String cveCapituloFraccion;
    @JsonProperty("cvePartidaFraccion") private String cvePartidaFraccion;
    @JsonProperty("fecCaptura") private LocalDate fecCaptura;
    @JsonProperty("fecIniVigencia") private LocalDate fecIniVigencia;
    @JsonProperty("fecFinVigencia") private LocalDate fecFinVigencia;
    @JsonProperty("capitulo") private String capitulo;
    @JsonProperty("partida") private String partida;
    @JsonProperty("subPartida") private String subPartida;
    @JsonProperty("cveUsuario") private String cveUsuario;
    @JsonProperty("blnActivo") private Boolean blnActivo;
    @JsonProperty("blnAnexo28") private Boolean blnAnexo28;
    @JsonProperty("blnDecretoImmex") private Boolean blnDecretoImmex;
}
