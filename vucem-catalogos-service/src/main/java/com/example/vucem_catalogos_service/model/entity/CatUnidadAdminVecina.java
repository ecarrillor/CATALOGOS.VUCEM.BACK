package com.example.vucem_catalogos_service.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "cat_unidad_admin_vecina")
public class CatUnidadAdminVecina {
    @EmbeddedId
    private CatUnidadAdminVecinaId id;

    @MapsId("cveUnidadAdministrativa")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cve_unidad_administrativa", nullable = false, referencedColumnName = "cve_unidad_administrativa")
    private CatUnidadAdministrativa cveUnidadAdministrativa;

    @MapsId("cveEntidad")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cve_entidad", nullable = false, referencedColumnName = "cve_entidad")
    private CatEntidad cveEntidad;

    @NotNull
    @Column(name = "fec_ini_vigencia", nullable = false)
    private Instant fecIniVigencia;

    @Column(name = "fec_fin_vigencia")
    private Instant fecFinVigencia;

    @NotNull
    @Column(name = "bln_activo", nullable = false)
    private Boolean blnActivo;


}