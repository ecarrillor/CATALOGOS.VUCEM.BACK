package com.example.vucem_catalogos_service.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "cat_control_referencia")
public class CatControlReferencia {
    @Id
    @Column(name = "id_control_referencia", nullable = false)
    private Integer id;

    @Size(max = 20)
    @Column(name = "ide_tipo_presentacion", length = 20)
    private String ideTipoPresentacion;

    @Size(max = 20)
    @Column(name = "ide_subtipo_presentacion", length = 20)
    private String ideSubtipoPresentacion;

    @NotNull
    @Column(name = "minimo", nullable = false)
    private Short minimo;

    @NotNull
    @Column(name = "maximo", nullable = false)
    private Short maximo;

    @NotNull
    @Column(name = "cantidad_presentacion", nullable = false, precision = 10, scale = 3)
    private BigDecimal cantidadPresentacion;

    @Column(name = "tamanio_muestra", precision = 10, scale = 3)
    private BigDecimal tamanioMuestra;

    @NotNull
    @Column(name = "fec_ini_vigencia", nullable = false)
    private Instant fecIniVigencia;

    @Column(name = "fec_fin_vigencia")
    private Instant fecFinVigencia;

    @Column(name = "bln_activo")
    private Boolean blnActivo;


}