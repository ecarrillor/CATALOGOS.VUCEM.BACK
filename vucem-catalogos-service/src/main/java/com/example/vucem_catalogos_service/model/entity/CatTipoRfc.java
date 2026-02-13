package com.example.vucem_catalogos_service.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "cat_tipo_rfc")
public class CatTipoRfc {
    @EmbeddedId
    private CatTipoRfcId id;

    @Size(max = 254)
    @Column(name = "razon_social", length = 254)
    private String razonSocial;

    @Column(name = "fec_fin_vigencia")
    private Instant fecFinVigencia;

    @NotNull
    @Column(name = "fec_ini_vigencia", nullable = false)
    private Instant fecIniVigencia;

    @Size(max = 255)
    @Column(name = "direccion")
    private String direccion;

    @Size(max = 30)
    @Column(name = "telefono", length = 30)
    private String telefono;

    @Size(max = 20)
    @Column(name = "clave", length = 20)
    private String clave;

    @NotNull
    @Column(name = "bln_activo", nullable = false)
    private Boolean blnActivo;

    @Size(max = 320)
    @Column(name = "correo_electronico", length = 320)
    private String correoElectronico;

    @Size(max = 30)
    @Column(name = "fax", length = 30)
    private String fax;

    @Column(name = "bln_lab_acreditado")
    private Short blnLabAcreditado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cve_unidad_administrativa", referencedColumnName = "cve_unidad_administrativa")
    private CatUnidadAdministrativa cveUnidadAdministrativa;


}
