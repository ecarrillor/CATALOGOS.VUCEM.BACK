package mx.gob.sat.catalogo.controller.request.controlreferencia;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * DTO de solicitud para el catalogo de control de referencia.
 */
@Getter
@Setter
public class CatControlReferenciaRequest {

    @JsonProperty("idControlReferencia")
    private Integer idControlReferencia;

    @JsonProperty("ideTipoPresentacion")
    private String ideTipoPresentacion;

    @JsonProperty("ideSubtipoPresentacion")
    private String ideSubtipoPresentacion;

    @NotNull
    @JsonProperty("minimo")
    private Short minimo;

    @NotNull
    @JsonProperty("maximo")
    private Short maximo;

    @NotNull
    @JsonProperty("cantidadPresentacion")
    private BigDecimal cantidadPresentacion;

    @JsonProperty("tamanioMuestra")
    private BigDecimal tamanioMuestra;

    @NotNull
    @JsonProperty("fecIniVigencia")
    private Instant fecIniVigencia;

    @JsonProperty("fecFinVigencia")
    private Instant fecFinVigencia;

    @JsonProperty("blnActivo")
    private Boolean blnActivo;
}
