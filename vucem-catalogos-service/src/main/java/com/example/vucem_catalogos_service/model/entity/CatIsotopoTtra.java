package com.example.vucem_catalogos_service.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "cat_isotopo_ttra")
public class CatIsotopoTtra {
    @EmbeddedId
    private CatIsotopoTtraId id;

    @MapsId("idIsotopo")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_isotopo", nullable = false, referencedColumnName = "id_isotopo")
    private CatIsotopoFraccion idIsotopo;

    @MapsId("idTipoTramite")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_tipo_tramite", nullable = false, referencedColumnName = "id_tipo_tramite")
    private CatTipoTramite idTipoTramite;

    @Column(name = "fec_ini_vigencia")
    private Instant fecIniVigencia;

    @Column(name = "fec_fin_vigencia")
    private Instant fecFinVigencia;

    @Column(name = "bln_activo")
    private Boolean blnActivo;


}