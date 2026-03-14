package com.example.vucem_catalogos_service.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "cat_subdivision_fraccion")
public class CatSubdivisionFraccion {
    @Id
    @Size(max = 10)
    @Column(name = "cve_subdivision", nullable = false, length = 10)
    private String cveSubdivision;

    @Size(max = 2)
    @NotNull
    @Column(name = "cod_subdivision", nullable = false, length = 2)
    private String codSubdivision;

    @Size(max = 500)
    @NotNull
    @Column(name = "descripcion", nullable = false, length = 500)
    private String descripcion;

    @NotNull
    @Column(name = "precio_estimado", nullable = false, precision = 19, scale = 4)
    private BigDecimal precioEstimado;

    @NotNull
    @Column(name = "fec_ini_vigencia", nullable = false)
    private LocalDate fecIniVigencia;

    @Column(name = "fec_fin_vigencia")
    private LocalDate fecFinVigencia;

    @NotNull
    @Column(name = "bln_activo", nullable = false)
    private Boolean blnActivo;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cve_fraccion", nullable = false)
    private CatFraccionArancelaria cveFraccion;


}
