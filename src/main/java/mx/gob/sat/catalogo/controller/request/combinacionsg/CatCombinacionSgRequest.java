package mx.gob.sat.catalogo.controller.request.combinacionsg;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CatCombinacionSgRequest {
    @JsonProperty("idCombinacionSg") private Long idCombinacionSg;
    @JsonProperty("cvcEspecie") private String cvcEspecie;
    @JsonProperty("cvcFuncionZootecnica") private String cvcFuncionZootecnica;
    @JsonProperty("cvcNombreComun") private String cvcNombreComun;
    @JsonProperty("cvcTipoProducto") private String cvcTipoProducto;
    @JsonProperty("cvePais") private String cvePais;
    @JsonProperty("fecIniVigencia") private LocalDate fecIniVigencia;
    @JsonProperty("fecFinVigencia") private LocalDate fecFinVigencia;
    @JsonProperty("blnActivo") private Boolean blnActivo;
    @JsonProperty("ideTipoCertificadoMerc") private String ideTipoCertificadoMerc;
}
