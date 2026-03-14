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
@Table(name = "cat_empresa_recif")
public class CatEmpresaRecif {
    @Id
    @Size(max = 21)
    @Column(name = "recif", nullable = false, length = 21)
    private String recif;

    @Size(max = 13)
    @Column(name = "rfc", length = 13)
    private String rfc;

    @Size(max = 255)
    @Column(name = "razon_social")
    private String razonSocial;

    @NotNull
    @Column(name = "bln_activo", nullable = false)
    private Boolean blnActivo;

    @NotNull
    @Column(name = "fec_ini_vigencia", nullable = false)
    private LocalDate fecIniVigencia;

    @Column(name = "fec_fin_vigencia")
    private LocalDate fecFinVigencia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cve_unidad_administrativa")
    private CatUnidadAdministrativa cveUnidadAdministrativa;


}
