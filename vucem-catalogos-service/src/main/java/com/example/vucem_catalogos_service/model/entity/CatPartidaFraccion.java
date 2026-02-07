package com.example.vucem_catalogos_service.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "cat_partida_fraccion")
public class CatPartidaFraccion {
    @EmbeddedId
    private CatPartidaFraccionId id;

    @MapsId("cveCapituloFraccion")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cve_capitulo_fraccion", nullable = false, referencedColumnName = "cve_capitulo_fraccion")
    private CatCapituloFraccion cveCapituloFraccion;

    @Size(max = 1000)
    @Column(name = "nombre", length = 1000)
    private String nombre;

    @NotNull
    @Column(name = "fec_ini_vigencia", nullable = false)
    private Instant fecIniVigencia;

    @Column(name = "fec_fin_vigencia")
    private Instant fecFinVigencia;

    @NotNull
    @Column(name = "bln_activo", nullable = false)
    private Boolean blnActivo;


}