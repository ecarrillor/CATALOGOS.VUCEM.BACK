package com.example.vucem_catalogos_service.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Data
@Entity
@Table(name = "cat_pais")
public class CatPai {
    @Id
    @Size(max = 3)
    @Column(name = "cve_pais", nullable = false, length = 3)
    private String cvePais;

    @Size(max = 120)
    @Column(name = "nombre", length = 120)
    private String nombre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cve_moneda", referencedColumnName = "cve_moneda")
    private CatMoneda cveMoneda;

    @Size(max = 2)
    @Column(name = "cve_pais_wco", length = 2)
    private String cvePaisWco;

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

    @Column(name = "bln_restriccion")
    private Short blnRestriccion;

    @Size(max = 120)
    @Column(name = "nombre_alterno", length = 120)
    private String nombreAlterno;


}