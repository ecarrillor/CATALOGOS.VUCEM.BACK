package com.example.vucem_catalogos_service.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "cat_unidad_medida")
public class CatUnidadMedida {
    @Id
    @Size(max = 10)
    @Column(name = "cve_unidad_medida", nullable = false, length = 10)
    private String cveUnidadMedida;

    @Size(max = 100)
    @Column(name = "descripcion", length = 100)
    private String descripcion;

    @Column(name = "fec_captura")
    private Instant fecCaptura;

    @Column(name = "fec_fin_vigencia")
    private Instant fecFinVigencia;

    @NotNull
    @Column(name = "fec_ini_vigencia", nullable = false)
    private Instant fecIniVigencia;

    @NotNull
    @Column(name = "bln_activo", nullable = false)
    private Boolean blnActivo;

    @Size(max = 20)
    @Column(name = "ide_origen_unidad_medida", length = 20)
    private String ideOrigenUnidadMedida;

    @Size(max = 50)
    @Column(name = "sigla", length = 50)
    private String sigla;

    @Size(max = 10)
    @Column(name = "id_oma", length = 10)
    private String idOma;

    @Size(max = 50)
    @Column(name = "sigla_cbp", length = 50)
    private String siglaCbp;


}
