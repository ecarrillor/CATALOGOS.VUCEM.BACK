package com.example.vucem_catalogos_service.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "cat_categoria_textil")
public class CatCategoriaTextil {
    @Id
    @Column(name = "id_categoria_textil", nullable = false)
    private Long id;

    @Size(max = 120)
    @Column(name = "descripcion", length = 120)
    private String descripcion;

    @Size(max = 20)
    @Column(name = "cod_categoria_textil", length = 20)
    private String codCategoriaTextil;

    @Column(name = "bln_npa")
    private Boolean blnNpa;

    @Column(name = "fact_conversion")
    private Double factConversion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cve_unidad_medida")
    private CatUnidadMedida cveUnidadMedida;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cve_unidad_medida_equivalente")
    private CatUnidadMedida cveUnidadMedidaEquivalente;

    @Column(name = "fec_captura")
    private LocalDate fecCaptura;

    @NotNull
    @Column(name = "fec_ini_vigencia", nullable = false)
    private LocalDate fecIniVigencia;

    @Column(name = "fec_fin_vigencia")
    private LocalDate fecFinVigencia;

    @Column(name = "fec_actualizacion")
    private LocalDate fecActualizacion;

    @NotNull
    @Column(name = "bln_activo", nullable = false)
    private Boolean blnActivo;


}