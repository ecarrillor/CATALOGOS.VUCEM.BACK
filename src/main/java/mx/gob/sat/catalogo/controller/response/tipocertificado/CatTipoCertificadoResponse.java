package mx.gob.sat.catalogo.controller.response.tipocertificado;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CatTipoCertificadoResponse {

    @JsonProperty("idTipoCertificado")
    private Long idTipoCertificado;

    @JsonProperty("cveCatalogo")
    private String cveCatalogo;

    @JsonProperty("descripcion")
    private String descripcion;

    @JsonProperty("fecIniVigencia")
    private LocalDate fecIniVigencia;

    @JsonProperty("fecFinVigencia")
    private LocalDate fecFinVigencia;

    @JsonProperty("blnActivo")
    private Boolean blnActivo;

    @JsonProperty("blnRfc")
    private Boolean blnRfc;
}
