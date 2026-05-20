package mx.gob.sat.catalogo.controller.response.controlreferencia;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * DTO de respuesta para el catalogo de control de referencia.
 */
@Getter
@Setter
public class CatControlReferenciaResponse {

    @JsonProperty("idControlReferencia")
    private Integer idControlReferencia;

    @JsonProperty("ideTipoPresentacion")
    private String ideTipoPresentacion;

    @JsonProperty("ideSubtipoPresentacion")
    private String ideSubtipoPresentacion;

    @JsonProperty("minimo")
    private Short minimo;

    @JsonProperty("maximo")
    private Short maximo;

    @JsonProperty("cantidadPresentacion")
    private BigDecimal cantidadPresentacion;

    @JsonProperty("tamanioMuestra")
    private BigDecimal tamanioMuestra;

    @JsonProperty("fecIniVigencia")
    private Instant fecIniVigencia;

    @JsonProperty("fecFinVigencia")
    private Instant fecFinVigencia;

    @JsonProperty("blnActivo")
    private Boolean blnActivo;
}
