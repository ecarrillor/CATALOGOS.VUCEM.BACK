package com.example.vucem_catalogos_service.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Data
@Entity
@Table(name = "cat_normal_oficial")
public class CatNormalOficial {
    @Id
    @Column(name = "id_norma_oficial", nullable = false)
    private Integer id;

    @Size(max = 30)
    @NotNull
    @Column(name = "clave_norma", nullable = false, length = 30)
    private String claveNorma;

    @NotNull
    @Column(name = "fec_ini_vigencia", nullable = false)
    private Instant fecIniVigencia;

    @Column(name = "fec_fin_vigencia")
    private Instant fecFinVigencia;

    @NotNull
    @Column(name = "bln_activo", nullable = false)
    private Boolean blnActivo;

    @Size(max = 300)
    @Column(name = "desc_norma", length = 300)
    private String descNorma;

    @Column(name = "fec_publicacion")
    private Instant fecPublicacion;

    @Column(name = "fec_entrada_vigor")
    private Instant fecEntradaVigor;

    @Size(max = 20)
    @Column(name = "ide_clasif_norma", length = 20)
    private String ideClasifNorma;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cve_pais", referencedColumnName = "cve_pais")
    private CatPai cvePais;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_norma_oficial_r", referencedColumnName = "id_norma_oficial")
    private CatNormalOficial idNormaOficialR;

    @Column(name = "bln_lote_estructurado")
    private Short blnLoteEstructurado;


}