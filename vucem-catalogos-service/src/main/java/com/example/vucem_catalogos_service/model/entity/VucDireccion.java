package com.example.vucem_catalogos_service.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "vuc_direccion")
public class VucDireccion {
    @Id
    @Column(name = "id_direccion", nullable = false)
    private Long id;

    @Size(max = 100)
    @Column(name = "calle", length = 100)
    private String calle;

    @Size(max = 55)
    @Column(name = "num_exterior", length = 55)
    private String numExterior;

    @Size(max = 55)
    @Column(name = "num_interior", length = 55)
    private String numInterior;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cve_pais", referencedColumnName = "cve_pais")
    private CatPais cvePais;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cve_deleg_mun", referencedColumnName = "cve_deleg_mun")
    private CatDelegMun cveDelegMun;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cve_entidad", referencedColumnName = "cve_entidad")
    private CatEntidad cveEntidad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cve_colonia", referencedColumnName = "cve_colonia")
    private CatColonia cveColonia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cve_localidad", referencedColumnName = "cve_localidad")
    private CatLocalidad cveLocalidad;

    @Size(max = 12)
    @Column(name = "cp", length = 12)
    private String cp;

    @Size(max = 255)
    @Column(name = "estado_mun_col_extr")
    private String estadoMunColExtr;

    @Size(max = 120)
    @Column(name = "ciudad", length = 120)
    private String ciudad;

    @Size(max = 30)
    @Column(name = "telefono", length = 30)
    private String telefono;

    @Size(max = 30)
    @Column(name = "fax", length = 30)
    private String fax;

    @Size(max = 120)
    @Column(name = "municipio", length = 120)
    private String municipio;

    @Size(max = 120)
    @Column(name = "colonia", length = 120)
    private String colonia;

    @Size(max = 255)
    @Column(name = "telefonos")
    private String telefonos;

    @Size(max = 30)
    @Column(name = "desc_carga_ini", length = 30)
    private String descCargaIni;

    @Size(max = 30)
    @Column(name = "cve_generica", length = 30)
    private String cveGenerica;


}
