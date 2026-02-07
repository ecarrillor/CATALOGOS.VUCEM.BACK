package com.example.vucem_catalogos_service.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "cat_restric_desc_prod")
public class CatRestricDescProd {
    @Id
    @Column(name = "id_restric_desc_prod", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_restriccion_ttra", referencedColumnName = "id_restriccion_ttra")
    private CatRestriccionTtra idRestriccionTtra;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_descripcion_prod", referencedColumnName = "id_descripcion_prod")
    private CatDescripcionProd idDescripcionProd;

    @NotNull
    @Column(name = "fec_ini_vigencia", nullable = false)
    private Instant fecIniVigencia;

    @Column(name = "fec_fin_vigencia")
    private Instant fecFinVigencia;

    @NotNull
    @Column(name = "bln_activo", nullable = false)
    private Boolean blnActivo;


}