package com.example.vucem_catalogos_service.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cglib.core.Local;

import java.time.Instant;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "cat_dependencia")
public class CatDependencia {
    @Id
    @Column(name = "id_dependencia", nullable = false)
    private Long id;

    @Size(max = 120)
    @Column(name = "nombre", length = 120)
    private String nombre;

    @Size(max = 20)
    @Column(name = "acronimo", length = 20)
    private String acronimo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cve_calendario", referencedColumnName = "cve_calendario")
    private CatCalendario cveCalendario;

    @Column(name = "fec_captura")
    private LocalDate fecCaptura;

    @Column(name = "fec_fin_vigencia")
    private LocalDate fecFinVigencia;

    @Column(name = "bln_tramites_vu")
    private Boolean blnTramitesVu;

    @NotNull
    @Column(name = "fec_ini_vigencia", nullable = false)
    private LocalDate fecIniVigencia;

    @NotNull
    @Column(name = "bln_activo", nullable = false)
    private Boolean blnActivo;


}
