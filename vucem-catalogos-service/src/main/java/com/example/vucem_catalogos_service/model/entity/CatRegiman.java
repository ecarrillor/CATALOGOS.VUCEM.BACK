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
@Table(name = "cat_regimen")
public class CatRegiman {
    @Id
    @Size(max = 2)
    @Column(name = "cve_regimen", nullable = false, length = 2)
    private String cveRegimen;

    @Size(max = 120)
    @Column(name = "nombre", length = 120)
    private String nombre;

    @NotNull
    @Column(name = "fec_ini_vigencia", nullable = false)
    private Instant fecIniVigencia;

    @Column(name = "fec_fin_vigencia")
    private Instant fecFinVigencia;

    @NotNull
    @Column(name = "bln_activo", nullable = false)
    private Boolean blnActivo;

    @Size(max = 20)
    @Column(name = "cve_enumeracion", length = 20)
    private String cveEnumeracion;


}
