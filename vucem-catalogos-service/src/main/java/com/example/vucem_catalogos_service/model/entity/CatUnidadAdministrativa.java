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
@Table(name = "cat_unidad_administrativa")
public class CatUnidadAdministrativa {
    @Id
    @Size(max = 10)
    @Column(name = "cve_unidad_administrativa", nullable = false, length = 10)
    private String cveUnidadAdministrativa;

    @Size(max = 20)
    @Column(name = "ide_tipo_unidad_administrativa", length = 20)
    private String ideTipoUnidadAdministrativa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cve_unidad_administrativa_r", referencedColumnName = "cve_unidad_administrativa")
    private CatUnidadAdministrativa cveUnidadAdministrativaR;

    @Column(name = "nivel")
    private Short nivel;

    @Size(max = 20)
    @Column(name = "acronimo", length = 20)
    private String acronimo;

    @Size(max = 120)
    @Column(name = "nombre", length = 120)
    private String nombre;

    @Size(max = 120)
    @Column(name = "descripcion", length = 120)
    private String descripcion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cve_entidad", referencedColumnName = "cve_entidad")
    private CatEntidad cveEntidad;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_dependencia", nullable = false, referencedColumnName = "id_dependencia")
    private CatDependencia idDependencia;

    @Column(name = "bln_fronteriza")
    private Short blnFronteriza;

    @NotNull
    @Column(name = "fec_ini_vigencia", nullable = false)
    private Instant fecIniVigencia;

    @Column(name = "fec_fin_vigencia")
    private Instant fecFinVigencia;

    @NotNull
    @Column(name = "bln_activo", nullable = false)
    private Boolean blnActivo;


}