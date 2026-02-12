package com.example.vucem_catalogos_service.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.Instant;

@Data
@Entity
@Table(name = "cat_isotopo_fraccion")
public class CatIsotopoFraccion {
    @Id
    @Column(name = "id_isotopo", nullable = false)
    private Short id;

    @Size(max = 120)
    @Column(name = "nombre", length = 120)
    private String nombre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cve_fraccion", referencedColumnName = "cve_fraccion")
    private CatFraccionArancelaria cveFraccion;

    @Column(name = "fec_ini_vigencia")
    private Instant fecIniVigencia;

    @Column(name = "fec_fin_vigencia")
    private Instant fecFinVigencia;

    @Column(name = "bln_activo")
    private Boolean blnActivo;


}