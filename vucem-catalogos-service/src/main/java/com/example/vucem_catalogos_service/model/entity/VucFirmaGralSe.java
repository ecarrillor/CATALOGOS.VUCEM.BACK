package com.example.vucem_catalogos_service.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "vuc_firma_gral_se")
public class VucFirmaGralSe {
    @Id
    @Column(name = "id_firma_gral_se", nullable = false)
    private Long id;

    @Size(max = 13)
    @NotNull
    @Column(name = "rfc", nullable = false, length = 13)
    private String rfc;

    @Size(max = 250)
    @NotNull
    @Column(name = "password", nullable = false, length = 250)
    private String password;

    @NotNull
    @Column(name = "key", nullable = false, length = Integer.MAX_VALUE)
    private String key;

    @NotNull
    @Column(name = "cert", nullable = false, length = Integer.MAX_VALUE)
    private String cert;

    @Column(name = "fundamento_firmante", length = Integer.MAX_VALUE)
    private String fundamentoFirmante;

    @ColumnDefault("statement_timestamp()")
    @Column(name = "fec_creacion")
    private LocalDate fecCreacion;

    @ColumnDefault("1")
    @Column(name = "bln_activo")
    private Boolean blnActivo;


}