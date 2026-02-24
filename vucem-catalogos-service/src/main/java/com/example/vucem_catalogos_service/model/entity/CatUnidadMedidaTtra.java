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
@Table(name = "cat_unidad_medida_ttra")
public class CatUnidadMedidaTtra {
    @Id
    @Column(name = "id_unidad_medida_ttra", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cve_unidad_medida", nullable = false)
    private CatUnidadMedida cveUnidadMedida;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_tipo_tramite", nullable = false)
    private CatTipoTramite idTipoTramite;

    @NotNull
    @Column(name = "fec_ini_vigencia", nullable = false)
    private LocalDate fecIniVigencia;

    @Column(name = "fec_fin_vigencia")
    private LocalDate fecFinVigencia;

    @NotNull
    @Column(name = "bln_activo", nullable = false)
    private Short blnActivo;


}