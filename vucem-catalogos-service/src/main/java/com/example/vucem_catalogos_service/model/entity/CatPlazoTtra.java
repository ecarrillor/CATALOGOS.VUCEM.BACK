package com.example.vucem_catalogos_service.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "cat_plazo_ttra")
public class CatPlazoTtra {

    @EmbeddedId
    private CatPlazoTtraId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idTipoTramite")
    @JoinColumn(name = "id_tipo_tramite")
    private CatTipoTramite tipoTramite;

    @Column(name = "fec_ini_vigencia")
    private LocalDate fecIniVigencia;

    @Column(name = "fec_fin_vigencia")
    private LocalDate fecFinVigencia;

    @Column(name = "bln_activo")
    private Boolean blnActivo;


}
