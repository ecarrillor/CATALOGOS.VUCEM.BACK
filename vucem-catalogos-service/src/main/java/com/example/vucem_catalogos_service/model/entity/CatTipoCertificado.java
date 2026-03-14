package com.example.vucem_catalogos_service.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "cat_tipo_certificado")
public class CatTipoCertificado {
    @Id
    @Column(name = "id_tipo_certificado", nullable = false)
    private Long id;

    @Size(max = 10)
    @NotNull
    @Column(name = "cve_catalogo", nullable = false, length = 10)
    private String cveCatalogo;

    @Size(max = 250)
    @Column(name = "descripcion", length = 250)
    private String descripcion;

    @Column(name = "fec_ini_vigencia")
    private LocalDate fecIniVigencia;

    @Column(name = "fec_fin_vigencia")
    private LocalDate fecFinVigencia;

    @Column(name = "bln_activo")
    private Boolean blnActivo;

    @Column(name = "bln_rfc")
    private Boolean blnRfc;


}