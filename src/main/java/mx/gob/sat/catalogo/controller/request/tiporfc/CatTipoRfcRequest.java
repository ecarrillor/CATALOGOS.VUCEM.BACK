package mx.gob.sat.catalogo.controller.request.tiporfc;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class CatTipoRfcRequest {
    @JsonProperty("rfc") private String rfc;
    @JsonProperty("ideTipoRfc") private String ideTipoRfc;
    @JsonProperty("razonSocial") private String razonSocial;
    @JsonProperty("fecFinVigencia") private LocalDate fecFinVigencia;
    @JsonProperty("fecIniVigencia") private LocalDate fecIniVigencia;
    @JsonProperty("direccion") private String direccion;
    @JsonProperty("telefono") private String telefono;
    @JsonProperty("clave") private String clave;
    @JsonProperty("blnActivo") private Boolean blnActivo;
    @JsonProperty("correoElectronico") private String correoElectronico;
    @JsonProperty("fax") private String fax;
    @JsonProperty("blnLabAcreditado") private Boolean blnLabAcreditado;
    @JsonProperty("cveUnidadAdministrativa") private String cveUnidadAdministrativa;
}
