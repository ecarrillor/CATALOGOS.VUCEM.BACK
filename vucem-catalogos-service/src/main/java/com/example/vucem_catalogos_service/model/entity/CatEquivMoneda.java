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
@Table(name = "cat_equiv_moneda")
public class CatEquivMoneda {
    @Id
    @Column(name = "id_equiv_moneda", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cve_moneda_origen", nullable = false, referencedColumnName = "cve_moneda")
    private CatMoneda cveMonedaOrigen;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cve_moneda_destino", nullable = false, referencedColumnName = "cve_moneda")
    private CatMoneda cveMonedaDestino;

    @NotNull
    @Column(name = "valor_conversion", nullable = false, precision = 22, scale = 8)
    private BigDecimal valorConversion;

    @NotNull
    @Column(name = "fec_ini_vigencia", nullable = false)
    private Instant fecIniVigencia;

    @Column(name = "fec_fin_vigencia")
    private Instant fecFinVigencia;

    @NotNull
    @Column(name = "bln_activo", nullable = false)
    private Boolean blnActivo;


}