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
@Table(name = "cat_subpartida_fraccion")
public class CatSubpartidaFraccion {
    @EmbeddedId
    private CatSubpartidaFraccionId id;

    @MapsId("id")
    @JoinColumns({
            @JoinColumn(name = "cve_capitulo_fraccion",
                    referencedColumnName = "cve_capitulo_fraccion",
                    nullable = false),
            @JoinColumn(name = "cve_partida_fraccion",
                    referencedColumnName = "cve_partida_fraccion",
                    nullable = false)})
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private CatPartidaFraccion catPartidaFraccion;

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
