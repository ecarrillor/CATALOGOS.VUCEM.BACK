package com.example.vucem_catalogos_service.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;

@Data
@EqualsAndHashCode
@Embeddable
public class CatMontoExportacionId implements Serializable {
    private static final long serialVersionUID = -2479130627971183426L;
    @Size(max = 20)
    @NotNull
    @Column(name = "rfc_exportador", nullable = false, length = 20)
    private String rfcExportador;

    @NotNull
    @Column(name = "fec_monto_exportacion", nullable = false)
    private Instant fecMontoExportacion;


}
