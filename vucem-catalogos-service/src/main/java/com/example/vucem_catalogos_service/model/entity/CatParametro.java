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
@Table(name = "cat_parametro")
public class CatParametro {
    @Id
    @Size(max = 30)
    @Column(name = "cve_parametro", nullable = false, length = 30)
    private String cveParametro;

    @Size(max = 200)
    @Column(name = "descripcion", length = 200)
    private String descripcion;

    @Size(max = 2000)
    @Column(name = "valor", length = 2000)
    private String valor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_dependencia", referencedColumnName = "id_dependencia")
    private CatDependencia idDependencia;

    @NotNull
    @Column(name = "fec_ini_vigencia", nullable = false)
    private Instant fecIniVigencia;

    @Column(name = "fec_fin_vigencia")
    private Instant fecFinVigencia;

    @NotNull
    @Column(name = "bln_activo", nullable = false)
    private Boolean blnActivo;


}