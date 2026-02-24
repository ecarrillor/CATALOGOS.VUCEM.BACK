package com.example.vucem_catalogos_service.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "cat_equivalencia_aelc")
public class CatEquivalenciaAelc {
    @EmbeddedId
    private CatEquivalenciaAelcId id;

    @Column(name = "valor", precision = 19, scale = 2)
    private BigDecimal valor;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("cveMoneda")
    @JoinColumn(name = "cve_moneda")
    private CatMoneda moneda;

    @Column(name = "fec_captura")
    private LocalDate fecCaptura;

    @Column(name = "fec_fin_vigencia")
    private LocalDate fecFinVigencia;

    @NotNull
    @Column(name = "bln_activo", nullable = false)
    private Boolean blnActivo;


}
