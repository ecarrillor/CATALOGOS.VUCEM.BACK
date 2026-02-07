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
@Table(name = "cat_justificacion_ttra")
public class CatJustificacionTtra {
    @Id
    @Column(name = "id_justificacion_ttra", nullable = false)
    private Short id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_tramite", referencedColumnName = "id_tipo_tramite")
    private CatTipoTramite idTipoTramite;

    @Size(max = 250)
    @Column(name = "desc_justificacion", length = 250)
    private String descJustificacion;

    @Size(max = 1000)
    @Column(name = "desc_contenido_justificacion", length = 1000)
    private String descContenidoJustificacion;

    @NotNull
    @Column(name = "fec_ini_vigencia", nullable = false)
    private Instant fecIniVigencia;

    @Column(name = "fec_fin_vigencia")
    private Instant fecFinVigencia;

    @NotNull
    @Column(name = "bln_activo", nullable = false)
    private Boolean blnActivo;


}