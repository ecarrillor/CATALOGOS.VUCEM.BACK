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
public class CatPartidaFraccionId implements Serializable {
    private static final long serialVersionUID = -8025172250457458988L;
    @Size(max = 2)
    @NotNull
    @Column(name = "cve_capitulo_fraccion", nullable = false, length = 2)
    private String cveCapituloFraccion;

    @Size(max = 2)
    @NotNull
    @Column(name = "cve_partida_fraccion", nullable = false, length = 2)
    private String cvePartidaFraccion;


}