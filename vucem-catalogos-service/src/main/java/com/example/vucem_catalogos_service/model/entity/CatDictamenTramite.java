package com.example.vucem_catalogos_service.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "cat_dictamen_tramite")
public class CatDictamenTramite {
    @EmbeddedId
    private CatDictamenTramiteId id;

    @NotNull
    @Column(name = "fec_ini_vigencia", nullable = false)
    private LocalDate fecIniVigencia;

    @Column(name = "fec_fin_vigencia")
    private LocalDate fecFinVigencia;

    @NotNull
    @Column(name = "bln_activo", nullable = false)
    private Boolean blnActivo;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idTipoTramite")
    @JoinColumn(name = "id_tipo_tramite")
    private CatTipoTramite tipoTramite;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idTipoDictamen")
    @JoinColumn(name = "id_tipo_dictamen")
    private CatTipoDictamen tipoDictamen;


}
