package com.example.vucem_catalogos_service.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@Entity
@Table(name = "cat_monto_exportacion")
public class CatMontoExportacion {
    @EmbeddedId
    private CatMontoExportacionId id;

    @Size(max = 254)
    @Column(name = "razon_social", length = 254)
    private String razonSocial;

    @Column(name = "monto", precision = 14, scale = 2)
    private BigDecimal monto;

    @Column(name = "fec_modificacion")
    private Instant fecModificacion;

    @NotNull
    @Column(name = "fec_ini_vigencia", nullable = false)
    private Instant fecIniVigencia;

    @Column(name = "fec_fin_vigencia")
    private Instant fecFinVigencia;

    @NotNull
    @Column(name = "bln_activo", nullable = false)
    private Boolean blnActivo;


}