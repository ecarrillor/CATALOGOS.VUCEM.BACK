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
@Table(name = "cat_catalogo_d")
public class CatCatalogoD {
    @Id
    @Size(max = 10)
    @Column(name = "cve_catalogo", nullable = false, length = 10)
    private String cveCatalogo;

    @Size(max = 100)
    @Column(name = "codigo_generico1", length = 100)
    private String codigoGenerico1;

    @Size(max = 100)
    @Column(name = "codigo_generico2", length = 100)
    private String codigoGenerico2;

    @Size(max = 1000)
    @Column(name = "desc_generica1", length = 1000)
    private String descGenerica1;

    @Size(max = 300)
    @Column(name = "desc_generica2", length = 300)
    private String descGenerica2;

    @Column(name = "num_generico1")
    private Long numGenerico1;

    @Column(name = "num_generico2")
    private Long numGenerico2;

    @NotNull
    @Column(name = "fec_ini_vigencia", nullable = false)
    private Instant fecIniVigencia;

    @Column(name = "fec_fin_vigencia")
    private Instant fecFinVigencia;

    @NotNull
    @Column(name = "bln_activo", nullable = false)
    private Short blnActivo;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cve_catalogo_h", nullable = false)
    private CatCatalogoH cveCatalogoH;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cve_catalogo_r")
    private CatCatalogoD cveCatalogoR;


}