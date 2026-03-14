package com.example.vucem_catalogos_service.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "cat_catalogo_d_tr")
public class CatCatalogoDTr {
    @EmbeddedId
    private CatCatalogoDTrId id;

    @MapsId("cveCatalogo")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cve_catalogo", nullable = false)
    private CatCatalogoD cveCatalogo;

    @MapsId("cveLenguaje")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cve_lenguaje", nullable = false)
    private CatLenguaje cveLenguaje;

    @Size(max = 2000)
    @NotNull
    @Column(name = "descripcion", nullable = false, length = 2000)
    private String descripcion;

    @NotNull
    @Column(name = "fec_ini_vigencia", nullable = false)
    private LocalDate fecIniVigencia;

    @Column(name = "fec_fin_vigencia")
    private LocalDate fecFinVigencia;

    @NotNull
    @Column(name = "bln_activo", nullable = false)
    private Boolean blnActivo;


}