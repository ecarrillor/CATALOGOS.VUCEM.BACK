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
@Table(name = "cat_scian")
public class CatScian {
    @Id
    @Size(max = 6)
    @Column(name = "cve_scian", nullable = false, length = 6)
    private String cveScian;

    @Size(max = 250)
    @Column(name = "desc_scian", length = 250)
    private String descScian;

    @NotNull
    @Column(name = "fec_ini_vigencia", nullable = false)
    private Instant fecIniVigencia;

    @Column(name = "fec_fin_vigencia")
    private Instant fecFinVigencia;

    @NotNull
    @Column(name = "bln_activo", nullable = false)
    private Boolean blnActivo;

    @Size(max = 6)
    @Column(name = "giro", length = 6)
    private String giro;


}
