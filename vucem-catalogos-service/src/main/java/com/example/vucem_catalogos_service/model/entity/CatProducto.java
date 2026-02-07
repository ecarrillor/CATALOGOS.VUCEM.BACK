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
@Table(name = "cat_producto")
public class CatProducto {
    @Id
    @Size(max = 10)
    @Column(name = "cve_producto", nullable = false, length = 10)
    private String cveProducto;

    @Size(max = 3)
    @Column(name = "sigla", length = 3)
    private String sigla;

    @Size(max = 60)
    @Column(name = "nombre", length = 60)
    private String nombre;

    @Size(max = 120)
    @Column(name = "descripcion", length = 120)
    private String descripcion;

    @Column(name = "fec_captura")
    private Instant fecCaptura;

    @Column(name = "fec_fin_vigencia")
    private Instant fecFinVigencia;

    @NotNull
    @Column(name = "fec_ini_vigencia", nullable = false)
    private Instant fecIniVigencia;

    @NotNull
    @Column(name = "bln_activo", nullable = false)
    private Boolean blnActivo;


}