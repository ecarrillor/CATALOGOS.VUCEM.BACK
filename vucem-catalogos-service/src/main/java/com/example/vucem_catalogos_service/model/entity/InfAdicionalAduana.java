package com.example.vucem_catalogos_service.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "inf_adicional_aduana")
public class InfAdicionalAduana {
    @Id
    @Size(max = 3)
    @Column(name = "cve_aduana", nullable = false, length = 3)
    private String cveAduana;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cve_aduana", nullable = false, referencedColumnName = "cve_aduana")
    private CatAduana catAduana;

    @Size(max = 100)
    @Column(name = "correo_notificacion", length = 100)
    private String correoNotificacion;

    @NotNull
    @Column(name = "bln_cuenta_rni", nullable = false)
    private Short blnCuentaRni;

    @Size(max = 100)
    @Column(name = "tag_aduana", length = 100)
    private String tagAduana;


}