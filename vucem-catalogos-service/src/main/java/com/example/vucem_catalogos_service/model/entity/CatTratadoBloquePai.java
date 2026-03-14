package com.example.vucem_catalogos_service.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
@Entity
@Table(name = "cat_tratado_bloque_pais")
@Data
public class CatTratadoBloquePai {

    @EmbeddedId
    private CatTratadoBloquePaiId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cve_pais", referencedColumnName = "cve_pais",
            insertable = false, updatable = false)
    private CatPais pais;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tratado_acuerdo", referencedColumnName = "id_tratado_acuerdo",
            insertable = false, updatable = false)
    private CatTratadoAcuerdo tratadoAcuerdo;

    @Column(name = "fec_captura")
    private LocalDate fecCaptura;

    @NotNull
    @Column(name = "fec_ini_vigencia", nullable = false)
    private LocalDate fecIniVigencia;

    @Column(name = "fec_fin_vigencia")
    private LocalDate fecFinVigencia;

    @NotNull
    @Column(name = "bln_activo", nullable = false)
    private Boolean blnActivo;

    @Column(name = "bln_envio_electronico")
    private Boolean blnEnvioElectronico;

    @Column(name = "bln_muestra_certificado")
    private Boolean blnMuestraCertificado;
}