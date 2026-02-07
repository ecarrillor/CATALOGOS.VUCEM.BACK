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
@Table(name = "cat_tipo_empresa_recif")
public class CatTipoEmpresaRecif {
    @Id
    @Size(max = 2)
    @Column(name = "cve_tipo_empresa_recif", nullable = false, length = 2)
    private String cveTipoEmpresaRecif;

    @Size(max = 50)
    @Column(name = "descripcion", length = 50)
    private String descripcion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cve_tipo_empresa_recif_r", referencedColumnName = "cve_tipo_empresa_recif")
    private CatTipoEmpresaRecif cveTipoEmpresaRecifR;

    @NotNull
    @Column(name = "fec_ini_vigencia", nullable = false)
    private Instant fecIniVigencia;

    @Column(name = "fec_fin_vigencia")
    private Instant fecFinVigencia;

    @NotNull
    @Column(name = "bln_activo", nullable = false)
    private Boolean blnActivo;


}