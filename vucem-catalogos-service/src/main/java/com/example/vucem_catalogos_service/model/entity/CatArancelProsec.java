package com.example.vucem_catalogos_service.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "cat_arancel_prosec")
public class CatArancelProsec {
    @EmbeddedId
    private CatArancelProsecId id;

    @MapsId("cveFraccion")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cve_fraccion", nullable = false)
    private CatFraccionArancelaria cveFraccion;

    @MapsId("cveSectorProsec")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cve_sector_prosec", nullable = false)
    private CatSectorProsec cveSectorProsec;

    @NotNull
    @Column(name = "tasa", nullable = false)
    private Long tasa;

    @NotNull
    @Column(name = "fec_ini_vigencia", nullable = false)
    private LocalDate fecIniVigencia;

    @Column(name = "fec_fin_vigencia")
    private LocalDate fecFinVigencia;

    @NotNull
    @Column(name = "bln_activo", nullable = false)
    private Boolean blnActivo;


}