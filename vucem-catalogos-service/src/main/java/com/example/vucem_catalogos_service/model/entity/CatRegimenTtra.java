package com.example.vucem_catalogos_service.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.Instant;

@Data
@Entity
@Table(name = "cat_regimen_ttra")
public class CatRegimenTtra {
    @Id
    @Column(name = "id_regimen_ttra", nullable = false)
    private Short id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cve_regimen", referencedColumnName = "cve_regimen")
    private CatRegimen cveRegimen;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_tramite", referencedColumnName = "id_tipo_tramite")
    private CatTipoTramite idTipoTramite;

    @NotNull
    @Column(name = "fec_ini_vigencia", nullable = false)
    private Instant fecIniVigencia;

    @Column(name = "fec_fin_vigencia")
    private Instant fecFinVigencia;

    @NotNull
    @Column(name = "bln_activo", nullable = false)
    private Boolean blnActivo;


}
