package com.example.vucem_catalogos_service.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Data
@Entity
@Table(name = "cat_dia_no_laborable")
public class CatDiaNoLaborable {
    @EmbeddedId
    private CatDiaNoLaborableId id;

    @MapsId("cveCalendario")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cve_calendario", nullable = false, referencedColumnName = "cve_calendario")
    private CatCalendario cveCalendario;

    @Size(max = 256)
    @Column(name = "descripcion", length = 256)
    private String descripcion;

    @NotNull
    @Column(name = "fec_ini_vigencia", nullable = false)
    private Instant fecIniVigencia;

    @Column(name = "fec_fin_vigencia")
    private Instant fecFinVigencia;

    @NotNull
    @Column(name = "bln_activo", nullable = false)
    private Boolean blnActivo;


}