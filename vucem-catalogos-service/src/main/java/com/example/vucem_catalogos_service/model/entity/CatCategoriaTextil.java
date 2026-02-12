package com.example.vucem_catalogos_service.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
@Table(name = "cat_categoria_textil")
public class CatCategoriaTextil {
    @Id
    @Column(name = "id_categoria_textil", nullable = false)
    private Integer id;

    @Size(max = 120)
    @Column(name = "descripcion", length = 120)
    private String descripcion;

    @Size(max = 20)
    @Column(name = "cod_categoria_textil", length = 20)
    private String codCategoriaTextil;

    @Column(name = "bln_npa")
    private Short blnNpa;

    @Column(name = "fact_conversion", precision = 5, scale = 3)
    private BigDecimal factConversion;

    @Column(name = "fec_captura")
    private Instant fecCaptura;

    @NotNull
    @Column(name = "fec_ini_vigencia", nullable = false)
    private Instant fecIniVigencia;

    @Column(name = "fec_fin_vigencia")
    private Instant fecFinVigencia;

    @Column(name = "fec_actualizacion")
    private Instant fecActualizacion;

    @NotNull
    @Column(name = "bln_activo", nullable = false)
    private Boolean blnActivo;


}