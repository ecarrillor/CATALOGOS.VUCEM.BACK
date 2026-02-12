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
@Table(name = "cat_localidad")
public class CatLocalidad {
    @Id
    @Size(max = 12)
    @Column(name = "cve_localidad", nullable = false, length = 12)
    private String cveLocalidad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cve_deleg_mun", referencedColumnName = "cve_deleg_mun")
    private CatDelegMun cveDelegMun;

    @Size(max = 120)
    @Column(name = "nombre", length = 120)
    private String nombre;

    @Column(name = "fec_captura")
    private Instant fecCaptura;

    @NotNull
    @Column(name = "fec_ini_vigencia", nullable = false)
    private Instant fecIniVigencia;

    @Column(name = "fec_fin_vigencia")
    private Instant fecFinVigencia;

    @Size(max = 12)
    @Column(name = "cp", length = 12)
    private String cp;

    @Size(max = 10)
    @Column(name = "sat_town_code", length = 10)
    private String satTownCode;

    @NotNull
    @Column(name = "bln_activo", nullable = false)
    private Boolean blnActivo;


}