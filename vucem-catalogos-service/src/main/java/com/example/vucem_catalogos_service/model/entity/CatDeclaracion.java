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
@Table(name = "cat_declaracion")
public class CatDeclaracion {
    @Id
    @Size(max = 20)
    @Column(name = "cve_declaracion", nullable = false, length = 20)
    private String cveDeclaracion;

    @Size(max = 2000)
    @Column(name = "desc_declaracion", length = 2000)
    private String descDeclaracion;

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

    @Size(max = 20)
    @Column(name = "cve_referencia", length = 20)
    private String cveReferencia;


}
