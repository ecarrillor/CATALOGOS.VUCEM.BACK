package com.example.vucem_catalogos_service.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "cat_tratado_acuerdo")
public class CatTratadoAcuerdo {
    @Id
    @Column(name = "id_tratado_acuerdo", nullable = false)
    private Short id;

    @Size(max = 20)
    @Column(name = "ide_tipo_tratado_acuerdo", length = 20)
    private String ideTipoTratadoAcuerdo;

    @Size(max = 20)
    @NotNull
    @Column(name = "cve_tratado_acuerdo", nullable = false, length = 20)
    private String cveTratadoAcuerdo;

    @Size(max = 250)
    @Column(name = "nombre", length = 250)
    private String nombre;

    @Column(name = "bln_pexim")
    private Short blnPexim;

    @Column(name = "fec_captura")
    private Instant fecCaptura;

    @Column(name = "fec_fin_vigencia")
    private Instant fecFinVigencia;

    @Size(max = 20)
    @Column(name = "ide_tipo_cupo_saai", length = 20)
    private String ideTipoCupoSaai;

    @NotNull
    @Column(name = "fec_ini_vigencia", nullable = false)
    private Instant fecIniVigencia;

    @NotNull
    @Column(name = "bln_activo", nullable = false)
    private Boolean blnActivo;

    @Column(name = "bln_evaluar_individual")
    private Short blnEvaluarIndividual;


}
