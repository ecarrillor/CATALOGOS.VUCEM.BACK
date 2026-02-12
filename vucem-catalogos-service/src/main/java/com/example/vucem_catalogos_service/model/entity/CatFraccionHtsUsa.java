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

import java.time.Instant;

@Data
@Entity
@Table(name = "cat_fraccion_hts_usa")
public class CatFraccionHtsUsa {
    @Id
    @Column(name = "id_fraccion_hts_usa", nullable = false)
    private Long id;

    @Size(max = 10)
    @NotNull
    @Column(name = "cve_fraccion_hts_usa", nullable = false, length = 10)
    private String cveFraccionHtsUsa;

    @Size(max = 1000)
    @Column(name = "descripcion", length = 1000)
    private String descripcion;

    @Column(name = "fec_captura")
    private Instant fecCaptura;

    @NotNull
    @Column(name = "fec_ini_vigencia", nullable = false)
    private Instant fecIniVigencia;

    @Column(name = "fec_fin_vigencia")
    private Instant fecFinVigencia;

    @Size(max = 20)
    @Column(name = "ide_tipo_bien_fraccion", length = 20)
    private String ideTipoBienFraccion;

    @Column(name = "bln_exenta")
    private Short blnExenta;

    @NotNull
    @Column(name = "bln_activo", nullable = false)
    private Boolean blnActivo;


}