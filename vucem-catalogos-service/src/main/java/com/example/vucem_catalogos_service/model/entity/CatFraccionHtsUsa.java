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
    private LocalDate fecCaptura;

    @NotNull
    @Column(name = "fec_ini_vigencia", nullable = false)
    private LocalDate fecIniVigencia;

    @Column(name = "fec_fin_vigencia")
    private LocalDate fecFinVigencia;

    @Size(max = 20)
    @Column(name = "ide_tipo_bien_fraccion", length = 20)
    private String ideTipoBienFraccion;

    @Column(name = "bln_exenta")
    private Boolean blnExenta;

    @NotNull
    @Column(name = "bln_activo", nullable = false)
    private Boolean blnActivo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cve_unidad_medida")
    private CatUnidadMedida cveUnidadMedida;


}
