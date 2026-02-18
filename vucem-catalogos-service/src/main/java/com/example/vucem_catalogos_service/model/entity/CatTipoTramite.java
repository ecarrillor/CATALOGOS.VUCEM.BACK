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
@Table(name = "cat_tipo_tramite")
public class CatTipoTramite {
    @Id
    @Column(name = "id_tipo_tramite", nullable = false)
    private Integer id;

    @Size(max = 2)
    @Column(name = "cve_servicio", length = 2)
    private String cveServicio;

    @Size(max = 500)
    @Column(name = "desc_servicio", length = 500)
    private String descServicio;

    @Size(max = 5)
    @Column(name = "cve_subservicio", length = 5)
    private String cveSubservicio;

    @Size(max = 500)
    @Column(name = "desc_subservicio", length = 500)
    private String descSubservicio;

    @Size(max = 11)
    @Column(name = "cve_modalidad", length = 11)
    private String cveModalidad;

    @Size(max = 500)
    @Column(name = "desc_modalidad", length = 500)
    private String descModalidad;

    @Size(max = 20)
    @Column(name = "cve_flujo", length = 20)
    private String cveFlujo;

    @Size(max = 250)
    @Column(name = "desc_flujo", length = 250)
    private String descFlujo;

    @Column(name = "nivel_servicio")
    private Short nivelServicio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_dependencia", referencedColumnName = "id_dependencia")
    private CatDependencia idDependencia;

    @Size(max = 120)
    @Column(name = "nom_serv_axway", length = 120)
    private String nomServAxway;

    @Size(max = 120)
    @Column(name = "nom_mensaje_axway", length = 120)
    private String nomMensajeAxway;

    @Size(max = 120)
    @Column(name = "url_axway", length = 120)
    private String urlAxway;

    @Column(name = "fec_captura")
    private Instant fecCaptura;

    @Column(name = "fec_fin_vigencia")
    private Instant fecFinVigencia;

    @Size(max = 250)
    @Column(name = "nombre", length = 250)
    private String nombre;

    @Column(name = "bln_replica_info")
    private Boolean blnReplicaInfo;

    @Column(name = "bln_automatico")
    private Boolean blnAutomatico;

    @NotNull
    @Column(name = "fec_ini_vigencia", nullable = false)
    private Instant fecIniVigencia;

    @NotNull
    @Column(name = "bln_activo", nullable = false)
    private Boolean blnActivo;

    @Column(name = "bln_asignacion")
    private Boolean blnAsignacion;

    @NotNull
    @Column(name = "cve_modulo", nullable = false)
    private Short cveModulo;


}
