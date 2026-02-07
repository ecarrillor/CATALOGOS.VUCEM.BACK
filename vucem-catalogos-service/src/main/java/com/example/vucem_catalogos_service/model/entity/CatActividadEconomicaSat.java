package com.example.vucem_catalogos_service.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Data
@Entity
@Table(name = "cat_actividad_economica_sat")
public class CatActividadEconomicaSat {
    @Id
    @Column(name = "id_actividad_economica_sat", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_actividad_economica_r", referencedColumnName = "id_actividad_economica_sat")
    private CatActividadEconomicaSat idActividadEconomicaR;

    @Size(max = 2000)
    @Column(name = "descripcion", length = 2000)
    private String descripcion;

    @Size(max = 2000)
    @Column(name = "desc_scian", length = 2000)
    private String descScian;

    @Size(max = 2000)
    @Column(name = "desc_notas", length = 2000)
    private String descNotas;

    @NotNull
    @Column(name = "fec_ini_vigencia", nullable = false)
    private Instant fecIniVigencia;

    @Column(name = "fec_fin_vigencia")
    private Instant fecFinVigencia;

    @NotNull
    @Column(name = "fec_captura", nullable = false)
    private Instant fecCaptura;

    @Column(name = "fec_actualizacion")
    private Instant fecActualizacion;

    @Size(max = 2)
    @Column(name = "cve_tipo_industria_idc", length = 2)
    private String cveTipoIndustriaIdc;

    @NotNull
    @Column(name = "bln_activo", nullable = false)
    private Boolean blnActivo;


}