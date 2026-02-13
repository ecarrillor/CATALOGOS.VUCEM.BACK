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
@Table(name = "cat_fraccion_aladi")
public class CatFraccionAladi {
    @Id
    @Column(name = "id_fraccion_aladi", nullable = false)
    private Integer id;

    @Size(max = 20)
    @Column(name = "ide_tipo_fraccion_aladi", length = 20)
    private String ideTipoFraccionAladi;

    @Size(max = 8)
    @NotNull
    @Column(name = "cve_fraccion", nullable = false, length = 8)
    private String cveFraccion;

    @Size(max = 1000)
    @Column(name = "descripcion", length = 1000)
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


}
