package com.example.vucem_catalogos_service.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.Instant;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "cat_pais")
public class CatPais {
    @Id
    @Size(max = 3)
    @Column(name = "cve_pais", nullable = false, length = 3)
    private String cvePais;

    @Size(max = 120)
    @Column(name = "nombre", length = 120)
    private String nombre;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cve_moneda", referencedColumnName = "cve_moneda")
    private CatMoneda cveMoneda;

    @Size(max = 2)
    @Column(name = "cve_pais_wco", length = 2)
    private String cvePaisWco;

    @Column(name = "fec_captura")
    private LocalDate fecCaptura;

    @Column(name = "fec_fin_vigencia")
    private LocalDate fecFinVigencia;

    @NotNull
    @Column(name = "fec_ini_vigencia", nullable = false)
    private LocalDate fecIniVigencia;

    @NotNull
    @Column(name = "bln_activo", nullable = false)
    private Boolean blnActivo;

    @Column(name = "bln_restriccion")
    private Boolean blnRestriccion;

    @Size(max = 120)
    @Column(name = "nombre_alterno", length = 120)
    private String nombreAlterno;

}
