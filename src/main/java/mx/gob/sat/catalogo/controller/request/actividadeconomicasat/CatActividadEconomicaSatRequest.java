package mx.gob.sat.catalogo.controller.request.actividadeconomicasat;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CatActividadEconomicaSatRequest {

    @JsonProperty("idActividadEconomicaSat")
    private Long idActividadEconomicaSat;

    @JsonProperty("idActividadEconomicaR")
    private Long idActividadEconomicaR;

    @JsonProperty("descripcion")
    private String descripcion;

    @JsonProperty("descScian")
    private String descScian;

    @JsonProperty("descNotas")
    private String descNotas;

    @NotNull
    @JsonProperty("fecIniVigencia")
    private LocalDate fecIniVigencia;

    @JsonProperty("fecFinVigencia")
    private LocalDate fecFinVigencia;

    @NotNull
    @JsonProperty("fecCaptura")
    private LocalDate fecCaptura;

    @JsonProperty("fecActualizacion")
    private LocalDate fecActualizacion;

    @JsonProperty("cveTipoIndustriaIdc")
    private String cveTipoIndustriaIdc;

    @NotNull
    @JsonProperty("blnActivo")
    private Boolean blnActivo;

    @JsonProperty("cveTipoEmpresaRecif")
    private String cveTipoEmpresaRecif;
}
