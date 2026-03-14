package com.example.vucem_catalogos_service.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@Embeddable
public class CatArancelProsecId implements Serializable {
    private static final long serialVersionUID = 7955580608601601932L;
    @Size(max = 8)
    @NotNull
    @Column(name = "cve_fraccion", nullable = false, length = 8)
    private String cveFraccion;

    @Size(max = 6)
    @NotNull
    @Column(name = "cve_sector_prosec", nullable = false, length = 6)
    private String cveSectorProsec;


}