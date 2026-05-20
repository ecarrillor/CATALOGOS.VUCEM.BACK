package mx.gob.sat.catalogo.controller.request.tipocertificado;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CatTipoCertificadoRequest {

    @JsonProperty("idTipoCertificado")
    private Long idTipoCertificado;

    @NotNull
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
