package com.example.vucem_catalogos_service.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "cat_tarifa")
public class CatTarifa {
    @EmbeddedId
    private CatTarifaId id;

    @MapsId("idTipoTramite")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_tipo_tramite", nullable = false, referencedColumnName = "id_tipo_tramite")
    private CatTipoTramite idTipoTramite;

    @NotNull
    @Column(name = "fec_fin_vigencia", nullable = false)
    private Instant fecFinVigencia;

    @Column(name = "tarifa", precision = 19, scale = 2)
    private BigDecimal tarifa;

    @NotNull
    @Column(name = "bln_activo", nullable = false)
    private Boolean blnActivo;


}
