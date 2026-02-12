package com.example.vucem_catalogos_service.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.Instant;

@Data
@Entity
@Table(name = "cat_aga")
public class CatAga {
    @Id
    @Size(max = 30)
    @Column(name = "cve_parametro", nullable = false, length = 30)
    private String cveParametro;

    @Size(max = 200)
    @NotNull
    @Column(name = "descripcion", nullable = false, length = 200)
    private String descripcion;

    @Size(max = 4000)
    @NotNull
    @Column(name = "valor", nullable = false, length = 4000)
    private String valor;

    @NotNull
    @Column(name = "fec_ini_vigencia", nullable = false)
    private Instant fecIniVigencia;

    @Column(name = "fec_fin_vigencia")
    private Instant fecFinVigencia;

    @NotNull
    @Column(name = "bln_activo")
    private Boolean blnActivo = true;


}