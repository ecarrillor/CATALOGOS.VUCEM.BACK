package com.example.vucem_catalogos_service.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Data
@Entity
@Table(name = "cat_colonia")
public class CatColonia {
    @Id
    @Size(max = 12)
    @Column(name = "cve_colonia", nullable = false, length = 12)
    private String cveColonia;

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
    @Column(name = "sat_colony_cd", length = 10)
    private String satColonyCd;

    @NotNull
    @Column(name = "bln_activo", nullable = false)
    private Boolean blnActivo;


}