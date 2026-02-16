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
@Table(name = "vuc_dictamen_digi")
public class VucDictamenDigi {
    @Id
    @Column(name = "id_dictamen", nullable = false)
    private Long id;

    @Size(max = 30)
    @NotNull
    @Column(name = "num_folio_tramite", nullable = false, length = 30)
    private String numFolioTramite;

    @Size(max = 20)
    @Column(name = "ide_tipo_dictamen", length = 20)
    private String ideTipoDictamen;

    @Size(max = 20)
    @Column(name = "ide_est_dictamen", length = 20)
    private String ideEstDictamen;

    @Column(name = "fec_creacion")
    private Instant fecCreacion;

    @Column(name = "fec_emision")
    private Instant fecEmision;

    @Column(name = "fec_autorizacion")
    private Instant fecAutorizacion;

    @Column(name = "fec_verificacion")
    private Instant fecVerificacion;

    @Column(name = "texto_dictamen", length = Integer.MAX_VALUE)
    private String textoDictamen;

    @Column(name = "observacion", length = Integer.MAX_VALUE)
    private String observacion;

    @Column(name = "fec_observacion")
    private Instant fecObservacion;

    @Column(name = "fec_cita")
    private Instant fecCita;

    @Column(name = "justificacion", length = Integer.MAX_VALUE)
    private String justificacion;

    @Size(max = 20)
    @Column(name = "ide_sent_dictamen", length = 20)
    private String ideSentDictamen;

    @Column(name = "plazo_anios")
    private Short plazoAnios;

    @Size(max = 20)
    @Column(name = "ide_plazo_meses", length = 20)
    private String idePlazoMeses;

    @Column(name = "fec_ini_vigencia")
    private Instant fecIniVigencia;

    @Column(name = "fec_fin_vigencia")
    private Instant fecFinVigencia;

    @Column(name = "bln_aplica_ley_aduanera_144a")
    private Short blnAplicaLeyAduanera144a;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_dictamen")
    private CatTipoDictamen idTipoDictamen;

    @Size(max = 8)
    @Column(name = "plazo", length = 8)
    private String plazo;

    @Size(max = 20)
    @Column(name = "num_folio_externo", length = 20)
    private String numFolioExterno;

    @Size(max = 20)
    @Column(name = "num_folio_alterno", length = 20)
    private String numFolioAlterno;


}