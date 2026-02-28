package com.example.vucem_catalogos_service.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "cat_fraccion_ttra_desc_prod")
public class CatFraccionTtraDescProd {
    @Id
    @Column(name = "id_fraccion_ttra_desc_prod", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_descripcion_prod")
    private CatDescripcionProd idDescripcionProd;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_fraccion_gob")
    private CatFraccionTtra idFraccionGob;

    @NotNull
    @Column(name = "fec_ini_vigencia", nullable = false)
    private LocalDate fecIniVigencia;

    @Column(name = "fec_fin_vigencia")
    private LocalDate fecFinVigencia;

    @NotNull
    @Column(name = "bln_activo", nullable = false)
    private Boolean blnActivo;


}