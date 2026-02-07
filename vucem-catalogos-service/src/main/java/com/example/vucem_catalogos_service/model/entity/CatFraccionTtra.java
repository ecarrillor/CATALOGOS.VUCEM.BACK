package com.example.vucem_catalogos_service.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "cat_fraccion_ttra")
public class CatFraccionTtra {
    @Id
    @Column(name = "id_fraccion_gob", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cve_fraccion", referencedColumnName = "cve_fraccion")
    private CatFraccionArancelaria cveFraccion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_tramite", referencedColumnName = "id_tipo_tramite")
    private CatTipoTramite idTipoTramite;

    @NotNull
    @Column(name = "fec_ini_vigencia", nullable = false)
    private Instant fecIniVigencia;

    @Column(name = "fec_fin_vigencia")
    private Instant fecFinVigencia;

    @NotNull
    @Column(name = "bln_activo", nullable = false)
    private Boolean blnActivo;

    @Size(max = 2000)
    @Column(name = "desc_fraccion_alt", length = 2000)
    private String descFraccionAlt;

    @Size(max = 20)
    @Column(name = "ide_clasif_partida", length = 20)
    private String ideClasifPartida;

    @Column(name = "bln_fraccion_controlada")
    private Short blnFraccionControlada;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_categoria_textil", referencedColumnName = "id_categoria_textil")
    private CatCategoriaTextil idCategoriaTextil;

    @Column(name = "factor_conversion", precision = 19, scale = 2)
    private BigDecimal factorConversion;

    @Column(name = "valor_equivalencia", precision = 19, scale = 2)
    private BigDecimal valorEquivalencia;

    @Size(max = 10)
    @Column(name = "cve_unidad_medida", length = 10)
    private String cveUnidadMedida;

    @Size(max = 2000)
    @Column(name = "regla_aplicable", length = 2000)
    private String reglaAplicable;


}