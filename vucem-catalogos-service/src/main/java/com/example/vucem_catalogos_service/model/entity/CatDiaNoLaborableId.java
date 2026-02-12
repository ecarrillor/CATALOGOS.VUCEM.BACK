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
public class CatDiaNoLaborableId implements Serializable {
    private static final long serialVersionUID = 8373111051876371574L;
    @NotNull
    @Column(name = "fec_no_laborable", nullable = false)
    private Instant fecNoLaborable;

    @Size(max = 4)
    @NotNull
    @Column(name = "cve_calendario", nullable = false, length = 4)
    private String cveCalendario;


}