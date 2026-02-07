package com.example.vucem_catalogos_service.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "cat_pais_restriccion_ttra")
public class CatPaisRestriccionTtra {
    @Id
    @Column(name = "id_pais_restriccion_ttra", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_tramite", referencedColumnName = "id_tipo_tramite")
    private CatTipoTramite idTipoTramite;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cve_pais", referencedColumnName = "cve_pais")
    private CatPai cvePais;

    @Size(max = 20)
    @Column(name = "ide_tipo_restriccion_pais_ttra", length = 20)
    private String ideTipoRestriccionPaisTtra;

    @NotNull
    @Column(name = "fec_ini_vigencia", nullable = false)
    private Instant fecIniVigencia;

    @Column(name = "fec_fin_vigencia")
    private Instant fecFinVigencia;

    @NotNull
    @Column(name = "bln_activo", nullable = false)
    private Boolean blnActivo;


}