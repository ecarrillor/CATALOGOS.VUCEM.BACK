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
@Table(name = "cat_restriccion_ttra")
public class CatRestriccionTtra {
    @Id
    @Column(name = "id_restriccion_ttra", nullable = false)
    private Short id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_tramite", referencedColumnName = "id_tipo_tramite")
    private CatTipoTramite idTipoTramite;

    @Size(max = 250)
    @Column(name = "desc_restriccion", length = 250)
    private String descRestriccion;

    @Size(max = 1000)
    @Column(name = "desc_contenido_restriccion", length = 1000)
    private String descContenidoRestriccion;

    @NotNull
    @Column(name = "fec_ini_vigencia", nullable = false)
    private Instant fecIniVigencia;

    @Column(name = "fec_fin_vigencia")
    private Instant fecFinVigencia;

    @NotNull
    @Column(name = "bln_activo", nullable = false)
    private Boolean blnActivo;

    @Size(max = 20)
    @Column(name = "ide_sent_dictamen", length = 20)
    private String ideSentDictamen;

    @Size(max = 20)
    @Column(name = "ide_tipo_restriccion_ttra", length = 20)
    private String ideTipoRestriccionTtra;

    @Size(max = 20)
    @Column(name = "ide_motivo_rechazo_dict", length = 20)
    private String ideMotivoRechazoDict;


}