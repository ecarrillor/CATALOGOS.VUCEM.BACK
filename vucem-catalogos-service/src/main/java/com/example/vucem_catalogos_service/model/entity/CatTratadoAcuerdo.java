package com.example.vucem_catalogos_service.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;

@Data
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
    private Boolean blnPexim;

    @Column(name = "fec_captura")
    private LocalDate fecCaptura;

    @Column(name = "fec_fin_vigencia")
    private LocalDate fecFinVigencia;

    @Size(max = 20)
    @Column(name = "ide_tipo_cupo_saai", length = 20)
    private String ideTipoCupoSaai;

    @NotNull
    @Column(name = "fec_ini_vigencia", nullable = false)
    private LocalDate fecIniVigencia;

    @NotNull
    @Column(name = "bln_activo", nullable = false)
    private Boolean blnActivo;

    @Column(name = "bln_evaluar_individual")
    private Boolean blnEvaluarIndividual;


}
