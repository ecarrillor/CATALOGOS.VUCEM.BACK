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
@Table(name = "cat_unidad_admin_aduana")
public class CatUnidadAdminAduana {
    @EmbeddedId
    private CatUnidadAdminAduanaId id;

    @MapsId("cveUnidadAdministrativa")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cve_unidad_administrativa", nullable = false)
    private CatUnidadAdministrativa cveUnidadAdministrativa;

    @MapsId("cveAduana")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cve_aduana", nullable = false)
    private CatAduana cveAduana;

    @NotNull
    @Column(name = "fec_ini_vigencia", nullable = false)
    private LocalDate fecIniVigencia;

    @Column(name = "fec_fin_vigencia")
    private LocalDate fecFinVigencia;

    @NotNull
    @Column(name = "bln_activo", nullable = false)
    private Boolean blnActivo;


}