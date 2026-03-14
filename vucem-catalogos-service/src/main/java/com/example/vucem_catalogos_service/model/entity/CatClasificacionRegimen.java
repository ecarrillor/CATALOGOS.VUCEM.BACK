package com.example.vucem_catalogos_service.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "cat_clasificacion_regimen")
public class CatClasificacionRegimen {
    @EmbeddedId
    private CatClasificacionRegimanId id;

    @MapsId("cveRegimen")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cve_regimen", nullable = false)
    private CatRegimen cveRegimen;

    @Size(max = 120)
    @Column(name = "nombre", length = 120)
    private String nombre;

    @Size(max = 3)
    @Column(name = "cod_regimen", length = 3)
    private String codRegimen;

    @NotNull
    @Column(name = "fec_ini_vigencia", nullable = false)
    private LocalDate fecIniVigencia;

    @Column(name = "fec_fin_vigencia")
    private LocalDate fecFinVigencia;

    @NotNull
    @Column(name = "bln_activo", nullable = false)
    private Boolean blnActivo;


}