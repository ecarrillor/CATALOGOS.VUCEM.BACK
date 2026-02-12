package com.example.vucem_catalogos_service.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@Entity
@Table(name = "cat_equivalencia_aelc")
public class CatEquivalenciaAelc {
    @EmbeddedId
    private CatEquivalenciaAelcId id;

    @Column(name = "valor", precision = 19, scale = 2)
    private BigDecimal valor;

    @Column(name = "fec_captura")
    private Instant fecCaptura;

    @Column(name = "fec_fin_vigencia")
    private Instant fecFinVigencia;

    @NotNull
    @Column(name = "bln_activo", nullable = false)
    private Boolean blnActivo;


}