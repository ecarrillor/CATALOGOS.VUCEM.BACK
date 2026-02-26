package com.example.vucem_catalogos_service.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "cat_catalogo_h")
public class CatCatalogoH {
    @Id
    @Size(max = 6)
    @Column(name = "cve_catalogo_h", nullable = false, length = 6)
    private String cveCatalogoH;

    @Size(max = 300)
    @NotNull
    @Column(name = "nom_catalogo", nullable = false, length = 300)
    private String nomCatalogo;

    @NotNull
    @Column(name = "fec_ini_vigencia", nullable = false)
    private Instant fecIniVigencia;

    @Column(name = "fec_fin_vigencia")
    private Instant fecFinVigencia;

    @NotNull
    @Column(name = "bln_activo", nullable = false)
    private Short blnActivo;


}