package com.example.vucem_catalogos_service.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.Instant;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "cat_pais_tratado_acuerdo")
public class CatPaisTratadoAcuerdo {
    @EmbeddedId
    private CatPaisTratadoAcuerdoId id;

    @MapsId("cvePais")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cve_pais", nullable = false, referencedColumnName = "cve_pais")
    private CatPais cvePais;

    @MapsId("idTratadoAcuerdo")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_tratado_acuerdo", nullable = false, referencedColumnName = "id_tratado_acuerdo")
    private CatTratadoAcuerdo idTratadoAcuerdo;

    @Column(name = "fec_captura")
    private LocalDate fecCaptura;

    @NotNull
    @Column(name = "fec_ini_vigencia", nullable = false)
    private LocalDate fecIniVigencia;

    @NotNull
    @Column(name = "fec_fin_vigencia", nullable = false)
    private LocalDate fecFinVigencia;

    @NotNull
    @Column(name = "bln_activo", nullable = false)
    private Boolean blnActivo;

    @Column(name = "bln_envio_electronico")
    private Boolean blnEnvioElectronico;
    @OneToMany
    private Set<CatVigenciaServicio> catVigenciaServicios = new LinkedHashSet<>();
    @OneToMany
    private Set<CnfNormaPaisCriterio> cnfNormaPaisCriterios = new LinkedHashSet<>();


}
