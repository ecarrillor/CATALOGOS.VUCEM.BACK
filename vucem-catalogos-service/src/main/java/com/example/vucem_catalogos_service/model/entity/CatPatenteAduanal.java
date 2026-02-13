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
@Table(name = "cat_patente_aduanal")
public class CatPatenteAduanal {
    @Id
    @Size(max = 4)
    @Column(name = "cve_patente_aduanal", nullable = false, length = 4)
    private String cvePatenteAduanal;

    @Size(max = 13)
    @Column(name = "rfc", length = 13)
    private String rfc;

    @Column(name = "fec_fin_vigencia")
    private Instant fecFinVigencia;

    @NotNull
    @Column(name = "fec_ini_vigencia", nullable = false)
    private Instant fecIniVigencia;

    @NotNull
    @Column(name = "bln_activo", nullable = false)
    private Boolean blnActivo;

    @Size(max = 20)
    @Column(name = "ide_est_patente_aut", length = 20)
    private String ideEstPatenteAut;


}
