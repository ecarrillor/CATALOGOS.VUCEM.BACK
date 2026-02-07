package com.example.vucem_catalogos_service.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "cat_pais_tratado_acuerdo")
public class CatPaisTratadoAcuerdo {
    @EmbeddedId
    private CatPaisTratadoAcuerdoId id;

    @MapsId("cvePais")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cve_pais", nullable = false, referencedColumnName = "cve_pais")
    private CatPai cvePais;

    @Column(name = "fec_captura")
    private Instant fecCaptura;

    @NotNull
    @Column(name = "fec_ini_vigencia", nullable = false)
    private Instant fecIniVigencia;

    @NotNull
    @Column(name = "bln_activo", nullable = false)
    private Boolean blnActivo;

    @Column(name = "bln_envio_electronico")
    private Short blnEnvioElectronico;


}