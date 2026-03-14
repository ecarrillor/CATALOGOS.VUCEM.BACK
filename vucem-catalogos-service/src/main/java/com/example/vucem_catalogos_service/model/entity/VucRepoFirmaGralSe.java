package com.example.vucem_catalogos_service.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "vuc_repo_firma_gral_se")
public class VucRepoFirmaGralSe {
    @Id
    @Column(name = "id_firma_ttra_gral_se", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_tipo_tramite", nullable = false)
    private CatTipoTramite idTipoTramite;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_firma_gral_se")
    private VucFirmaGralSe idFirmaGralSe;

    @Size(max = 20)
    @Column(name = "ide_tipo_firma", length = 20)
    private String ideTipoFirma;

    @Size(max = 20)
    @Column(name = "rfc", length = 20)
    private String rfc;

    @Size(max = 200)
    @Column(name = "puesto", length = 200)
    private String puesto;

    @Column(name = "facsimil")
    private byte[] facsimil;

    @Column(name = "sello")
    private byte[] sello;

    @ColumnDefault("statement_timestamp()")
    @Column(name = "fec_creacion")
    private LocalDate fecCreacion;

    @Column(name = "fec_modificacion")
    private LocalDate fecModificacion;

    @Size(max = 20)
    @Column(name = "cve_usuario_mod", length = 20)
    private String cveUsuarioMod;

    @NotNull
    @Column(name = "fec_ini_vigenia", nullable = false)
    private LocalDate fecIniVigenia;

    @Column(name = "fec_fin_vigenia")
    private LocalDate fecFinVigenia;

    @ColumnDefault("1")
    @Column(name = "bln_activo")
    private Boolean blnActivo;

    @Size(max = 250)
    @Column(name = "password", length = 250)
    private String password;

    @Column(name = "key", length = Integer.MAX_VALUE)
    private String key;

    @Column(name = "cert", length = Integer.MAX_VALUE)
    private String cert;


}