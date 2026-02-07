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
@Table(name = "cat_empresa_recif")
public class CatEmpresaRecif {
    @Id
    @Size(max = 21)
    @Column(name = "recif", nullable = false, length = 21)
    private String recif;

    @Size(max = 13)
    @Column(name = "rfc", length = 13)
    private String rfc;

    @Size(max = 255)
    @Column(name = "razon_social")
    private String razonSocial;

    @NotNull
    @Column(name = "bln_activo", nullable = false)
    private Boolean blnActivo;

    @NotNull
    @Column(name = "fec_ini_vigencia", nullable = false)
    private Instant fecIniVigencia;

    @Column(name = "fec_fin_vigencia")
    private Instant fecFinVigencia;


}