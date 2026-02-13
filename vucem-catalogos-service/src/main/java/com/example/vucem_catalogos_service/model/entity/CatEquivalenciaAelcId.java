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

@Embeddable
public class CatEquivalenciaAelcId implements Serializable {
    private static final long serialVersionUID = -2481523761262642810L;
    @NotNull
    @Column(name = "fec_ini_vigencia", nullable = false)
    private Instant fecIniVigencia;

    @Size(max = 3)
    @NotNull
    @Column(name = "cve_moneda", nullable = false, length = 3)
    private String cveMoneda;


}
