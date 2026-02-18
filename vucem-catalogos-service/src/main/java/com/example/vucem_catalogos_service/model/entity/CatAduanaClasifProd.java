package com.example.vucem_catalogos_service.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.Instant;

@Data
@Entity
@Table(name = "cat_aduana_clasif_prod")
public class CatAduanaClasifProd {
    @Id
    @Column(name = "id_aduana_clasif_prod", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cve_aduana")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private CatAduana aduana;

    @NotNull
    @Column(name = "fec_ini_vigencia", nullable = false)
    private Instant fecIniVigencia;

    @Column(name = "fec_fin_vigencia")
    private Instant fecFinVigencia;

    @NotNull
    @Column(name = "bln_activo")
    private Boolean blnActivo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_clasif_producto")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private CatClasifProducto idClasifProducto;


}
