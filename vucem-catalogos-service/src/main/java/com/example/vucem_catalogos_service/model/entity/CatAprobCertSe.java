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
@Table(name = "cat_aprob_cert_se")
public class CatAprobCertSe {
    @Id
    @Column(name = "id_aprob_cert_se", nullable = false)
    private Short id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cve_unidad_administrativa", nullable = false)
    private CatUnidadAdministrativa cveUnidadAdministrativa;

    @Size(max = 20)
    @NotNull
    @Column(name = "ide_tipo_aprob_cert_se", nullable = false, length = 20)
    private String ideTipoAprobCertSe;

    @Size(max = 30)
    @NotNull
    @Column(name = "num_aprob_cert", nullable = false, length = 30)
    private String numAprobCert;

    @Column(name = "fec_emision")
    private LocalDate fecEmision;

    @NotNull
    @Column(name = "fec_ini_vigencia", nullable = false)
    private LocalDate fecIniVigencia;

    @Column(name = "fec_fin_vigencia")
    private LocalDate fecFinVigencia;

    @NotNull
    @Column(name = "bln_activo", nullable = false)
    private Boolean blnActivo;


}