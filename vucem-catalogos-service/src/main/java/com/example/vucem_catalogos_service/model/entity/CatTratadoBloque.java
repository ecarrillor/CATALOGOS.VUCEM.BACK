package com.example.vucem_catalogos_service.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "cat_tratado_bloque")
public class CatTratadoBloque {
    @EmbeddedId
    private CatTratadoBloqueId id;

    @MapsId("idTratadoAcuerdo")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_tratado_acuerdo", nullable = false, referencedColumnName = "id_tratado_acuerdo")
    private CatTratadoAcuerdo idTratadoAcuerdo;

    @MapsId("idBloque")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_bloque", nullable = false, referencedColumnName = "id_tratado_acuerdo")
    private CatTratadoAcuerdo idBloque;

    @NotNull
    @Column(name = "fec_ini_vigencia", nullable = false)
    private Instant fecIniVigencia;

    @Column(name = "fec_fin_vigencia")
    private Instant fecFinVigencia;

    @NotNull
    @Column(name = "bln_activo", nullable = false)
    private Boolean blnActivo;


}